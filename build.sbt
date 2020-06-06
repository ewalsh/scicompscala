organization := "ai.economicdatasciences"

version := "1.0.SNAPSHOT"

sbtVersion := "0.13.17"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.1.2" % "test",
    "io.spray" %% "spray-json" % "1.3.5"
)
