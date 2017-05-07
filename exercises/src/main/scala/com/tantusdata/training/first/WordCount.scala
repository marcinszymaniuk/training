package com.tantusdata.training.first

import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]) {
    val conf = new SparkConf()
      .setAppName("WordCount")
      .setMaster("local[2]")
    val spark = new SparkContext(conf)
    val output: String = "/home/marcin/spark-training/exercises/output/1/"
    val fileSystem = FileSystem.get(spark.hadoopConfiguration)
    fileSystem.delete(new Path(output), true)

    val inputRdd = spark.textFile("/home/marcin/spark-training/exercises/input/1")

    val countersRdd = wordCount(inputRdd)

    countersRdd.saveAsTextFile(output)
  }

  def wordCount(inputRdd: RDD[String]) = {
    val wordsRdd = inputRdd
    .flatMap(line=>line.split(" "))
    /*
    TODO: transform inputRdd(containing lines from input file) to rdd containing words.
    TODO     hint: str.split(" ") function
    TODO     hint: use flatMap operation
     */
    wordsRdd
      /*
      TODO: use map function to transform word into tuple of word and a counter initialized with value 1
      */
      .map(word => (word, 1))

      .groupByKey()
      .mapValues(x => x.foldLeft(0)(_ + _))

  }
}
