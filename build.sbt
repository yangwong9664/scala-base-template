
name := "scala-api-template"
scalaVersion := "2.12.4"

Test / parallelExecution := false
Test / fork := true

Compile/ scalaSource := baseDirectory.value / "src"
Compile / resourceDirectory := baseDirectory.value / "conf"
Test / scalaSource := baseDirectory.value / "test"

val appDependencies = Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.3",
  "com.lihaoyi" %% "sourcecode" % "0.2.6",
  "net.codingwell" %% "scala-guice" % "5.0.0"
)

val testDependencies = Seq(
  "org.scalactic" %% "scalactic" % "3.2.9",
  "org.scalatest" %% "scalatest" % "3.2.9" % "test",
  "org.scalamock" %% "scalamock" % "5.1.0" % "test"
)

libraryDependencies ++= appDependencies ++ testDependencies

assembly / assemblyJarName := "scala-base-template.jar"

lazy val root = (project in file("."))
  .configs(IntegrationTest)
  .settings(
    Defaults.itSettings
  )
