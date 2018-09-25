package ee.entusiast.autovaadin

object Main extends App {

  val generator =
    """
      |{
      |  "typ":"VerticalLayout",
      |  "name":"MainLayout",
      |  "components": [
      |  {
      |   "typ":"Label",
      |   "name":"TitleLabel"
      |  },
      |  {
      |   "typ":"Label",
      |   "name":"ContentLabel",
      |   "components": [
      |   {
      |    "typ":"Seq[String]",
      |    "name":"contentTexts",
      |    "actions": [
      |    {
      |     "action":"setValue(contentTexts.mkString(\", \"))"
      |    }
      |     ]
      |   }
      |   ]
      |  },
      |  {
      |   "typ":"Button",
      |   "name":"SendButton"
      |   "actions": [
      |   {
      |    "action":"addClickListener(new SendButtonClickListener)"
      |   }.
      |   {
      |    "action":"setCaption(\"Send\")"
      |   }.
      |   {
      |    "action":"setCaption(\"Send\")"
      |   }
      |]
      |  }
      |  ]
      |}
      |
   """.stripMargin


  val leftContent =
    """
      |[
      |     {
      |       "typ":"Label",
      |       "name":"NameLabel",
      |       "components": [
      |       {
      |         "typ": "String",
      |         "name": "Name"
      |       }
      |       ],
      |       "actions": [
      |           "setValue(name)",
      |           "setCaption(\"Name\")",
      |           "setWidth(\"400px\")"
      |       ]
      |     }
      |]
    """.stripMargin



  val generatorS =
    s"""
       |{
       |  "typ":"VerticalLayout",
       |  "name":"MainLayout",
       |  "components": [
       |  {
       |   "typ":"HorizontalLayout",
       |   "name":"ContentLayout",
       |   "components": [
       |   {
       |     "typ":"VerticalLayout",
       |     "name":"ContentLeftLayout",
       |     "components": $leftContent
       |   },
       |   {
       |     "typ":"VerticalLayout",
       |     "name":"ContentRightLayout"
       |   }
       |   ]
       |  }
       |  ]
       |}
       |
   """.stripMargin

  AutoVaadin.create(generatorS, "./src/main/scala/ee/entusiast/autovaadin/"+"generated/")

}