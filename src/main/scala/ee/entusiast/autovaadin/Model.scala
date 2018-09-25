package ee.entusiast.autovaadin

object Model {

  type FileContent = String

  type Service = FileContent // data inputs and destinations
  type UserActionService = FileContent // any actions that user makes, logic is sent here
  type View = FileContent // this contains the composition of layouts, etc and providers the composed piece

  type SingleComponent = (Generator, FileContent)
  type Components = Seq[SingleComponent] // these are the layouts, labels, etc that contain eachother

  type Application = (Service, UserActionService, View, Components)

  case class Generator(typ: String,
                       name: String,
                       components: Option[Seq[Generator]] = None,
                       actions: Option[Seq[String]] = None)
}
