package rj.a10_spark.a10_API.a15_UDAF_Custom

import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, LongType, StructField, StructType}
import org.apache.spark.sql.Row
import rj.a10_spark.z10_common.SparkUtil

object UDAFExample {


  def main(args: Array[String]): Unit = {
    val spark = SparkUtil.getSparkSession()
    val ids = spark.range(1, 9)
    ids.createTempView("ids")
    val df = spark.sql("select id, id % 3 as group_id from ids order by group_id")
    df.show(false)
    df.createTempView("simple")
    spark.udf.register("gm", new Mean)
    spark.sql("select group_id, gm(id) from simple group by group_id") match {
      case df =>
        df.show(false)
    }
  }
}

class Mean extends UserDefinedAggregateFunction {

  // This is the input fields for your aggregate function
  // ie We describe, this UDF expects a Double as an input
  override def inputSchema: org.apache.spark.sql.types.StructType = {
    StructType(StructField("value", DoubleType) :: Nil)
  }

  // Buffer Schema: This is the internal fields you keep for computing your aggregate.
  override def bufferSchema: StructType = StructType(
    StructField("count", LongType) ::
    StructField("product", DoubleType) :: Nil
  )

  // This is the initial value for your buffer schema.
  // On a given node, this method is called once for each group
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0) = 0L  // Initial value of FIRST field ('count') of Buffer schema
    buffer(1) = 0.0 // Initial value of SECOND field ('product') of Buffer schema
  }

  // This is the output type of your aggregation function.
  override def dataType: DataType = DoubleType

  // ???: The doc says, if true, for the same input, the function returns the same output
  //      But not sure what that means?
  override def deterministic: Boolean = true

  // This is how to update your buffer schema given an input.
  // In a given group, Spark will call this method for each input record
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    // Increment 'count' in Buffer Schema
    buffer(0) = buffer.getAs[Long](0) + 1
    // Add:  'product' in Buffer Schema + Input Field ('value')
    buffer(1) = buffer.getAs[Double](1) + input.getAs[Double](0)
  }

  // This is how to merge two objects with the bufferSchema type.
  // If the function supports partial aggregates,
  // spark MIGHT (as an optimization) compute partial result and combine them together
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    buffer1(0) = buffer1.getAs[Long](0) + buffer2.getAs[Long](0)
    buffer1(1) = buffer1.getAs[Double](1) + buffer2.getAs[Double](1)
  }

  // This is where you output the final value, given the final value of your bufferSchema.
  // Once all the entries for a group are EXHAUSTED, spark will call evaluate to get the final result
  override def evaluate(buffer: Row): Any = {
    buffer.getDouble(1) / buffer.getLong(0)
  }

}

/*

+---+--------+
|id |group_id|
+---+--------+
|3  |0       |
|6  |0       |
|4  |1       |
|7  |1       |
|1  |1       |
|2  |2       |
|8  |2       |
|5  |2       |
+---+--------+

+--------+------------------------+
|group_id|mean(CAST(id AS DOUBLE))|
+--------+------------------------+
|0       |4.5                     |
|1       |4.0                     |
|2       |5.0                     |
+--------+------------------------+


https://ragrawal.wordpress.com/2015/11/03/spark-custom-udaf-example/
https://docs.databricks.com/spark/latest/spark-sql/udaf-scala.html

 */