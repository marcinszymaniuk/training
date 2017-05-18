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

    //TODo : get familiar with input file
    val dataSet = session.read.json("/home/marcin/spark-training-repo/training/solutions/input/2/typed")


    //TODO: count how many distinct operation systems we have
    val distinctOs = dataSet.select("operatingSystem").distinct.count
    print (s"Number of distinct operating systems: $distinctOs \n" )

    //TODO: Does it match your expectations? No? Do you see why?



    //TODO: seems that we have too many operating systems
    //TODO :comparing to what we see in input file
    //TODO : let's count them by OS type
    val countPerOs = dataSet.groupBy("operatingSystem").count.collect()
    println("Count per os:")
    countPerOs.foreach(println)
    //tODO: Can you see null os? Do you know why?

    //TODO: ok let's see the record with null OS
    //TODO: find a record which has operatingSystem set to null
    val rowWithNullOs = dataSet.selectExpr("*").where("operatingSystem is null").collect()
    println("Problematic row:")
    rowWithNullOs.foreach(println)
    println(rowWithNullOs.size)



    //TODO: So you see the row, do you know why there are that many nulls?
    //TODO: Hint: You have assumed that I'm nice to you right? But nah!
    //TODO: Hint Something is wrong with the file :)

    //TODO: If you give up just ask, I will tell you :)

    //TODO: ok we have that fixed, lets do some aggregation
    //TODO: what is the max xCoord by operating system
    val maxCoordPerOS = dataSet.groupBy("operatingSystem").max("xCoord").collect()

    println("Max xCoord per OS:")
    maxCoordPerOS.foreach(println)

    //TODO: what is the avg xCoord by operating system
    val avgXcoordPerOs = dataSet.groupBy("operatingSystem").avg("xCoord").collect()

    println("Avg xCoord per OS:")
    avgXcoordPerOs.foreach(println)


    // TODO: Strings are not that nice, what about some data model?
    // TODO: get familiar with Event class in com.tantusdata.training.second.Event
    // TODO: let's read our datset as collection of events
    // todo: use .as[Event]
    val eventDataSet = session.read.json("/home/marcin/spark-training-repo/training/solutions/input/2/typed").as[Event]

    //todo: filter ios events
    val iosEvents = eventDataSet.filter(event=>event.operatingSystem.toLowerCase().equals("ios")).collect
    println("ios events:")
    iosEvents.foreach(println)

    val selected1 = eventDataSet.select("userId","operatingSystem" ,"xCoord", "yCoord")
    val joinedByOS = selected1.join(selected1,"operatingSystem" )
    println ("joined by OS:")
    joinedByOS.join(selected1,"operatingSystem" ).collect().foreach(println)

//    case class Joined(os:String, userId1:String, xCoord1:Double, yCoord1:Double, userId2:String, xCoord2:String, yCoord2:String)
//    val joinedTyped = joinedByOS.as[Joined]

//    def calculateDistance()
//    joinedTyped.filter(joined => joined.userId1!=joined.userId2 && )
    //    val res3 = dataSet.groupBy("operatingSystem").count().collect()
//    println("XXX")
//    res01.foreach(println)
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

//    print (s"Result: ${}")
    //    val inputRdd = spark.rea("/home/marcin/spark-training/exercises/input/2")

  }



}
