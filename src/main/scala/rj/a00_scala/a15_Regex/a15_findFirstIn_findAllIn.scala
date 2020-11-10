package rj.a00_scala.a15_Regex

object a15_findFirstIn_findAllIn {

  def main(args: Array[String]): Unit = {
    val address = "123 Main Street Suite 101"
    val regex = "[0-9]+".r
    regex.findFirstIn(address) match {
      case Some(token) => println("Token = " + token)
      case _ => println("Nothing to find")
    }

    val result = regex.findAllIn(address)
    println(result.toList.mkString(","))
  }

}
