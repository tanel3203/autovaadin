package ee.entusiast.autovaadin.templates

import ee.entusiast.autovaadin.Model.Generator

object LayoutTemplate {

  import TemplateUtils._

  def create(clazzName: String, componentType: String, paramsGenerators: Seq[Generator])(implicit packageName: String): String =
    s"""
       |package $packageName
       |
       |class ${clazzName}(${createParams(paramsGenerators.map(gen => gen.name)).mkString(", ")}) extends com.vaadin.ui.$componentType {
       |
       |${createAddComponents(paramsGenerators.map(gen => gen.name)).mkString("\n")}
       |}
   """.stripMargin

  private def createParams(params: Seq[String]): Seq[String] =
    params.map(param => s"${changeBetweenClassNameAndVariableName(param)}: ${param}")

  private def createAddComponents(params: Seq[String]): Seq[String] =
    params.map(param => s"\taddComponent(${changeBetweenClassNameAndVariableName(param)})")
}
