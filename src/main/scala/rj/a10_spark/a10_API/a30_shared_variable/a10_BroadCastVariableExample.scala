package rj.a10_spark.a10_API.a30_shared_variable

import org.apache.log4j.Logger
import rj.a10_spark.a10_API.a25_Join.{Department, Employee}
import rj.a10_spark.z10_common.{Log, SparkUtil}

/*
  Emulating join using broadcast variable.
  This is for demonstration of b.cast variable only
  For join, we can use BroadCast Join which does the exact same thing
  (using broadcast function that acts as a hint for Spark to broadcast
   one side of a data)
 */
object a10_BroadCastVariableExample {

  val log: Logger = Log.getLogger(this.getClass.getName.dropRight(1))

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
    val rddTmpDepartment = spark.sparkContext.broadcast(
      departmentsRDD.keyBy{ d => (d.id) }  // turn to pair RDD
        .collectAsMap()                  // collect as Map
    )
    employeesRDD.map( emp =>
      if(rddTmpDepartment.value.get(emp.depId) != None){
        (emp.name, emp.age, emp.depId,
          rddTmpDepartment.value.get(emp.depId).get.id,
          rddTmpDepartment.value.get(emp.depId).get.name)
      } else {
        None
      }
    ).collect().foreach(println)
  }

}


/*

(Mary,33,IT,IT,IT  Department)
(Paul,45,IT,IT,IT  Department)
(Peter,26,MKT,MKT,Marketing Department)
(Jon,34,MKT,MKT,Marketing Department)
(Sarah,29,IT,IT,IT  Department)


 */

/*import spark.implicits._
employeesRDD.toDS.map { emp =>
  rddTmpDepartment.value.get(emp.depId) match {
    case Some(_) =>
      (emp.name, emp.age, emp.depId,
        rddTmpDepartment.value.get(emp.depId).get.id,
        rddTmpDepartment.value.get(emp.depId).get.name)
    case None => None
  }
}.collect().foreach(x => log.info(x))*/
