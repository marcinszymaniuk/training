package com.tantusdata.training

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

  def wordCount(inputRdd: RDD[String]): RDD[(String,Int)] = {


    inputRdd.take(5).foreach(println)

    inputRdd
      .flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .groupByKey()
      .mapValues(counters=>counters.sum)

    //BONUS solution: replace last two lines with:
    //.reduceByKey{case (counter1, counter2) => counter1+counter2 }

  }

}
