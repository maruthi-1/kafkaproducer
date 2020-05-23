name := "Myproducer"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.10.2"
libraryDependencies += "org.apache.flink" %% "flink-connector-kafka" % "1.10.0"