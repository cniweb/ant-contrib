---
layout: home
---
Propertycopy
============

Copies the value of a named property to another property. This is useful when you need to plug in the value of another property in order to get a property name and then want to get the value of that property name.

Parameters
----------

| Attribute         | Description                                                                                                    | Required                                 |
|-------------------|----------------------------------------------------------------------------------------------------------------|------------------------------------------|
| `property`  | The name of the property to set.                                                                               | Yes.                                     |
| `override`  | If the property is already set, should we change it's value. Can be `true` or `false`                          | No. Defaults to `false`                  |
| `name` *Deprecated* | The name of the property to set.                                                                               | No. Use the `property` attribute instead |
| `from`  | The name of the property you wish to copy the value from.                                                      | Yes.                                     |
| `silent`  | Do you want to suppress the error if the "from" property does not exist, and just not set the property "name". | No, default is "false".                  |

Example
-------

```xml
<property name="org" value="MyOrg" />
<property name="org.MyOrg.DisplayName" value="My Organiziation" />
<propertycopy name="displayName" from="org.${org}.DisplayName" />
```

Sets `displayName` to "My Organiziation".
