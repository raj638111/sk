name := "sk"

scalaVersion := "2.11.8"

//scalacOptions += "-target:jvm-1.8"

libraryDependencies ++= Seq(
	//"org.apache.spark" %% "spark-core" % "2.3.4" % "provided",
	"org.apache.spark" %% "spark-core" % "2.3.4",
	"org.apache.spark" %% "spark-sql" % "2.3.4",
	//"org.apache.spark" %% "spark-sql" % "2.3.4" % "provided",
	"org.apache.spark" %% "spark-hive" % "2.3.4",
	//"org.apache.spark" %% "spark-hive" % "2.3.4" % "provided",
  "org.apache.kafka" % "kafka-clients" % "2.3.0",
  "org.scalatest" %% "scalatest" % "3.0.4" % "test",
  "com.github.scopt" %% "scopt" % "3.2.0"
)



