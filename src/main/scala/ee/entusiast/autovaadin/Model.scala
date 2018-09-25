package ee.entusiast.autovaadin

object Model {

  case class Generator( typ: String,
                        name: String,
                        components: Option[Seq[Generator]] = None,
                        actions: Option[Seq[String]] = None
                      )
}
