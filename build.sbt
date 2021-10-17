val ZIOVersion = "2.0.0-M2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "functional-effects",
    organization := "net.degoes",
    scalaVersion := "2.13.6",
    Compile / console / initialCommands :=
      """|import zio._
         |import zio.Console._
         |implicit class RunSyntax[E, A](io: ZIO[ZEnv, E, A]){ def unsafeRun: A = Runtime.default.unsafeRun(io) }
    """.stripMargin
  )

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias(
  "check",
  "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck"
)

libraryDependencies ++= Seq(
  // ZIO
  "dev.zio" %% "zio"          % ZIOVersion,
  "dev.zio" %% "zio-streams"  % ZIOVersion,
  "dev.zio" %% "zio-test"     % ZIOVersion % "test",
  "dev.zio" %% "zio-test-sbt" % ZIOVersion % "test",
  // URL parsing
  "io.lemonlabs" %% "scala-uri" % "3.6.0"
)

testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))

Compile / console / scalacOptions := Seq(
  "-Ypartial-unification",
  "-language:higherKinds",
  "-language:existentials",
  "-Yno-adapted-args",
  "-Xsource:2.13",
  "-Yrepl-class-based",
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-explaintypes",
  "-Yrangepos",
  "-feature",
  "-Xfuture",
  "-unchecked",
  "-Xlint:_,-type-parameter-shadow",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-opt-warnings",
  "-Ywarn-extra-implicit",
  "-Ywarn-unused:_,imports",
  "-Ywarn-unused:imports",
  "-opt:l:inline",
  "-opt-inline-from:<source>",
  "-Ypartial-unification",
  "-Yno-adapted-args",
  "-Ywarn-inaccessible",
  "-Ywarn-infer-any",
  "-Ywarn-nullary-override",
  "-Ywarn-nullary-unit"
)
