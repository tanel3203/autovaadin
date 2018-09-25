package ee.entusiast.autovaadin.generators

import ee.entusiast.autovaadin.LOG
import ee.entusiast.autovaadin.Model.{Components, Generator, SingleComponent}
import ee.entusiast.autovaadin.templates.{LabelTemplate, LayoutTemplate}


object ComponentsGenerator {


  def create(generator: Generator, packageName: String): Components = {
    LOG.info(s"ComponentsGenerator.create started with generator: $generator, packageName: $packageName")


    val components = iterate(generator)(packageName)
    LOG.info(s"ComponentsGenerator.create populated components: $components")

    components.filter(component => component._2.nonEmpty)

  }

  def iterate(generator: Generator)(implicit packageName: String): Seq[SingleComponent] = {

    if (generator.components.isEmpty) Seq(createSingle(generator))
    else {
      iterateDepth(generator, Seq.empty)
    }

  }

  def iterateDepth(generator: Generator, generators: Seq[SingleComponent])(implicit packageName: String): Seq[SingleComponent] = {

    if (generator.components.isEmpty) generators :+ createSingle(generator)
    else {
      iterateWidth(generator.components.getOrElse(Seq.empty), generators :+ createSingle(generator))
    }

  }

  def iterateWidth(components: Seq[Generator], generators: Seq[SingleComponent])(implicit packageName: String): Seq[SingleComponent] = {

    var preparedComponents: Seq[SingleComponent] = generators

    for (subComponentGenerator <- components) {
      preparedComponents = preparedComponents ++ iterateDepth(subComponentGenerator, generators)
    }

    preparedComponents
  }

  def createSingle(generator: Generator)(implicit packageName: String): SingleComponent = {
    LOG.info("creating single component...")
    (generator, createTemplate(generator.name,
      generator.typ,
      generator.components.getOrElse(Seq.empty),
      generator.actions.getOrElse(Seq.empty)))
  }

  // TODO add package names to vaadin components
  // TODO add package names to project components

  // TODO handle Panel instead of Layout
  // TODO handle non-vaadin components instead of Layout. E.g. String can't be added to Label with addcomponent
  // TODO handle varying param types.
  // E.g. all Layouts have addComponent
  // Panels have setContent
  //


  val vaadinLayouts = Seq("VerticalLayout","HorizontalLayout", "GridLayout", "FormLayout")
  val vaadinOthers = Seq("Label")

  def createTemplate(clazzName: String, componentType: String, paramsGenerators: Seq[Generator], actions: Seq[String])(implicit packageName: String): String = {
    LOG.info(s"creating template... clazzName: $clazzName, componentType: $componentType, paramsGenerators: $paramsGenerators")

    def createLayout(): String = LayoutTemplate.create(clazzName, componentType, paramsGenerators)
    def createLabel(): String = LabelTemplate.create(clazzName, componentType, paramsGenerators, actions)

    if (vaadinLayouts.contains(componentType)) createLayout()
    else if (vaadinOthers.contains(componentType)) createLabel()
    else ""
  }
}
