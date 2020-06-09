organization := "ai.economicdatasciences"

version := "1.0.SNAPSHOT"

sbtVersion := "0.13.17"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.1.2" % "test",
    "io.spray" %% "spray-json" % "1.3.5",
    "io.argonaut" %% "argonaut" % "6.3.0",
    "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
    "com.typesafe.slick" %% "slick" % "3.3.1",
    "com.typesafe.slick" %% "slick-hikaricp" % "3.3.1",
    "org.slf4j" % "slf4j-nop" % "1.7.26"
)
