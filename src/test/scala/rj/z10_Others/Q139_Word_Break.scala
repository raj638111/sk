package rj.a00_scala

import org.scalatest._


class Q139_Word_Break extends FunSuite with Matchers {

  /*
    Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

    Note:

    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.
    Example 1:

    Input: s = "leetcode", wordDict = ["leet", "code"]
    Output: true
    Explanation: Return true because "leetcode" can be segmented as "leet code".
    Example 2:

    Input: s = "applepenapple", wordDict = ["apple", "pen"]
    Output: true
    Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
                 Note that you are allowed to reuse a dictionary word.
    Example 3:

    Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
    Output: false
   */
  test("-") {
    assert(wordBreak("leetcode", List("leet", "code")) == true)
    assert(wordBreak("applepenapple", List("apple", "pen")) == true)
    assert(wordBreak("applepenap", List("apple", "pen")) == false)
  }

  /*
    This method is inefficient though. A better approach is to
    take one token from the list, Extract all the available matches
    for the token from the string..and start processing rest of
    string with remaining tokens. (Avoids unnecessary looping)
   */
  def wordBreak(s: String, wordDict: List[String]): Boolean = {
    val (result, str) = wordDict.foldLeft((true, s)){ case (bind @(result, str), key) =>
      println(s"result -> $result, str -> [$str]")
      val rgx = (s"(^.*)($key)(.*" + "$)").r
      println("rgx = " + rgx)
      result match {
        case false =>
          (false, "")
        case true =>
          str match {
            case "" => bind
            case rgx(pre, token, post) =>
              println(s"Regex match string '$str', pre+post = ${pre + post}")
              (true, pre.trim + post.trim)
            case _ =>
              println("Regex not matching to string " + str)
              (false, "")
          }
      }
    }
    result match {
      case bind @ false => bind
      case true =>
        str match {
          case "" => result
          case _ => wordBreak(str, wordDict)
        }

    }
  }



}

