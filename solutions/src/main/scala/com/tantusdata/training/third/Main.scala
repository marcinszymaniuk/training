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
    val output: String = "/home/marcin/spark-training-repo/training/exercises/output/3"
    val fileSystem = FileSystem.get(spark.hadoopConfiguration)
    fileSystem.delete(new Path(output), true)

    val eventsRdd =
      session.read.json("/home/marcin/spark-training-repo/training/solutions/input/2/typed").as[Event].rdd

    val usersRDD = spark.textFile("/home/marcin/spark-training-repo/training/exercises/input/3/users")

    val joined = joinUsersAndEvents(usersRDD, eventsRdd)

    joined.collect().foreach(println)
    joined.saveAsTextFile(output)
  }

  case class UserEvent(username: String, os:String)
  def joinUsersAndEvents(usersRDD: RDD[String], eventsRDD: RDD[Event]): RDD[UserEvent] = {



    val userIdToOsRDD = eventsRDD.map(e=>(e.userID.toInt, e.operatingSystem))
    val userIdToUserNameRDD =usersRDD.map(x=>(x.split(" ")(0).toInt,x.split(" ")(1)))
    userIdToUserNameRDD
      .join(userIdToOsRDD)
       .map{case(userId:Int,(username:String, os:String))=>UserEvent(username, os)}

  }

}
