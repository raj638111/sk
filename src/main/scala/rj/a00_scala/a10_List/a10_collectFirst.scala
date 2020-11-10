package rj.a00_scala.a10_List

object a10_collectFirst {

  def main(args: Array[String]): Unit = {
    val lst = List(1, 2, 3, 3, 4, 5)
    val result = lst.collectFirst{
      case no if no > 3 => no
    }
    println(result)
  }

}
