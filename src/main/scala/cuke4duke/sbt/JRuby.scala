package cuke4duke.sbt

import _root_.sbt._
import java.io.File

class JRuby(val classpath: PathFinder,
            val jvmArgs: List[String],
            val home: Path,
            val gemPath: Path,
            val log: Logger) {

  def apply(args: List[String]): Int = {
    if (!home.exists) {
      home.asFile.mkdirs
    }

    Fork.java(None, javaArgs ++ args, None, jrubyEnv, LoggedOutput(log))
  }

  def installGem(gem:String) = {
    apply(List("-S", "gem", "install", "--no-ri", "--no-rdoc", "--install-dir", gemPath.absolutePath) ++ gem.split("\\s+"))
  }

  private def classpathString = (classpath.getFiles ++ FileUtilities.scalaJars).map(_.getAbsolutePath).mkString(File.pathSeparator)
  private def javaArgs = jvmArgs ++ ("-classpath" :: classpathString :: "org.jruby.Main" :: Nil)
  private def jrubyEnv = Map("GEM_PATH" -> gemPath.absolutePath,
                             "HOME"     -> home.absolutePath)
}
