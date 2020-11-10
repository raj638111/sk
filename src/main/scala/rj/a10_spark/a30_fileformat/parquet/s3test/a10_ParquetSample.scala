package rj.a10_spark.a30_fileformat.parquet.s3test

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import rj.a10_spark.z10_common.{Log, SparkUtil}

object ParquetSample {

  val log: Logger = Log.getLogger(this.getClass.getName.dropRight(1))

  def main(args: Array[String]): Unit = {
    val param = ParamParquetTest().parse(args)
    val spark = param.spark
    val df = spark.read.parquet(param.sourcePath)
    log.info("Schema -> " + df.schema.treeString)
    val res = 1 to param.recordCount map (_ => df) reduce(_ union _)
    log.info("Schema. -> " + res.schema.treeString)
    res.repartition(1).write.option("compression", "snappy").parquet(param.targetPath)
  }
}

case class ParamParquetTest(
  value: String = null,
  sourcePath: String = null,
  targetPath: String = null,
  spark: SparkSession = null,
  val recordCount: Int = 0
){
  val log: Logger = Log.getLogger(this.getClass.getName.dropRight(1))

  def parse(args: Array[String]): ParamParquetTest ={
    val parser = new scopt.OptionParser[ParamParquetTest]("ParquetTest") {
      head("scopt", "3.x")
      opt[String]("value").optional().action { (x, c) =>
        c.copy(value = x) }
      opt[String]("sourcePath") optional() action { (x, c) =>
        c.copy(sourcePath = x) }
      opt[String]("targetPath") optional() action { (x, c) =>
        c.copy(targetPath = x) }
      opt[Int]("recordCount") optional() action { (x, c) =>
        c.copy(recordCount = x) }
    }
    parser.parse(args, ParamParquetTest()) match {
      case Some(param) =>
        param.copy(spark = SparkUtil.getSparkSession("ParquetTest"))
      case _ =>
        log.error("Bad arguments")
        throw new Exception
    }
  }


}

/**

/usr/bin/spark-submit --driver-memory 5G --executor-memory 15G --num-executors 5 --executor-cores 4 --class rj.a30_fileformat.parquet.s3test.ParquetTest ./sk-assembly-0.1.0-SNAPSHOT.jar --value "-" --sourcePath "s3://rajvkl/test2/" --targetPath "s3://rajvkl/test3/" --recordCount 1


 */