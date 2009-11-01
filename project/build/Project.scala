import sbt._

class Cuke4DukeProject(info: ProjectInfo) extends DefaultProject(info) with test.ScalaScripted {
  val ivy = "org.apache.ivy" % "ivy" % "2.0.0"
}
