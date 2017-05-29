---
layout: home
---
# Assert Task

The Assert task adds an assertion capability to Ant projects. This task works in a manner very similar to the Java `assert` keyword, and provides a limited "design by contract" facility to Ant. This is very useful for testing build scripts prior to putting them into production.

The Assert task verifies that a particular boolean condition is met, and throws a BuildException if it is not.

Also like Java's `assert` keyword, the Assert task must be 'turned on' using the property `ant.enable.asserts` . If not set, or is set to ` false` , the Assert task works exactly like the Sequential task. If the [Variable task](variable_task.html "Variable Task") is used to define this property, then it can be turned on and off as needed throughout a build.

This task can hold other tasks including Assert.

Thie assert task may contain a single conditional element known by ANT, or one of the following additional conditions: [IsPropertyTrue](more_conditions.html "More Conditions") , [IsPropertyFalse](more_conditions.html "More Conditions") , [StartsWith](more_conditions.html "More Conditions") , [EndsWith](more_conditions.html "More Conditions") , [IsGreaterThan](more_conditions.html "More Conditions") , [IsLessThan](more_conditions.html "More Conditions") and conditions. See the If task for examples of using these conditionals.

**Table 4.1. Assert Task Attributes**

| Attribute   | Description                                                                                                                                                 | Default | Required                                                              |
|-------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|-----------------------------------------------------------------------|
| `name`  | The name of the property to test for. This is a shortcut for specifying an <equals> condition.                                                        | none    | No. However, if specified, the 'value' attribute must also be present |
| `value`  | The value to test for, implies . If the value in the project is different than this value, a BuildException will be thrown and the build will stop.         | none    | No, unless the 'name' attribute is specified.                         |
| `execute`  | Should the tasks contained in this task be executed? It may be useful to set this to false when testing build files.                                        | True    | No                                                                    |
| `failonerror`  | Should the build halt if the assertion fails? Setting this to false is contrary to the intented use of assertions, but may be useful in certain situations. | True    | No                                                                    |

Examples
--------

In the following example, the first ` assert` task checks that the ` wait` property exists and does not execute the ` echo` and ` sleep` tasks. The second ` assert` task checks that the ` wait` property exists, has a value of 2, and executes the ` echo` task.

```xml
<property name="wait" value="2"/>
<assert execute="false">
<isset property="wait" />
<sequential>
  <echo>
    Waiting ${wait} seconds...
    Click the red button to stop waiting.
  </echo>
  <sleep seconds="${wait}"/>
</sequential>
</assert>
<assert name="wait" value="2" execute="true">
<sequential>
  <echo>done waiting!</echo>
</sequential>
</assert>
```

The next example shows Assert being used in a unit test for the "limit" task:

```xml
<property name="ant.enable.asserts" value="true"/>
<target name="test2">
  <!-- should not stop &#39;sleep&#39; task, should print out &#39;_passed_&#39; -->
  <stopwatch name="timer"/>
  <limit maxwait="5">
    <sleep seconds="1"/>
    <echo>_passed_</echo>
  </limit>
  <stopwatch name="timer" action="total"/>
  <assert message="Too much time.">
    <islessthan arg1="${timer}" arg2="2"/>
  </assert>
</target>
```

If the ` ant.enable.asserts` property is set to false, then in the above example, the ` echo` , ` sleep` , and ` echo` tasks will all execute.
