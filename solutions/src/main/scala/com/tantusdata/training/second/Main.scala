package com.tantusdata.training.second

import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext._
object Main {

  def main(args: Array[String]) {

   val session = SparkSession

      .builder().master("local[2]")
    .appName("SQL aggregation").getOrCreate()
    import session.implicits._
//    val conf = new SparkConf()
//      .setAppName("DataSet Api")
//      .setMaster("local[2]")
//    val spark = new SparkContext(conf)
    val output: String = "/home/marcin/spark-training/exercises/output/2/"
    val fileSystem = FileSystem.get(session.sparkContext.hadoopConfiguration)
    fileSystem.delete(new Path(output), true)

    val dataSet = session.read.json("/home/marcin/spark-training/exercises/input/2")
//      .as[Event]
    dataSet.select("a").collect().foreach(println)
    //    val inputRdd = spark.rea("/home/marcin/spark-training/exercises/input/2")

  }



}
