cuke4duke-sbt-plugin
==========

### Setup ###
in `project/plugins/Plugins.scala`
    import sbt._

    class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
      val cuke4duke = "cuke4duke" % "cuke4duke-sbt-plugin" % "0.1.alpha"
    }

in `project/build/Project.scala`
    import sbt._
    import cuke4duke.sbt.Cuke4Duke

    class YourProject(info: ProjectInfo) extends DefaultProject(info) with Cuke4Duke {
      // ....
    }

