---
layout: home
---
Trycatch
========

A wrapper that lets you run a set of tasks and optionally run a different set of tasks if the first set fails and yet another set after the first one has finished.

This mirrors Java's try/catch/finally.

The tasks inside of the required `<try>` element will be run. If one of them should throw a BuildException several things can happen:

-   If there is no `<catch>` block, the exception will be passed through to Ant.
-   If the property attribute has been set, a property of the given name will be set to the message of the exception.
-   If the reference attribute has been set, a reference of the given id will be created and point to the exception object.
-   If there is a `<catch>` block, the tasks nested into it will be run.

If a `<finally>` block is present, the task nested into it will be run, no matter whether the first tasks have thrown an exception or not.

Parameters
----------

| Attribute | Description                                                                                     | Required |
|-----------|-------------------------------------------------------------------------------------------------|----------|
| `property`  | Name of a property that will receive the message of the exception that has been caught (if any) | No.      |
| `reference` | Id of a reference that will point to the exception object that has been caught (if any)         | No       |

Example
-------

```xml
<trycatch property="foo" reference="bar">
  <try>
    <fail>Tada!</fail>
  </try>
  <catch>
    <echo>In &lt;catch&gt;.</echo>
  </catch>
  <finally>
    <echo>In &lt;finally&gt;.</echo>
  </finally>
</trycatch>

<echo>As property: ${foo}</echo>
<property name="baz" refid="bar" />
<echo>From reference: ${baz}</echo>
```

results in

```
[trycatch] Caught exception: Tada!
    [echo] In <catch>.
    [echo] In <finally>.
    [echo] As property: Tada!
    [echo] From reference: Tada!
```
