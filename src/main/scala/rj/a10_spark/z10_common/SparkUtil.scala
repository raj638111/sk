package rj.a10_spark.z10_common

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession

object SparkUtil {

  val log: Logger = Log.getLogger(this.getClass.getName.dropRight(1))

  def getSparkSession(appName: String = "-"): SparkSession = {
    log.debug("Creating spark context with app name -> " + appName)
    val ss = SparkSession
      .builder()
      .appName(appName)
      .master("local[*]")
      .config("spark.dynamicAllocation.enabled", true)
      .config("spark.shuffle.service.enabled", true)
      .config("spark.qubole.autoscaling.stagetime", "5000")
      .config("spark.sql.parquet.mergeSchema", false)
      .config("spark.sql.parquet.filterPushdown", true)
      .config("spark.sql.hive.metastorePartitionPruning", true)
      .config("spark.hadoop.parquet.enable.summary-metadata", false)
      .config("spark.hadoop.mapreduce.fileoutputcommitter.algorithm.version", "2")
      .config("spark.yarn.executor.memoryOverhead", "5000")
      .enableHiveSupport()
      .getOrCreate();
    log.debug("Spark Session Created...")
    ss.conf.set("spark.hadoop.mapreduce.fileoutputcommitter.algorithm.version", "2")
    ss.sql("set hive.exec.dynamic.partition.mode=nonstrict")
    ss.sql("set spark.sql.parquet.compression.codec=snappy")
    ss.conf.set("spark.sql.broadcastTimeout", 36000) //Experimental
    ss.sparkContext.hadoopConfiguration.
      set("spark.hadoop.mapreduce.fileoutputcommitter.algorithm.version", "2")
    ss
  }

}