import sbt._

object Dependencies {
  lazy val flink = Seq(
    "org.apache.flink" %% "flink-streaming-scala" % versions.flink,
    "org.apache.flink" %% "flink-clients" % versions.flink
  )

  lazy val spark = Seq(
    "org.apache.spark" %% "spark-streaming" % versions.spark,
    "org.apache.spark" %% "spark-core" % versions.spark,
    "org.apache.spark" %% "spark-sql" % versions.spark
  )

  lazy val avro = Seq(
    "org.apache.avro" % "avro" % versions.avro
  )

  lazy val kafka = Seq(
    "org.apache.kafka" % "kafka-clients" % versions.kafka
  )

  lazy val deequ = Seq(
    "com.amazon.deequ" % "deequ" % versions.deequ
  )

  lazy val jackson = Seq(
    "com.fasterxml.jackson.module" %% "jackson-module-scala" % versions.jackson
  )

  lazy val flinkConnectorToKafka = Seq(
    "org.apache.flink" %% "flink-connector-kafka" % versions.flink_connector,
  )

  lazy val sparkSet: Seq[ModuleID] =
    avro ++ kafka ++ spark ++ deequ ++ jackson


  object versions {
    val flink = "1.13.2"
    val flink_connector = "1.13.1"
    val spark = "3.1.2"
    val avro = "1.10.2"
    val kafka = "2.8.0"
    val deequ = "1.2.2-spark-3.0"
    val jackson = "2.12.2"
  }
}
