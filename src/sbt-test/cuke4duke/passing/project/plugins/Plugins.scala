import sbt._
import java.io.File
import java.util.Properties

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
  val cuke4duke = "cuke4duke" % "cuke4duke-sbt-plugin" % "latest.integration"
}
