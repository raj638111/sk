package rj.a10_spark.a10_API.a25_Join

import rj.a10_spark.z10_common.SparkUtil

object a10_BroadcastJoinExample {

  def main(args: Array[String]): Unit = {
    val spark = SparkUtil.getSparkSession("")
    val employeesRDD = spark.sparkContext.parallelize(Seq(
      Employee("Mary", 33, "IT"),
      Employee("Paul", 45, "IT"),
      Employee("Peter", 26, "MKT"),
      Employee("Jon", 34, "MKT"),
      Employee("Sarah", 29, "IT"),
      Employee("Steve", 21, "Intern")
    ))
    val departmentsRDD = spark.sparkContext.parallelize(Seq(
      Department("IT", "IT  Department"),
      Department("MKT", "Marketing Department"),
      Department("FIN", "Finance & Controlling")
    ))
    import spark.implicits._
    val employeesDF = employeesRDD.toDF
    val departmentsDF = departmentsRDD.toDF
    import org.apache.spark.sql.functions.broadcast
    val tmpDepartments = broadcast(departmentsDF.as("departments"))
    val df = employeesDF.join(tmpDepartments,
      $"depId" === $"id",  // join by employees.depID == departments.id
      "inner")
    df.explain()
    df.show(100, false)
  }

}

case class Employee(name:String, age:Int, depId: String)
case class Department(id: String, name: String)

/*

20/03/05 13:44:52 | WARN | org.apache.spark.sql.execution.command.SetCommand | logWarning() | 66 | 'SET hive.exec.dynamic.partition.mode=nonstrict' might not work, since Spark doesn't support changing the Hive config dynamically. Please pass the Hive-specific config by adding the prefix spark.hadoop (e.g. spark.hadoop.hive.exec.dynamic.partition.mode) when starting a Spark application. For details, see the link: https://spark.apache.org/docs/latest/configuration.html#dynamically-loading-spark-properties.
== Physical Plan ==
*(2) BroadcastHashJoin [depId#14], [id#22], Inner, BuildRight
:- *(2) Filter isnotnull(depId#14)
:  +- *(2) SerializeFromObject [staticinvoke(class org.apache.spark.unsafe.types.UTF8String, StringType, fromString, assertnotnull(input[0, rj.a10_spark.a10_API.a25_Join.Employee, true]).name, true, false) AS name#12, assertnotnull(input[0, rj.a10_spark.a10_API.a25_Join.Employee, true]).age AS age#13, staticinvoke(class org.apache.spark.unsafe.types.UTF8String, StringType, fromString, assertnotnull(input[0, rj.a10_spark.a10_API.a25_Join.Employee, true]).depId, true, false) AS depId#14]
:     +- Scan ExternalRDDScan[obj#11]
+- BroadcastExchange HashedRelationBroadcastMode(List(input[0, string, false]))
   +- *(1) Filter isnotnull(id#22)
      +- *(1) SerializeFromObject [staticinvoke(class org.apache.spark.unsafe.types.UTF8String, StringType, fromString, assertnotnull(input[0, rj.a10_spark.a10_API.a25_Join.Department, true]).id, true, false) AS id#22, staticinvoke(class org.apache.spark.unsafe.types.UTF8String, StringType, fromString, assertnotnull(input[0, rj.a10_spark.a10_API.a25_Join.Department, true]).name, true, false) AS name#23]
         +- Scan ExternalRDDScan[obj#21]
+-----+---+-----+---+--------------------+
|name |age|depId|id |name                |
+-----+---+-----+---+--------------------+
|Mary |33 |IT   |IT |IT  Department      |
|Paul |45 |IT   |IT |IT  Department      |
|Peter|26 |MKT  |MKT|Marketing Department|
|Jon  |34 |MKT  |MKT|Marketing Department|
|Sarah|29 |IT   |IT |IT  Department      |
+-----+---+-----+---+--------------------+


Process finished with exit code 0





 */