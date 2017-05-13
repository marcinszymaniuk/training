package com.tantusdata.training.second

import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object Main {

  def main(args: Array[String]) {

    SparkSession
    val conf = new SparkConf()
      .setAppName("DataSet Api")
      .setMaster("local[2]")
    val spark = new SparkContext(conf)
    val output: String = "/home/marcin/spark-training/exercises/output/2/"
    val fileSystem = FileSystem.get(spark.hadoopConfiguration)
    fileSystem.delete(new Path(output), true)

//    val inputRdd = spark.rea("/home/marcin/spark-training/exercises/input/2")

  }



}
