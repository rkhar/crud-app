name := "crud-app"

version := "0.1"

scalaVersion := "2.13.8"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % "1.0.14",
  // http4s
  "org.http4s" %% "http4s-ember-client" % "0.23.11",
  "org.http4s" %% "http4s-circe" % "0.23.11",
  "org.http4s" %% "http4s-dsl" % "0.23.11"
)
