package ee.entusiast.autovaadin.templates

import ee.entusiast.autovaadin.Model._

object LabelTemplate {

  import TemplateUtils._

  def create(clazzName: String, componentType: String, paramsGenerators: Seq[Generator], actions: Seq[String])(implicit packageName: String): String =
    s"""
       |package $packageName
       |
       |class ${clazzName}(${createLabelParams(paramsGenerators.map(gen => (gen.name, gen.typ))).mkString(", ")}) extends com.vaadin.ui.$componentType {
       |
       |  ${actions.mkString("\n\t")}
       |}
   """.stripMargin

  def createLabelParams(paramsAndTypes: Seq[(String, String)]): Seq[String] =
    paramsAndTypes.map(paramWithType =>
      s"${changeBetweenClassNameAndVariableName(paramWithType._1)}: ${paramWithType._2}")

}
