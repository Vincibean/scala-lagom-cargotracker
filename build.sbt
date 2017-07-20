organization in ThisBuild := "org.lightbend.lagom"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.7"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.2.5" % "provided"

lazy val registrationApi = project("registration-api")
  .settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      lagomScaladslApi,
      lagomJavadslApi,
      lagomJavadslJackson,
      lagomJavadslPersistence,
      lagomJavadslClient,
      lagomJavadslImmutables
    )
  )

lazy val registrationImpl = project("registration-impl")
  .enablePlugins(LagomJava)
  .settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      lagomScaladslPersistence,
      lagomScaladslPersistenceCassandra,
      lagomScaladslPubSub,
      lagomScaladslTestKit,
      lagomScaladslClient,
      lagomScaladslServer,
      lagomJavadslPersistence,
      lagomJavadslPubSub,
      lagomJavadslImmutables,
      lagomJavadslTestKit
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(registrationApi)


lazy val shippingApi = project("shipping-api")
  .settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      lagomScaladslApi,
      lagomJavadslApi,
      lagomJavadslImmutables
    )
  )

lazy val shippingImpl = project("shipping-impl")
  .enablePlugins(LagomJava)
.dependsOn(registrationApi)
  .settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      lagomScaladslPersistence,
      lagomScaladslClient,
      lagomScaladslServer,
      macwire,
      lagomJavadslPersistence,
      lagomJavadslImmutables,
      lagomJavadslTestKit
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(shippingApi)

lazy val frontEnd = project("front-end")
  .enablePlugins(PlayJava, LagomPlay)
  .settings(
    version := "1.0-SNAPSHOT",
    routesGenerator := InjectedRoutesGenerator,
    libraryDependencies ++= Seq(
      "org.webjars" % "react" % "0.14.3",
      "org.webjars" % "react-router" % "1.0.3",
      "org.webjars" % "jquery" % "2.2.0",
      "org.webjars" % "foundation" % "5.3.0"
    ),
    ReactJsKeys.sourceMapInline := true
  )

def project(id: String) = Project(id, base = file(id))
