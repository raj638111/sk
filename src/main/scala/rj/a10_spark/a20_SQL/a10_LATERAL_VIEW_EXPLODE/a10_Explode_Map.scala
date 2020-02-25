package rj.a10_spark.a20_SQL.a10_LATERAL_VIEW_EXPLODE

import org.apache.log4j.Logger
import rj.a10_spark.z10_common.{Log, SparkUtil}

object a10_Explode_Map {

  val log: Logger = Log.getLogger(this.getClass.getName.dropRight(1))

  def main(args: Array[String]): Unit = {
    val spark = SparkUtil.getSparkSession(this.getClass.getName)
    import spark.implicits._
    val input = List(1, 2, 3)
    spark.sparkContext.parallelize(input).toDF("no") match {
      case df =>
        df.show(100, false)
        df.createOrReplaceTempView("vw")
    }
    """
      |select no, map('key1', 'val1', 'key2', 'val2') as mp
      |from vw
      |""".stripMargin match {
      case sql =>
        log.info(sql)
        val df = spark.sql(sql)
        df.show(100, false)
        df.createOrReplaceTempView("vw")
    }
    """
      |select vw.*, mp1.k, mp1.v
      |from
      |vw
      |lateral view explode (mp) mp1 as k, v
      |""".stripMargin match {
      case sql =>
        val df = spark.sql(sql)
        df.show(100, false)
    }

  }

}

/*

+---+
|no |
+---+
|1  |
|2  |
|3  |
+---+

20/01/13 14:55:05 | INFO | rj.a10_spark.a20_SQL.a10_LATERAL_VIEW_EXPLODE.a10_Explode_Map | main() | 24 |
select no, map('key1', 'val1', 'key2', 'val2') as mp
from vw

+---+----------------------------+
|no |mp                          |
+---+----------------------------+
|1  |[key1 -> val1, key2 -> val2]|
|2  |[key1 -> val1, key2 -> val2]|
|3  |[key1 -> val1, key2 -> val2]|
+---+----------------------------+

+---+----------------------------+----+----+
|no |mp                          |k   |v   |
+---+----------------------------+----+----+
|1  |[key1 -> val1, key2 -> val2]|key1|val1|
|1  |[key1 -> val1, key2 -> val2]|key2|val2|
|2  |[key1 -> val1, key2 -> val2]|key1|val1|
|2  |[key1 -> val1, key2 -> val2]|key2|val2|
|3  |[key1 -> val1, key2 -> val2]|key1|val1|
|3  |[key1 -> val1, key2 -> val2]|key2|val2|
+---+----------------------------+----+----+

 */