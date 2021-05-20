
name := "scala-api-template"
version := "0.1"
scalaVersion := "2.13.5"

Test / parallelExecution := false
Test / fork := true
IntegrationTest / parallelExecution := false
IntegrationTest / fork := true

coverageEnabled := true
coverageMinimumStmtTotal := 1
coverageFailOnMinimum := true
coverageHighlighting := true

scalaSource in Compile := baseDirectory.value / "src"
resourceDirectory in Compile := baseDirectory.value / "conf"
scalaSource in Test := baseDirectory.value / "test"
scalaSource in IntegrationTest := baseDirectory.value / "it"

val AkkaVersion = "2.6.8"
val AkkaHttpVersion = "10.2.4"

val appDependencies = Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.3",
  "com.lihaoyi" %% "sourcecode" % "0.2.6",
  "net.codingwell" %% "scala-guice" % "5.0.0"
)

val testDependencies = Seq(
  "org.scalactic" %% "scalactic" % "3.2.9",
  "org.scalatest" %% "scalatest" % "3.2.9" % "test, it",
  "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % "test, it",
  "com.typesafe.akka" %% "akka-http-testkit" % AkkaHttpVersion % "test, it",
  "com.github.tomakehurst" % "wiremock" % "2.27.2" % "it",
  "org.scalamock" %% "scalamock" % "5.1.0" % "test"
)

libraryDependencies ++= appDependencies ++ testDependencies

assemblyJarName in assembly := "scala-api-template.jar"

lazy val root = (project in file("."))
  .configs(IntegrationTest)
  .settings(
    Defaults.itSettings
  )
