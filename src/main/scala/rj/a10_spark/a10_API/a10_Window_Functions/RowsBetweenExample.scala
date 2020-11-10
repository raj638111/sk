package rj.a10_spark.a10_API.a10_Window_Functions

import org.apache.log4j.Logger
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
import rj.a10_spark.z10_common.{Log, SparkUtil}

// An example of using 'rowsBetween' as a Frame
object RowsBetweenExample {

  val log: Logger = Log.getLogger(this.getClass.getName.dropRight(1))

  def main(args: Array[String]): Unit = {
    val spark = SparkUtil.getSparkSession(this.getClass.getName)
    import spark.implicits._
    val currentAndNext = Window
      // 'author' with same name will belong to the same partition
      .partitionBy($"author")
      // Order the partition based on descending order of 'revenue'
      .orderBy($"revenue".desc)
      // Specifies the Frame that will be selected from the partition for a given row
      // The function that is used with this Spec will do calculation
      //  over this frame for the given row
      // Note: Both start & end are relative positions from the current row
      //       '0' means current row
      // 1st argument Upper bound
      // 2nd argument Lower bound
      .rowsBetween(Window.currentRow, 1) // NOte: Specifying -1 as lower bound do not work

    val input = List(
      Info("a1", "a1b1", 100),
      Info("a1", "a1b2", 110),
      Info("a1", "a1b3", 120),
      Info("a1", "a1b4", 130),
      Info("a2", "a2b1", 200)
    )
    val df = spark.sparkContext.parallelize(input).toDF
    df.show(100, false)
    log.info(df.schema.treeString)
    // Syntax
    //  <function> over WindowSpec
    //  Specifying '<function> over' makes a normal 'function' a Window Function
    df.withColumn("sum", sum($"revenue") over currentAndNext) match {
      case df => df.show(false)
    }

  }

}

case class Info(
  author: String,
  book: String,
  revenue: Int)

/* Result
+------+----+-------+
|author|book|revenue|
+------+----+-------+
|a1    |a1b1|100    |
|a1    |a1b2|110    |
|a1    |a1b3|120    |
|a1    |a1b4|130    |
|a2    |a2b1|200    |
+------+----+-------+

+------+----+-------+---+
|author|book|revenue|sum|
+------+----+-------+---+
|a2    |a2b1|200    |200|
|a1    |a1b4|130    |250|
|a1    |a1b3|120    |230|
|a1    |a1b2|110    |210|
|a1    |a1b1|100    |100|
+------+----+-------+---+

https://spark.apache.org/docs/latest/api/scala/index.html#org.apache.spark.sql.expressions.WindowSpec
https://databricks.com/blog/2015/07/15/introducing-window-functions-in-spark-sql.html

*/
