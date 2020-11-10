package rj.a00_scala

import org.scalatest._


class Q121_Best_Time_to_Buy_n_Sell_Stock extends FunSuite with Matchers {

  /*
    Say you have an array for which the ith element is the
    price of a given stock on day i.

    If you were only permitted to complete at most one transaction
    (i.e., buy one and sell one share of the stock), design an algorithm
    to find the maximum profit.

    Note that you cannot sell a stock before you buy one.

    Example 1:

    Input: [7,1,5,3,6,4]
    Output: 5
    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
                 Not 7-1 = 6, as selling price needs to be larger than buying price.
    Example 2:

    Input: [7,6,4,3,1]
    Output: 0
    Explanation: In this case, no transaction is done, i.e. max profit = 0.
   */
  test("-"){
    //println("Result -> " + maxProfit2(Array(7,1,5,3,6,4)))
    println("Result -> " + maxProfit2(Array(1, 2)))
  }

  def maxProfit2(prices: Array[Int]): Int = {
    var result: Int = Int.MinValue
    println(s"Input = ${prices.mkString(",")}")
    for(i <- 0 to prices.size - 2) {
      println("i = " + i)
      for( j <- (i + 1) to prices.size - 1){
        println("j = " + j)
        prices(j) - prices(i) > result match {
          case true =>
            result = prices(j) - prices(i)
          case false =>
        }
      }
    }
    if (result < 0) {
      0
    }else {
      result
    }
  }

  /*def maxProfit(prices: Array[Int]): Int = {
    var result: Int = 0
    println(s"Input = ${prices.mkString(",")}")
    for{
      i <- 0 to (prices.size - 2)
      j <- (i + 1) to (prices.size - 1)
      _ = println(s"i -> $i, val -> ${prices(i)}")
      _ = println(s"j -> $j, val -> ${prices(j)}")
      diff = prices(j) - prices(i)
      _ = println(s"diff -> $diff")
      _ = updateProfit(diff, result)
    } yield {}
    def updateProfit(curProfit: Int, earlierProfit: Int): Unit= {
      println(s"curProfit -> $curProfit, earlierProfit -> $earlierProfit")
      curProfit > earlierProfit match {
        case true =>
          println("New Profit -> " + curProfit)
          result = curProfit
        case false =>
          println(".New Profit -> " + earlierProfit)
          result = earlierProfit
      }
    }
    result
  }*/


}

