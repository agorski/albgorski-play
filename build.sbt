name := """/Users/albert.gorski/projects-scala/albgorski-play"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  filters,
  jdbc,
  cache,
  ws,
  "org.scalatest" %% "scalatest" % "2.2.0" % "test",
  "org.scalatestplus" %% "play" % "1.4.0-M3" % "test"
)

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
