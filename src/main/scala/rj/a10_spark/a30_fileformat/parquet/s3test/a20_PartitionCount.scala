package rj.a10_spark.a30_fileformat.parquet.s3test

import org.apache.log4j.Logger
import rj.a10_spark.z10_common.{Log, SparkUtil}

object a20_PartitionCount {

  val log: Logger = Log.getLogger(this.getClass.getName.dropRight(1))

  def main(args: Array[String]): Unit = {
    val param = ParamParquetTest().parse(args)
    val spark = param.spark
    val df = spark.read.parquet(param.sourcePath)
    log.info("No of partitions -> " + df.rdd.getNumPartitions)
    log.info("Total count -> " + df.count())
    /*val res = df.rdd.mapPartitions{iterator =>
      List(iterator.size).toIterator
    }.collect
    res.foreach(x => log.info("Count in partition -> " + x))*/
    import org.apache.spark.sql.functions.spark_partition_id
    val res = df.groupBy(spark_partition_id).count
    res.collect() match {
      case res =>
        res.foreach(x => log.info("Count in partition -> " + x))
    }

  }

}

/**

/usr/bin/spark-submit --driver-memory 5G --executor-memory 15G --num-executors 5 --executor-cores 4 --class rj.a30_fileformat.parquet.s3test.a20_PartitionCount ./sk-assembly-0.1.0-SNAPSHOT.jar  --sourcePath "s3://rajvkl/test4/"


 */

/*
Input: Single parquet file of 1.6GB
20/03/05 03:38:27 | INFO | rj.a30_fileformat.parquet.s3test.a20_PartitionCount | main() | 14 | No of partitions -> 14
20/03/05 03:38:28 | INFO | rj.a30_fileformat.parquet.s3test.a20_PartitionCount | main() | 15 | Total count -> 275248400
20/03/05 03:38:44 | INFO | rj.a30_fileformat.parquet.s3test.a20_PartitionCount | apply() | 22 | Count in partition -> [12,19640100]
20/03/05 03:38:44 | INFO | rj.a30_fileformat.parquet.s3test.a20_PartitionCount | apply() | 22 | Count in partition -> [1,19640100]
20/03/05 03:38:44 | INFO | rj.a30_fileformat.parquet.s3test.a20_PartitionCount | apply() | 22 | Count in partition -> [13,287000]
20/03/05 03:38:44 | INFO | rj.a30_fileformat.parquet.s3test.a20_PartitionCount | apply() | 22 | Count in partition -> [6,19640100]
20/03/05 03:38:44 | INFO | rj.a30_fileformat.parquet.s3test.a20_PartitionCount | apply() | 22 | Count in partition -> [3,19640100]
20/03/05 03:38:44 | INFO | rj.a30_fileformat.parquet.s3test.a20_PartitionCount | apply() | 22 | Count in partition -> [5,19640100]
20/03/05 03:38:44 | INFO | rj.a30_fileformat.parquet.s3test.a20_PartitionCount | apply() | 22 | Count in partition -> [9,19640100]
20/03/05 03:38:44 | INFO | rj.a30_fileformat.parquet.s3test.a20_PartitionCount | apply() | 22 | Count in partition -> [4,19640100]
20/03/05 03:38:44 | INFO | rj.a30_fileformat.parquet.s3test.a20_PartitionCount | apply() | 22 | Count in partition -> [8,19640100]
20/03/05 03:38:44 | INFO | rj.a30_fileformat.parquet.s3test.a20_PartitionCount | apply() | 22 | Count in partition -> [7,39280200]
20/03/05 03:38:44 | INFO | rj.a30_fileformat.parquet.s3test.a20_PartitionCount | apply() | 22 | Count in partition -> [10,19640100]
20/03/05 03:38:44 | INFO | rj.a30_fileformat.parquet.s3test.a20_PartitionCount | apply() | 22 | Count in partition -> [11,19640100]
20/03/05 03:38:44 | INFO | rj.a30_fileformat.parquet.s3test.a20_PartitionCount | apply() | 22 | Count in partition -> [2,19640100]
20/03/05 03:38:44 | INFO | rj.a30_fileformat.parquet.s3test.a20_PartitionCount | apply() | 22 | Count in partition -> [0,19640100]

 */
