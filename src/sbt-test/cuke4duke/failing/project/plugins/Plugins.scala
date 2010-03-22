import sbt._

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
  val cuke4duke = "cuke4duke" % "cuke4duke-sbt-plugin" % "0.1.10-SNAPSHOT"
}
