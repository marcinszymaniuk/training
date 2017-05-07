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

  def wordCount(inputRdd: RDD[String]): RDD[(String, Int)] = {
    /*
    TODO: Whenever you are not sure what and RDD contains you can materialize
    TODO: it and print it's value by calling:
    TODO: .collect.foreach(println)    -   prints all elements
    TODO: or .take(5).foreach(println)  -  prints 5 elements
    TODO: In order to get familiar with it try: wordsRdd.take(5).foreach(println)
    */


    /*
    TODO 1:  transform inputRdd(containing lines from input file) to rdd containing words.
    TODO 1   HINT: split function from String
    TODO 1   HINT: use flatMap operation
     */
    /*
    TODO 2: use map function to transform word into tuple of key:word and a value:counter initialized with value 1
    */

    /*
    TODO 3: group same words together
    TODO 3:   HINT: use groupByKey
    */

    /*
    TODO 4: After groupping by key you have an RDD with tuples: (key: String, value: Iterable[Int])
    TODO 4: You need to find a sum of the value collection per key
    TODO 4: HINT: Use mapValues function
    TODO 4: HINT: use Iterable.sum function
    */

    /*
    TODO Bonus: instead of groupping and then summing use reduceByKey function
     */

    /*
    TODO 5: return the result rdd you defined -
    TODO 5: right now we just return dummy one to avoid compilation failures
     */
    inputRdd.sparkContext.parallelize(List(("dummy",-1)))
  }
}
