scalaVersion := "2.11.12"

scalacOptions := Seq("-unchecked", "-deprecation")

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("releases")
)

libraryDependencies += "edu.berkeley.cs" %% "chisel3" % "3.1.+"
libraryDependencies += "edu.berkeley.cs" %% "chisel-iotesters" % "1.2.+"
