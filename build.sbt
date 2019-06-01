enablePlugins(ScalaJSPlugin)

organization := "com.payalabs"
name := "use-slinky-hooks"
version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.8"

scalacOptions := Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies ++= {
  val scalaJsDomVersion = "0.9.7"
  val slinkyVersion = "0.6.1"
  
  Seq(
    "org.scala-js" %%% "scalajs-dom" % scalaJsDomVersion % Provided,
    "me.shadaj" %%% "slinky-web" % slinkyVersion % Provided
  )
}

licenses := Seq("The MIT License (MIT)" -> url("https://opensource.org/licenses/MIT"))
homepage := Some(url("https://github.com/payalabs/use-slinky-hooks"))
scmInfo := Some(
  ScmInfo(
    url("https://github.com/payalabs/use-slinky-hooks"),
    "scm:git@github.com:payalabs/use-slinky-hooks.git"
  )
)
developers := List(
  Developer(
    id    = "kladdad",
    name  = "Kavita Laddad",
    email = "",
    url   = url("https://github.com/payalabs")
  )
)
