package cuke4duke.sbt

import _root_.sbt._
import java.io.File

class JRuby(val classpath: PathFinder,
            val jvmArgs: List[String],
            val home: Path,
            val gemPath: Path,
            val log: Logger) {

  if (!home.exists) {
    home.asFile.mkdirs
  }

  def apply(args: List[String]): Int = {
    val classpathArg = (classpath.getFiles ++ FileUtilities.scalaJars).map(_.getAbsolutePath).mkString(File.pathSeparator)
    val javaArgs = jvmArgs ++ ("-classpath" :: classpathArg :: "org.jruby.Main" :: args.toList)
    val env = Map("GEM_PATH" -> gemPath.absolutePath,
                  "HOME"     -> home.absolutePath)

    log.debug("Cuke4Duke jvmArgs:\n\t" + javaArgs.mkString("\n\t"))

    Fork.java(None, javaArgs, None, env, LoggedOutput(log))
  }

  def installGem(gem:String) = {
    apply(List("-S", "gem", "install", "--no-ri", "--no-rdoc", "--install-dir", gemPath.absolutePath) ++ gem.split("\\s+"))
  }
}
