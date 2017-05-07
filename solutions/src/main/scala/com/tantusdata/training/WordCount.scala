package com.tantusdata.training

import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]) {

    val conf = new SparkConf()
      .setAppName("Demo")
      .setMaster("local[3]")
    val spark = new SparkContext(conf)
    val inputRdd = spark.textFile("/home/marcin/spark-training/exercises/input/1")
    val output: String = "/home/marcin/spark-training/exercises/output/1/"
    val fileSystem = FileSystem.get(spark.hadoopConfiguration)
    fileSystem.delete(new Path(output), true)

    val words = inputRdd.flatMap(line => line.split(" "))

    println(inputRdd.toDebugString)
    println(s"inputRdd partitions: ${inputRdd.partitions.size}")

    println(words.toDebugString)
    println(s"words partitions: ${words.partitions.size}")

    words.map(word => (word, 1))
      .reduceByKey { case (x, y) => x + y }
      .map { case (line, counter) => (counter, line) }
      .saveAsTextFile(output)
  }

}
