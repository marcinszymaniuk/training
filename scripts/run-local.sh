export SPARK_HISTORY_OPTS="-Dspark.history.fs.logDirectory=$TRAINING_HOME/spark_history"

echo "STARTING Standalone Spark from $TRAINING_HOME"
$TRAINING_HOME/spark-2.0.0-bin-hadoop2.7/sbin/start-master.sh
$TRAINING_HOME/spark-2.0.0-bin-hadoop2.7/sbin/start-slave.sh spark://marcin-VirtualBox:7077
$TRAINING_HOME//spark-2.0.0-bin-hadoop2.7/sbin/start-history-server.sh



