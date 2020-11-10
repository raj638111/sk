package rj.a10_spark.z20_test

import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.format.DateTimeFormatter

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import rj.a10_spark.z10_common.{Log, SparkUtil}

object a10_Test {

  val log: Logger = Log.getLogger(this.getClass.getName.dropRight(1))

  def main(args: Array[String]): Unit = {
    val spark = SparkUtil.getSparkSession("Test")
    val str = "Can u type for me and type in chat wndow"
    import spark.implicits._
    val df = spark.sparkContext.parallelize(str.split(" ").map(_.trim)).toDF("word")
    df.groupBy($"word").count() match {
      case df =>
        df.show
    }
  }


  import org.apache.spark.sql.functions.udf
  def getWeekEndDate = udf((dt: String) => {
    import java.util.Calendar
    val dtLocal = new SimpleDateFormat("yyyy-MM-dd").parse(dt)
    val calendar: Calendar = Calendar.getInstance
    calendar.setTime(dtLocal)
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) -
      calendar.getFirstDayOfWeek()
    calendar.add(Calendar.DAY_OF_MONTH, -dayOfWeek)
    calendar.add(Calendar.DAY_OF_MONTH, 6)
    new java.sql.Date(calendar.getTime.getTime)
  })

  val list = List(
    Rec(user = "u1", org = "o1", date = "2019-05-03"),
    Rec(user = "u2", org = null, date = "2019-05-03"),
    Rec(user = "u3", org = "o3", date = "2019-05-04"),
    Rec(user = "u4", org = "o2", date = "2019-05-15"),
    Rec(user = "u5", org = "o2", date = "2019-05-16"),
    Rec(user = "u6", org = "o3", date = "2019-05-24"),
    Rec(user = "u7", org = "o3", date = "2019-05-24")
  )

  def getResult(spark: SparkSession): Unit = {
    import spark.implicits._
    spark.sparkContext.parallelize(list)
      .toDS()
      .withColumn("week_end", getWeekEndDate($"date"))
    match {
      case df =>
        df.orderBy($"date").show(100, false)
        df.where("org is not null")
          .createTempView("tbl")
    }

    spark.sql(
      """
        select week_end, count(*) as count from tbl group by week_end
      """).createTempView("groupedTbl")

    spark.sql("""
      select t1.week_end, sum(t2.count) as user_count
      from
        groupedTbl t1
      join
        groupedTbl t2
      on t2.week_end <= t1.week_end
      group by t1.week_end
    """)createTempView("accumTbl")

    spark.sql("""
        select
          t1.week_end,
          first(t1.user_count) as user_count,
          count(distinct t2.org) as org_count,
          round(first(t1.user_count) / count(distinct t2.org), 2) as fraction
        from
          accumTbl t1
        join
          tbl t2
        on t2.week_end <= t1.week_end
        group by t1.week_end
        """).orderBy("week_end").show(100, false)

  }





/*
   import spark.implicits._
    val df = spark.sparkContext.parallelize(List("This is a", "is a"))
    val words = df.map( line => line.split(" "))
    val mapped = words.map(word => (word, 1))

 */

  /*def main(args: Array[String]): Unit = {
    val spark = SparkUtil.getSparkSession("Test")
    val lst = List((1, 1), (2, 1), (3, 1), (4, 1),
      (5, 2), (6, 2), (7, 2), (8, 3))
    val window = Window.partitionBy().orderBy("Id")
      .rowsBetween(Window.currentRow, 2)
    import spark.implicits._
    val df = spark.sparkContext.parallelize(lst).toDF("Id", "Num")
    df.show(100, false)
    import org.apache.spark.sql.functions._
    val fn = udf((num: Int,
                  list: scala.collection.mutable.WrappedArray[Int]) =>
      list.count(_ == num) match {
        case 3 => true
        case _ => false
      }
    )
    df.withColumn("lst", collect_list("Num") over window)
      .withColumn("flag", fn(col("Num"), col("lst")))
      .where(expr("flag = true")) match {
      case df =>
        df.select($"Num").distinct().show(100, false)
    }
  }*/

  def findNumber(arr: Array[Int], k: Int): String = {
    // Write your code here
    arr.exists(_ == k) match {
      case true => "YES"
      case _ => "NO"
    }
  }

  def oddNumbers(l: Int, r: Int): Array[Int] = {
    (l to r).filter { x =>
      l % 3 != 0
    }.toArray
  }



}


case class Rec(
  user: String = null,
  org: String = null,
  date: String = null)


/*
Input

+---+---+
|Id |Num|
+---+---+
|1  |1  |
|2  |1  |
|3  |1  |
|4  |1  |
|5  |2  |
|6  |2  |
|7  |2  |
|8  |3  |
+---+---+

Output

+---+
|Num|
+---+
|1  |
|2  |
+---+

 */