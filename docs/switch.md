---
layout: home
---
Switch
======

Task definition for the ANT task to switch on a particular value.

Parameters
----------

| Attribute       | Description                                | Required               |
|-----------------|--------------------------------------------|------------------------|
| `value`           | The value to switch on.                    | Yes.                   |
| `caseinsensitive` | Should we do case insensitive comparisons? | No, default is `false` |

Parameters specified as nested elements
---------------------------------------

At least one `<case>` or `<default>` is required.

### case

An individual case to consider, if the value that is being switched on matches to value attribute of the case, then the nested tasks will be executed.

#### Parameters

| Attribute | Description                                           | Required |
|-----------|-------------------------------------------------------|----------|
| `value`     | The value to match against the tasks value attribute. | Yes.     |

### default

The default case for when no match is found. Must not appear more than once per task.

Example
-------

```xml
<switch value="${foo}">
  <case value="bar">
    <echo message="The value of property foo is bar" />
  </case>
  <case value="baz">
    <echo message="The value of property foo is baz" />
  </case>
  <default>
    <echo message="The value of property foo is not sensible" />
  </default>
</switch>
```
