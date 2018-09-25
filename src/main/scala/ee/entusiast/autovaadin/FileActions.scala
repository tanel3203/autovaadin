package ee.entusiast.autovaadin

import ee.entusiast.autovaadin.Model._
import org.json4s.DefaultFormats
import org.json4s.native.Serialization

import scala.util.{Failure, Success, Try}


object FileActions {

  def makeFiles(app: Application, relPath: String): Boolean = {

    // TODO. Generate files from prepared strings.
    app._4.foreach(singleComponent => {
      makeFile(singleComponent._1.name+".scala", singleComponent._2, relPath)
    })

    true
  }

  private def makeFile(fileName: String, fileContent: FileContent, relPath: String) = {
    java.nio.file.Files.write(
      java.nio.file.Paths.get(s"$relPath$fileName"),
      fileContent.getBytes(java.nio.charset.StandardCharsets.UTF_8)
    )

  }

  def generate[T](generator: String)(implicit manifest: Manifest[T]): T = {
    implicit val formats = DefaultFormats
    val generated = Serialization.read[T](generator)
    generated
  }

  def withAutoclosing[A, B](resource: A)(cleanup: A => Unit)(doWork: A => B): Try[B] = {
    try {
      Success(doWork(resource))
    } catch {
      case e: Exception =>
        println(e.getMessage)
        Failure(e)
    }
    finally {
      try {
        if (resource != null) {
          cleanup(resource)
        }
      } catch {
        case e: Exception => println(e.getMessage)
      }
    }
  }
}
