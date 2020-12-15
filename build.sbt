ThisBuild / scalaVersion := "2.12.12"
ThisBuild / organization := "advent2015"

lazy val hello = (project in file("."))
  .settings(
    name := "Advent2015",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.2",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % Test,
  )
