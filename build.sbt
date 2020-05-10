name := "swagger-play2"
organization := "com.chilipiper"

scalaVersion := "2.13.2"

crossScalaVersions := Seq(scalaVersion.value, "2.12.10")

val PlayVersion = "2.7.3"
val SwaggerVersion = "1.5.24"
val Specs2Version = "4.6.0"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % PlayVersion,
  "com.typesafe.play" %% "routes-compiler" % PlayVersion,
  "io.swagger" % "swagger-core" % SwaggerVersion,
  "io.swagger" %% "swagger-scala-module" % "1.0.6",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.9",
  "org.scala-lang.modules" %% "scala-collection-compat" % "2.1.2",
  "org.slf4j" % "slf4j-api" % "1.7.21",

  "com.typesafe.play" %% "play-ebean" % "5.0.2" % "test",
  "org.specs2" %% "specs2-core" % Specs2Version % "test",
  "org.specs2" %% "specs2-mock" % Specs2Version % "test",
  "org.specs2" %% "specs2-junit" % Specs2Version % "test",
  "org.mockito" % "mockito-core" % "2.21.0" % "test"
)

// see https://github.com/scala/bug/issues/11813
scalacOptions -= "-Wself-implicit"

scalacOptions in Test ~= filterConsoleScalacOptions

parallelExecution in Test := false // Swagger uses global state which breaks parallel tests

publishArtifact in Test := false
pomIncludeRepository := { _ => false }
publishMavenStyle := true
releaseCrossBuild := true

import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._

bintrayRepository := "swagger-play2"
bintrayOrganization := Some("chili-piper")
bintrayReleaseOnPublish := true
licenses := Seq("MIT" -> url("http://opensource.org/licenses/MIT"))
