name := "scala-base-template"

version := "0.1"

scalaVersion := "2.13.5"

scalaSource in IntegrationTest := baseDirectory.value / "it"

parallelExecution in IntegrationTest := false
parallelExecution in Test := false

fork in Test := true
fork in IntegrationTest := true

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.2.7",
  "org.scalatest" %% "scalatest" % "3.2.7" % "test"
)

assemblyJarName in assembly := "scala-base-template.jar"
