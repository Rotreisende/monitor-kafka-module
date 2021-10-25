name := "monitor-kafka"

version := "0.1"

scalaVersion := "2.12.0"

import Dependencies._
libraryDependencies ++= sparkSet ++ myAvro ++ kafkaSparkConnector