import sbt._

class Cuke4DukePluginProject(info: ProjectInfo) extends PluginProject(info) with test.ScalaScripted {
  val ivy = "org.apache.ivy" % "ivy" % "2.0.0"
}
