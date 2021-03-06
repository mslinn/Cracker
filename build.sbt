organization := "com.micronautics"

name := "cracker"

version := "0.1.3"

scalaVersion := "2.12.4"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-target:jvm-1.8",
  "-unchecked",
  "-Ywarn-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-Xlint"
)

scalacOptions in (Compile, doc) ++= baseDirectory.map {
  (bd: File) => Seq[String](
     "-sourcepath", bd.getAbsolutePath,
     "-doc-source-url", "https://github.com/mslinn/cracker/tree/master€{FILE_PATH}.scala"
  )
}.value

javacOptions ++= Seq(
  "-Xlint:deprecation",
  "-Xlint:unchecked",
  "-source", "1.8",
  "-target", "1.8",
  "-g:vars"
)

libraryDependencies ++= Seq(
  "com.amazonaws"      %  "aws-java-sdk-osgi" % "1.11.119" withSources(),
  "commons-io"         %  "commons-io"        % "2.5"      withSources(),
  "org.scalatest"      %% "scalatest"         % "3.0.1"    % "test" withSources(),
  "junit"              %  "junit"             % "4.12"     % "test"
)

assemblyMergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf")          => MergeStrategy.discard
  case m if m.toLowerCase.matches("meta-inf.*\\.sf$")      => MergeStrategy.discard
  case _                                                   => MergeStrategy.first
}

logLevel := Level.Warn

// Only show warnings and errors on the screen for compilations.
// This applies to both test:compile and compile and is Info by default
logLevel in compile := Level.Warn

// Level.INFO is needed to see detailed output when running tests
logLevel in test := Level.Info

// define the statements initially evaluated when entering 'console', 'console-quick', but not 'console-project'
initialCommands in console := """import com.amazonaws.services.polly._
                                |import com.amazonaws.services.polly.model._
                                |import com.micronautics.options._
                                |import java.io.{BufferedInputStream, File, FileOutputStream, InputStream}
                                |""".stripMargin

cancelable := true
