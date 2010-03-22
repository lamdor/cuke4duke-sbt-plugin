import sbt._

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
  val scriptedDep = "org.scala-tools.sbt" % "scripted" % "0.7.0"
  val databinder_repo = "Databinder Repository" at "http://databinder.net/repo"
}
