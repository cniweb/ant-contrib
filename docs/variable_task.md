---
layout: home
---
# Variable Task

The Variable task provides a mutable property to Ant and works much like variable assignment in Java. This task is similar to the standard Ant Property task, except that THESE PROPERTIES ARE MUTABLE. While this goes against the standard Ant use of properties, occasionally it is useful to be able to change a property value within the build. **In general, use of this task is DISCOURAGED, and the standard Ant Property should be used if possible.** Having said that, in real life I use this a lot.

Variables can be set individually or loaded from a standard properties file. A 'feature' of variables is that they can override properties, but properties cannot override variables. So if an already established property exists, its value can be reassigned by use of this task.

**Table 8.1. Variable Task Attributes**

| Attribute | Description                                                        | Default | Required                    |
|-----------|--------------------------------------------------------------------|---------|-----------------------------|
| `name`  | The name of the property to set.                                   | None    | Yes, unless 'file' is used. |
| `value`  | The value of the property.                                         | ""      | No                          |
| `unset`  | Removes the property from the project as if it had never been set. | false   | No                          |
| `file`  | The name of a standard properties file to load variables from.     | None    | No                          |

In the following example, the property ` x` is first set to "6", then evaluated by the ` if` , and reassigned the value "12". The ` echo` task will print out 12.

```xml
<var name="x" value="6"/>
<if>
  <equals arg1="${x}" arg2="6" />
  <then>
    <var name="x" value="12"/>
  </then>
</if>
<echo>${x}</echo>   <!-- will print 12 -->
````

The next example shows a property being set, echoed, unset, then reset:

```xml
<property name="x" value="6"/>
<echo>${x}</echo>   <!-- will print 6 -->
<var name="x" unset="true"/>
<property name="x" value="12"/>
<echo>${x}</echo>   <!-- will print 12 -->
```

The following shows some more uses of the Variable task. It is especially handy for property appending. Notice a couple of things: the property task can't override a var value, in general, you should use var with the unset attribute to change the value of a property.

```xml
<var name="x" value="6"/>
<echo>x = ${x}</echo>   <!-- print: 6 -->

<var name="x" value="12"/>
<echo>x = ${x}</echo>   <!-- print: 12 -->

<var name="x" value="6 + ${x}"/>
<echo>x = ${x}</echo>   <!-- print: 6 + 12 -->

<var name="str" value="I "/>
<var name="str" value="${str} am "/>
<var name="str" value="${str} a "/>
<var name="str" value="${str} string."/>
<echo>${str}</echo>     <!-- print: I am a string. -->

<var name="x" value="6"/>
<echo>x = ${x}</echo>   <!-- print: 6 -->

<property name="x" value="12"/>
<echo>x = ${x}</echo>   <!-- print: 6 (property can&#39;t override) -->

<var name="x" value="blue"/>
<tstamp>
  <format property="x" pattern="EEEE"/>
</tstamp>
<echo>Today is ${x}.</echo> <!-- print: Today is blue. -->

<var name="x" value="" unset="true"/>
<tstamp>
  <format property="x" pattern="EEEE"/>
</tstamp>
<echo>Today is ${x}.</echo> <!-- print: Today is Friday. -->
```
