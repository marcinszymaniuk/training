package com.tantusdata.training.first

import com.tantusdata.training.WordCount
import org.scalatest.{FunSuite, Matchers}

class WordCountTest extends FunSuite with Matchers with SparkTest {

  test("simple count"){
    val givenRdd = sc.textFile("src/test/resources/wc_input")
    val counterRdd = WordCount.wordCount(givenRdd)
    val counters = counterRdd.collect()

    //transforms List[( String,Int )] to Map[String, List]
    val resultMap = counters.groupBy(_._1).map { case (k, v) => (k, v.map(_._2).head) }

    resultMap.keys.size shouldBe 2
    resultMap.get("one") shouldBe 4
    resultMap.get("two") shouldBe 2
  }

}
