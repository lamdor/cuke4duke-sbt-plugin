package cuke4duke.sbt

import _root_.sbt._
import java.io.File

trait Cuke4Duke extends BasicManagedProject with ScalaPaths {
  def cuke4DukeVersion = "0.1.8"
  def cucumberGem = "cucumber --version 0.4.3 --source http://gems.rubyforge.org/"

  val cuke4DukeRepo = "Cuke4Duke Maven Repository" at "http://cukes.info/maven"
  val cuke4DukeDependency = "cuke4duke" % "cuke4duke" % cuke4DukeVersion % "test"
  val picoContainerDependency = "org.picocontainer" % "picocontainer" % "2.8.3" % "test"

  val cuke4DukeJvmArgs = List("-Dcuke4duke.objectFactory=cuke4duke.internal.jvmclass.PicoFactory")

  def jRubyHome = info.projectPath / "lib_managed" / "cuke4duke_gems"
  lazy val gemPath = jRubyHome / "gems"
  lazy val cucumberBin = gemPath / "bin" / "cucumber"

  def featuresDirectory = info.projectPath / "features"

  private def jruby(args: List[String]): Int = {
    if (!jRubyHome.exists) {
      jRubyHome.asFile.mkdirs
    }

    val jrubyClasspath = fullClasspath(Configurations.Test)
    val classpathArg = (jrubyClasspath.getFiles ++ FileUtilities.scalaJars).map(_.getAbsolutePath).mkString(File.pathSeparator)
    val javaArgs = cuke4DukeJvmArgs ++ ("-classpath" :: classpathArg :: "org.jruby.Main" :: args.toList)
    val env = Map("GEM_PATH" -> gemPath.absolutePath,
                  "HOME"     -> jRubyHome.absolutePath,
                  "JRUBY_PARENT_CLASSPATH" -> classpathArg)

    println("javaArgs:\n\t" + javaArgs.mkString("\n\t"))

    Fork.java(None, javaArgs, None, env, StdoutOutput)
  }

  def cucumberGemInstalled() = false

  def installCucumberGem() {
    log.info("Installing cucumber gem...")
    jruby(List("-S", "gem", "install", "--no-ri", "--no-rdoc", "--install-dir", gemPath.absolutePath) ++ cucumberGem.split("\\s+"))
  }

  lazy val updateNoInstallCucumberGem = super.updateAction
  lazy val updateGems = task { installCucumberGem; None }
  override def updateAction = updateGems dependsOn(updateNoInstallCucumberGem)


  def runCucumberFeatures() = {
     jruby(List("-r", "cuke4duke",
                cucumberBin.absolutePath,
                featuresDirectory.absolutePath,
                "--require", testCompilePath.absolutePath,
                "--color"))
  }

  lazy val features = task {
    runCucumberFeatures match {
      case 0 => None
      case i => Some("Cucumber features failed! - Exit Code: " + i)
    }
  }
}
