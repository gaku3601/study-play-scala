name := """study-play-scala"""
organization := "com.gaku3601"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
  jdbc,
  evolutions,
  "org.postgresql" % "postgresql" % "42.2.14",
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.gaku3601.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.gaku3601.binders._"
