name := "swagger-play2"
organization := "com.apple.geo.plasma"

scalaVersion := "2.13.1"

crossScalaVersions := Seq(scalaVersion.value, "2.12.10")

val PlayVersion = "2.8.1"
val SwaggerVersion = "1.6.0"
val Specs2Version = "4.8.3"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % PlayVersion,
  "com.typesafe.play" %% "routes-compiler" % PlayVersion,
  "io.swagger" % "swagger-core" % SwaggerVersion,
  "io.swagger" %% "swagger-scala-module" % "1.0.6",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.10.2",
  "org.scala-lang.modules" %% "scala-collection-compat" % "2.1.3",
  "org.slf4j" % "slf4j-api" % "1.7.30",

  "com.typesafe.play" %% "play-ebean" % "5.0.2" % "test",
  "org.specs2" %% "specs2-core" % Specs2Version % "test",
  "org.specs2" %% "specs2-mock" % Specs2Version % "test",
  "org.specs2" %% "specs2-junit" % Specs2Version % "test",
  "org.mockito" % "mockito-core" % "3.2.0" % "test"
)

// see https://github.com/scala/bug/issues/11813
scalacOptions -= "-Wself-implicit"

scalacOptions in Test ~= filterConsoleScalacOptions

parallelExecution in Test := false // Swagger uses global state which breaks parallel tests

publishTo := sonatypePublishTo.value

publishArtifact in Test := false
pomIncludeRepository := { _ => false }
publishMavenStyle := true
releaseCrossBuild := true

import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommandAndRemaining("+publishSigned"),
  setNextVersion,
  commitNextVersion,
  releaseStepCommand("sonatypeReleaseAll"),
  pushChanges
)
