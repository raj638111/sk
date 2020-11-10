package rj.a10_Questions.d00_Recursion

import org.scalatest.FunSuite



// 7 => 1, 1, 2, 3, 5, 8, 13
class a00_Fibonacci extends FunSuite {

  test("-"){
    println(recursiveNdynamic(7))
  }

  // Recursive only
  def recursiveOnly(n: Int): Int = {
    if (n <= 1) {
      n
    } else {
      recursiveOnly(n - 1) + recursiveOnly(n - 2)
    }
  }

  // Recursive & Dynamic
  def recursiveNdynamic(n: Int): Int = {
    def rec(n: Int, part: (Int, Int)): Int = {
      if (n <= 0) {
        part._2
      }
      else {
        val sum = part._1 + {
          if (part._2 == 0) 1 else part._2
        }
        rec(n - 1, (part._2, sum))
      }
    }
    rec(n, (0, 0))
  }

}

/*
  Recursive only do not store intermediate results so compute intensive
  6 => 1, 1, 2, 3, 5, 8
  fib(6)

  fib(5)
    fib(4)
      fib(3)
        fib(2)
          fib(1)
            1
          fib(0)
            0
        fib(1)
          1
      fib(2)
        fib(1)
          1
        fib(0)
          0
    fib(3)
      fib(2)
        fib(1)
          1
        fib(0)
          0
      fib(1)
        1
  fib(4)
    fib(3)
      fib(2)
        fib(1)
          1
        fib(0)
          0
      fib(1)
        1
    fib(2)
      fib(1)
        1
      fib(0)
        0

 */