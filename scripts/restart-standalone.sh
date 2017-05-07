
ps aux| grep Master | grep spark | grep 8080 | awk '{print $2}' | xargs kill
ps aux| grep Worker | grep spark | awk '{print $2}' | xargs kill
ps aux| grep HistoryServer | grep spark | awk '{print $2}' | xargs kill
 
sleep 1

export SPARK_HISTORY_OPTS="-Dspark.history.fs.logDirectory=$TRAINING_HOME/spark_history"

echo "STARTING Standalone Spark from $TRAINING_HOME"
$TRAINING_HOME/spark-2.0.0-bin-hadoop2.7/sbin/start-master.sh
$TRAINING_HOME/spark-2.0.0-bin-hadoop2.7/sbin/start-slave.sh spark://marcin-VirtualBox:7077
$TRAINING_HOME//spark-2.0.0-bin-hadoop2.7/sbin/start-history-server.sh
