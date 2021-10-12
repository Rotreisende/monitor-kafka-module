package scala.spark

import CheckBuilder._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame

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

  //dataDf.foreach(row => println(Instrument(errorsDf).validate(row)))// not work
  //println(Instrument(errorsDf).validate(dataDf.collect().head))// work
}