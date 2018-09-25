package ee.entusiast.autovaadin.templates

object TemplateUtils {

  def changeBetweenClassNameAndVariableName(name: String): String = {
    if (name.head.equals(name.head.toUpper))  name.head.toLower + name.substring(1, name.length)
    else                                      name.head.toUpper + name.substring(1, name.length)
  }
}
