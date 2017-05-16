    rm -rf $TRAINING_HOME/exercises/build/libs

    gradle clean build
    spark-submit  --master spark://student-VirtualBox:7077 --conf "spark.driver.extraJavaOptions=-Dlog4j.configuration=jar:file:$TRAINING_HOME/exercises/build/libs/exercises.jar!/log4j-driver.properties" --executor-memory 1G --conf spark.eventLog.dir=file:/home/marcin/spark-training/spark_history --conf spark.eventLog.enabled=true --class com.tantusdata.training.$1.Main $TRAINING_HOME/exercises/build/libs/exercises.jar