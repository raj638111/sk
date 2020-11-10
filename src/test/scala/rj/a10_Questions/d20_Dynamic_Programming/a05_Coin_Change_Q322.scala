package rj.a10_Questions.d20_Dynamic_Programming

import org.scalatest._



class a05_Coin_Change_Q322 extends FunSuite with Matchers {

  /*
    You are given coins of different denominations and a total amount of money amount.
    Write a function to compute the fewest number of coins that you need to
    make up that amount. If that amount of money cannot be made up
    by any combination of the coins, return -1.

    Example 1:

    Input: coins = [1, 2, 5], amount = 11
    Output: 3
    Explanation: 11 = 5 + 5 + 1
    Example 2:

    Input: coins = [2], amount = 3
    Output: -1
    Note:
    You may assume that you have an infinite number of each kind of coin.
 */
  test("-"){
    val res = coinChange(Array(1, 2, 5), 11)
    res.foreach(println _)
  }

  def coinChange(coins: Array[Int], amount: Int): List[(Int, Int)] = {
    val sorted = coins.sortWith(_ > _).toList
    sorted match {
      case Nil => Nil
      case coin :: Nil =>
        sumUsingCoin(coin, amount) match {
          case (0, rem) if rem > 0 =>
            throw new Exception("This cannot happen")
          case (count, _) if count == 0 =>
            Nil
          case (count, _)  =>
            (coin, count) :: Nil
        }
      case coin :: rest =>
        sumUsingCoin(coin, amount) match {
          case (count, rem) if count == 0 && rem > 0 =>
            coinChange(rest.toArray, amount)
          case (count, rem) if count > 0 =>
            (coin, count) :: coinChange(rest.toArray, rem)
          case _ => Nil
        }
    }
  }

  def sumUsingCoin(coin: Int, sum: Int): (Int, Int) = {
    println(s"Identifying coin $coin for sum $sum")
    sum / coin match {
      case 0 =>
        println("Not possible to use this coin for sum")
        (0, sum)
      case quotient =>
        println(s"$quotient coins of '$coin' can be used for sum $sum")
        (quotient, sum % coin)
    }
  }

}

