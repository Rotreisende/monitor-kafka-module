import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig}
import org.apache.kafka.common.serialization.{ByteArraySerializer, LongSerializer}
import org.apache.spark.sql.streaming.StreamingQuery
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import spark.DeequApp.{readCsv, spark, dataDf}

import java.util.Properties

object Producer {
  def prepareProducerProps(): Properties = {
    val props = new Properties
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.put(ProducerConfig.CLIENT_ID_CONFIG, "producer-playground")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[LongSerializer].getName)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[ByteArraySerializer].getName)
    props
  }

  def createProducer(): KafkaProducer[Long, Array[Byte]] = {
    new KafkaProducer[Long, Array[Byte]](prepareProducerProps())
  }

  def write(df: DataFrame): Unit = {
//    df
//      .writeStream
//      .format("kafka")
//      .option("kafka.bootstrap.servers", "localhost:9092")
//      .option("topic", "clients")
//      .start()
    df.show()
  }

  def testDataFrame(): DataFrame = {
    val sparkSession = SparkSession.builder().master("local").appName("suggestion").getOrCreate()
    readCsv("src/main/resources/data.csv")(sparkSession)
  }

  def main(args: Array[String]): Unit = {
    write(testDataFrame())
//    val producer = createProducer()
//    try {
//      val user = User(UUID.randomUUID().toString, "Ivanov", "Ivan", None, 18)
//      producer.send(new ProducerRecord[Long, Array[Byte]]("topic_1", 2, AvroSerializer.toByteArray(user)))
//      println("Send message")
//    } catch {
//      case e: Throwable => println(e.getCause)
//    }
  }
}