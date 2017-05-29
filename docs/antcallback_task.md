---
layout: home
---
# AntCallBack

AntCallBack is identical to the standard `<antcall>` task, except that it allows properties set in the called target to be available in the calling target.

Some background may be in order: When the `<antcall>` task is used, in actuality, a new Ant project is created, and depending on the inheritAll property, it is populated with properties from the original project. Then the requested target in this new project is executed. Any properties set in the new project remain with that project, they do not get "passed back" to the original project. So, for example, if the target in the new project sets a property named "image.directory", there is no reference to that property in the original. Here's an example of what I mean:

```xml
<target name="testCallback" description="Test CallBack">
  <antcallback target="-testcb" return="a, b"/>
  <echo>a = ${a}</echo>
  <echo>b = ${b}</echo>
</target>

<target name="-testcb">
  <property name="a" value="A"/>
  <property name="b" value="B"/>
</target>
```

The output from executing "testCallback" looks like this:

```
a = A
b = B
```

Contrast with this output from "antcall":

````
a = ${a}
b = ${b}
```

This is an often requested feature for Ant, at least judging from the Ant mailing lists. I assume this is because it allows a more functional programming style than Ant natively supports. The proper Ant way of doing the above is like this:

```xml
<target name="testCallback" description="Test CallBack" depends="-testcb">
  <echo>a = ${a}</echo>
  <echo>b = ${b}</echo>
</target>

<target name="-testcb">
  <property name="a" value="A"/>
  <property name="b" value="B"/>
</target>
```

This is actually looks cleaner in this situation, and is faster, too. There is significant overhead in using both "antcall" and "antcallback" in that they both require a lot of object instantiation and property copying. That said, many people prefer to use "antcall" and "antcallback" as it better fits their logic and style.
The attributes for AntCallBack are identical to the 'antcall' task, with one additional, optional attibute. This attribute is named "return" and can be either a single property name or a comma separated list of property names.

<span id="N10B76"></span>
**Table 15.1. AntCallBack Attributes**

| Attribute | Description                                                                                                | Default | Required |
|-----------|------------------------------------------------------------------------------------------------------------|---------|----------|
| return    | A comma separated list of property names. Whitespace is allowed, so either "a,b" or "a, b" are acceptable. | None    | No       |

For other attribute and nested element information and more examples, see the documentation for the "antcall" task in the Ant documentation.

