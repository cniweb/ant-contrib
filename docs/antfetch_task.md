---
layout: home
---
# AntFetch

AntFetch is identical to the standard 'Ant' task, except that it allows properties from the new project to be set in the original project.

Some background may be in order: When the <ant> task is used, in actuality, a new Ant project is created, and depending on the inheritAll property, it is populated with properties from the original project. Then the target in this new project is executed. Any properties set in the new project remain with that project, they do not get "passed back" to the original project. So, for example, if the target in the new project sets a property named "image.directory", there is no reference to that property in the original. Here's an example of what I mean:

Suppose that the "fillImageDirectory" target sets a property named "image.directory" and I call the following:

```xml
<ant dir="${image.project}" target="fillImageDirectory"/>
<echo>${image.directory}</echo>
````

The output of the echo task will be ${image.directory}, not whatever was set in the "fillImageDirectory" target.
The AntFetch task allows that image.directory property to be set in the original project. The attributes for AntFetch are identical to the 'Ant' task, with one additional, optional attibute. This attribute is named "return" and can be either a single property name or a comma separated list of property names.

Assuming that "fillImageDirectory" actually sets a property named "image.directory", the following example will print out the directory name:

```xml
<antfetch dir="${image.project}" target="fillImageDirectory" return="image.directory"/>
<echo>${image.directory}</echo>
````

And this one will also print out the thumbnail directory:

```xml
<antfetch dir="${image.project}" target="fillImageDirectory" return="image.directory, thumbnail.directory"/>
<echo>${image.directory}</echo>
<echo>${thumbnail.directory}</echo>
```

The attributes for AntFetch are identical to the 'ant' task, with one additional, optional attibute. This attribute is named "return" and can be either a single property name or a comma separated list of property names.

**Table 14.1. AntFetch Attributes**

| Attribute | Description                                                                                                | Default | Required |
|-----------|------------------------------------------------------------------------------------------------------------|---------|----------|
| `return`  | A comma separated list of property names. Whitespace is allowed, so either "a,b" or "a, b" are acceptable. | None    | No       |

For other attribute and nested element information and more examples, see the documentation for the "ant" task in the Ant documentation.

