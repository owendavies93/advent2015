ThisBuild / scalaVersion := "2.12.12"
ThisBuild / organization := "advent2015"

lazy val hello = (project in file("."))
  .settings(
    name := "Advent2015"
  )
