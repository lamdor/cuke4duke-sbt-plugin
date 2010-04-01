import sbt._

class Cuke4DukePluginProject(info: ProjectInfo) extends PluginProject(info) with test.ScalaScripted {
  override def scriptedSbt = "0.7.2"
  override def scriptedBufferLog = false

  override def testAction = testNoScripted
  lazy val default = scripted dependsOn(publishLocal) describedAs("Publishes locally and tests against example projects")
}
