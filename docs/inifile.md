---
layout: home
---
IniFile
=======

Build and Edit Windows .ini files. Only the simple edits, `remove` and `set` are allowed. Set has limited computation capability which is described later.

Parameters
----------

| Attribute | Description                              | Required |
|-----------|------------------------------------------|----------|
| `source`  | The name source .ini file to read in.    | No.      |
| `dest`  | The name destination .ini file to write. | Yes.     |

Parameters specified as nested elements
---------------------------------------

`remove`

| Attribute | Description             | Required                                                |
|-----------|-------------------------|---------------------------------------------------------|
| `section`  | The name of the section | Yes.                                                    |
| `property`  | The name property.      | No. If not supplied, the entire section will be removed |

`set`

| Attribute | Description                                                                                                                                                                                                    | Required                         |
|-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------|
| `section`  | The name of the section                                                                                                                                                                                        | Yes.                             |
| `property`  | The name property.                                                                                                                                                                                             | Yes.                             |
| `value`  | The value to set the property to.                                                                                                                                                                              | No, if `operation` is specified. |
| `operation`  | The operation to perform on the existing value. Possible values are "+" and "-", which add and subtract 1, respectively from the existing value. If the value doesn't already exist, the set is not performed. | No, if `value` is specified.     |

Example
-------

```xml
<inifile source="myprog.ini" dest="myprog.new.ini">
  <set section="Section1" property="release-date" value="${todays.date}" />
  <set section="Section1" property="build-number" operation="+" />
  <remove section="Section2" property="useless" />
  <remove section="OutdatedSection" />
</inifile>
```
