package spark

import CheckBuilder._
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import util.User
import validate.Validator.Instrument

object DeequApp extends App {
  implicit val spark: SparkSession =
    SparkSession.builder()
      .master("local")
      .appName("suggestion")
      .getOrCreate()

  def readCsv(path: String)(implicit spark: SparkSession): DataFrame = {
    spark.read
      .option("header", value = true)
      .option("sep", ",")
      .option("quote", "\"")
      .csv(path)
  }

  val errorsDf = readCsv("src/main/resources/errors.csv")
  val dataDf = readCsv("src/main/resources/data.csv")

  val result = dataDf.getCheckResult(errorsDf)

  val filterUsers = dataDf.collect().filter(row => Instrument(errorsDf).validate(row))

  val usersRdd = spark.sparkContext.parallelize(filterUsers)
  val usersDf = spark.createDataFrame(usersRdd, dataDf.schema)

  import spark.implicits._
  val usersDataset = usersDf.as[User]

  val schemaString = StructType(Seq(
    StructField("key", StringType),
    StructField("value", StringType)
  ))

  val stringRdd = spark.sparkContext.parallelize(Seq(Row("1", "qwe"), Row("2", "rty")))
  val dataFrame = spark.createDataFrame(stringRdd, schemaString)
  dataFrame
    .write
    .format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("topic", "topic-string")
    .option("group.id", "string")
    .save()
}