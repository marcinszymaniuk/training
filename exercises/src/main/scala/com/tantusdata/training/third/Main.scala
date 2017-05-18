package com.tantusdata.training.third

import com.tantusdata.training.second.Event
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}


object Main {

  def main(args: Array[String]) {
    val session = SparkSession
      .builder().master("local[2]")
      .appName("SQL aggregation").getOrCreate()
    import session.implicits._

    val spark = session.sparkContext
    val output: String = "/home/marcin/spark-training-repo/exercises/output/3"
    val fileSystem = FileSystem.get(spark.hadoopConfiguration)
    fileSystem.delete(new Path(output), true)

    val eventsRdd =
      session.read.json("/home/marcin/spark-training/exercises/input/2/typed").as[Event].rdd

    val usersRDD = spark.textFile("/home/marcin/spark-training/exercises/input/3/users")

    //TODO : get familiar with input files
    //TODO: get familiar with content of both RDDS
    //TODO: We want to know what operating system users use. We want a return like this: (userId, OS)
    //TODO: hint: transform the rdds to make more sense so you are able to group both by key and join

  }

  case class UserEvent(username: String, os:String)
  def joinUsersAndEvents(usersRDD: RDD[String], eventsRDD: RDD[Event]): RDD[UserEvent] = {
    usersRDD.sparkContext.parallelize(List())

  }

}
