package rj.a00_scala.a15_Regex

object a10_replaceAll {

  def main(args: Array[String]): Unit = {
    val str = "A man, a plan, a canal: Panama"
    val regex = "[^a-zA-Z0-9]"
    val result = str.replaceAll(regex, "")
    println("Result -> " + result)
  }

}

// https://alvinalexander.com/scala/how-to-replace-regular-expression-regex-patterns-in-strings-scala
