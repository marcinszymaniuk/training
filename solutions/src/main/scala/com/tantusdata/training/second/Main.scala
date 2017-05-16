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

    val dataSet = session.read.json("/home/marcin/spark-training-repo/training/solutions/input/2/typed")

    val res0 = dataSet.groupBy("operatingSystem").count.collect()
    val res01 = dataSet.selectExpr("*").where("operatingSystem is null").collect()
    val res1 = dataSet.groupBy("operatingSystem").max("xCoord").collect()
    val res2 = dataSet.groupBy("operatingSystem").mean("xCoord").collect()
    val res3 = dataSet.groupBy("operatingSystem").count().collect()

    println("XXX")
    res01.foreach(println)
//    res2.foreach(println)
//    res3.foreach(println)
//    dataSet.select("operatingSystem").collect().foreach(println)
//    val res = dataSet.rdd
//      .map(
//        x=>Event(
//          x.country
//          x.getAs[String]("eventTime")
//        )
//      )
//      .toDS()
//      .groupBy("country")
//    .count()

//    res.collect().map(x=>x.getValuesMap())

    print (s"Result: ${}")
    //    val inputRdd = spark.rea("/home/marcin/spark-training/exercises/input/2")

  }



}
