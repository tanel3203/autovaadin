# autovaadin

WIP. An attempt at autogenerating full vaadin application from JSON.
Goal is to generate all view-related classes and project structure automatically from a few JSONs. In addition service traits should be generated, so only the service content would need to be manually coded.


v0.1

* Generates files to given relative path
* one parent layout must exist
* supported is variable total depth and amount of inner components
* supported are vaadin methods as actions
* supported types are
** layouts - VerticalLayout, HorizontalLayout, GridLayout, FormLayout
** labels - Label
** non-vaadin parameters - Strings for example


Example input with a variable:
{
  "typ":"VerticalLayout",
  "name":"MainLayout",
  "components": [
  {
   "typ":"HorizontalLayout",
   "name":"ContentLayout",
   "components": [
   {
     "typ":"VerticalLayout",
     "name":"ContentLeftLayout",
     "components": $leftContent
   },
   {
     "typ":"VerticalLayout",
     "name":"ContentRightLayout"
   }
   ]
  }
  ]
}