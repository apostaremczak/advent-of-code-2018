name := "advent-of-code"

version := "0.1"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.10.1",
  "org.joda" % "joda-convert" % "2.1.2"
)

resolvers ++= Seq(
  Resolver.mavenLocal
)
