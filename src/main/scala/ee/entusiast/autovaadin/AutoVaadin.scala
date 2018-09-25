package ee.entusiast.autovaadin

import ee.entusiast.autovaadin.FileActions._
import ee.entusiast.autovaadin.Model._
import ee.entusiast.autovaadin.generators._

object AutoVaadin {

  def create(generatorInput: String, relPathFromCurrentDirectory: String) = {
    val relPath = relPathFromCurrentDirectory
    val packageName = (for {
      prepared: String <- Some(relPath.split("src/main/scala/").last.replace("/", "."))
      trimmed <- Some(if (prepared.last.equals(".".charAt(0))) prepared.substring(0, prepared.length-1) else prepared)
    } yield trimmed).getOrElse(relPath)

    val generator = generate[Generator](generatorInput)
    val app = createApplication(generator, packageName)
    val fileCreationSuccessful = makeFiles(app, relPath)

    if (fileCreationSuccessful) LOG.info(s"Success!")
    else LOG.warn("File creation failed!")
  }

  private def createApplication(generator: Generator, packageName: String): Application = {

    val service: Service =                      ServiceGenerator.create()
    val userActionService: UserActionService =  UserActionServiceGenerator.create()
    val view: View =                            ViewGenerator.create()
    val components: Components =                ComponentsGenerator.create(generator, packageName)
    LOG.info(s"AutoVaadin.createApplication received components: $components")

    (service, userActionService, view, components)
  }


}
