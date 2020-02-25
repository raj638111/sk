package rj.a10_spark.a20_SQL.a20_Count_Distinct

import org.apache.log4j.Logger
import rj.a10_spark.z10_common.{Log, SparkUtil}

object CountDistinct {

  val log: Logger = Log.getLogger(this.getClass.getName.dropRight(1))

  def main(args: Array[String]): Unit = {
    val spark = SparkUtil.getSparkSession(this.getClass.getName)
    import spark.implicits._
    val input = List(1, 2, 2, 3, 3, 3)
    spark.sparkContext.parallelize(input).toDF("no") match {
      case df =>
        df.show(100, false)
        df.createOrReplaceTempView("vw")
    }
    spark.sql("select count(distinct no) as ct from vw") match {
      case df =>
        df.show(100, false)
        val count = df.collect()(0).getAs[Long]("ct")
        log.info("count -> " + count)
        log.info("df.count -> " + df.count)
        log.info("df.collect.size -> " + df.collect().size)
    }
  }

}
