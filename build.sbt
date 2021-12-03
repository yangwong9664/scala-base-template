
name := "scala-base-template"
scalaVersion := "2.12.13"

Test / parallelExecution := false
Test / fork := true

Compile/ scalaSource := baseDirectory.value / "src"
Compile / resourceDirectory := baseDirectory.value / "conf"
Test / scalaSource := baseDirectory.value / "test"

val appDependencies = Seq(

)

val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.2.10" % "test",
  "org.scalamock" %% "scalamock" % "5.1.0" % "test"
)

val loggingDependencies = Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.7",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
  "com.lihaoyi" %% "sourcecode" % "0.2.7"
)

libraryDependencies ++= appDependencies ++ testDependencies ++ loggingDependencies

assembly / assemblyJarName := "scala-base-template.jar"

lazy val root = (project in file("."))

