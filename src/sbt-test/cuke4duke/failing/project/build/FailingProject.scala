import sbt._
import cuke4duke.sbt.Cuke4Duke

class FailingProject(info: ProjectInfo) extends DefaultProject(info) with Cuke4Duke {
  override def jRubyHome = Path.fromFile("/tmp/jruby_home") // necessary to deal with weird scripted/jruby/cucumber path error on MacOSX

  val junit = "junit" % "junit" % "4.7"
}
