import sbt._
import cuke4duke.sbt.Cuke4Duke

class PassingProject(info: ProjectInfo) extends DefaultProject(info) with Cuke4Duke {
  override def jRubyHome = Path.fromFile("/tmp/jruby_home")
  val junit = "junit" % "junit" % "4.7"
}
