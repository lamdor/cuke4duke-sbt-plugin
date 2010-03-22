cuke4duke-sbt-plugin
==========

This is a [simple-build-tool](http://simple-build-tool.googlecode.com/) plugin for running [Cucumber](http://cukes.info) features under [cuke4duke](http://github.com/aslakhellesoy/cuke4duke)

## Usage ##
Simply run the `features` action to run all cucumber features under the `features` directory. Step definitions go in src/test/scala/. See the [cuke4duke wiki page for scala](http://wiki.github.com/aslakhellesoy/cuke4duke/scala) for more information.

The Cuke4Duke trait automatically addes the cuke4duke dependency for you. You may add junit for for asserting in your step definitions.

## Setup ##

1. Build and publish the cuke4duke-sbt-plugin project
       git clone git://github.com/rubbish/cuke4duke-sbt-plugin
       cd cuke4duke-sbt-plugin
       sbt update
       sbt publish-local

1. In your plugin definition file, add a dependency on cuke4duke-sbt-plugin

    i.e. in `project/plugins/Plugins.scala`

        import sbt._
        class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
          val cuke4duke = "cuke4duke" % "cuke4duke-sbt-plugin" % "0.2.4"
        }

2. In your project file, mixin the Cuke4Duke trait

    i.e., in `project/build/Project.scala`

         import sbt._
         import cuke4duke.sbt.Cuke4Duke
         class YourProject(info: ProjectInfo) extends DefaultProject(info) with Cuke4Duke {
           // ....
         }

3. Profit!

## Future Features ##
 * Cucumber as a TestFramework
 * Running a single feature
