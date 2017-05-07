package com.tantusdata.training.first

import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfter, Suite}

trait SparkTest extends Suite with BeforeAndAfter {
  implicit var sc: SparkContext = _
  var conf: SparkConf = _

  before {
    conf = new SparkConf().set("spark.driver.allowMultipleContexts", "true")
    sc = new SparkContext("local[2]", "sc", conf)
  }

  after {
    sc.stop()
  }
}
