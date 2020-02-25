package rj.a10_spark.a10_API.a18_UDAF

import org.apache.log4j.Logger
import org.apache.spark.sql.functions.approx_count_distinct
import rj.a10_spark.z10_common.{Log, SparkUtil}

object approx_distinct {

  val log: Logger = Log.getLogger(this.getClass.getName.dropRight(1))

  def main(args: Array[String]): Unit = {
    val spark = SparkUtil.getSparkSession(this.getClass.getName)
    import spark.implicits._
    val input = List((1, "a"), (2, "a"), (2, "b"), (2, "a"))
    spark.sparkContext.parallelize(input).toDF("no", "str") match {
      case df =>
        df.show(100, false)
        df.groupBy($"no", $"str")
          .agg(approx_count_distinct($"no")) match {
          case df =>
            df.show(100, false)
            df.collect.foreach(x => log.info("x -> " + x))
            log.info("df.collect.size -> " + df.collect.size)
            log.info("df.count -> " + df.count)
        }

        df.groupBy($"no", $"str")
          .agg(approx_count_distinct($"str")) match {
          case df =>
            df.show(100, false)
            df.collect.foreach(x => log.info(".x -> " + x))
            log.info(".df.collect.size -> " + df.collect.size)
            log.info(".df.count -> " + df.count)
        }

    }
  }

}
