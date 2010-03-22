import sbt._

class Cuke4DukePluginProject(info: ProjectInfo) extends PluginProject(info) with test.ScalaScripted {
  override def scriptedSbt = "0.7.1"
  override def scriptedBufferLog = false

  override def testAction = testNoScripted
}
