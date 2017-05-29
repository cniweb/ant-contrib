---
layout: home
---
SortList
========

Sort a delimited list of items in their natural string order. Note that the `value` and `refid` attributes are mutually exclusive, and the `value` attribute takes precedence if both are specified.

Parameters
----------

| Attribute               | Description                                                                                                                                                                                                                                                                                                                                                                                  | Required                          |
|-------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------|
| `property`  | The name of the property to set.                                                                                                                                                                                                                                                                                                                                                             | Yes.                              |
| `override`  | If the property is already set, should we change it's value. Can be `true` or `false`                                                                                                                                                                                                                                                                                                        | No. Defaults to `false`           |
| `value`  | The list of values to process, with the delimiter character, indicated by the "delimiter" attribute, separating each value.                                                                                                                                                                                                                                                                  | Yes, unless "refid" is specified. |
| `refid`  | The id of where the list of values to sort is stored.                                                                                                                                                                                                                                                                                                                                        | Yes, unless "value" is specified. |
| `delimiter`  | The delimiter string that separates the values in the "list" attribute.                                                                                                                                                                                                                                                                                                                      | No, defaults to ",".              |
| `casesensitive`  | If `true`, does a case sensitive sorting of the strings. If `false` does case insensitive sorting.                                                                                                                                                                                                                                                                                           | No. Defaults to true.             |
| `numeric`  | If `true`, does a numeric sorting, treating all list entries as if they were numbers.                                                                                                                                                                                                                                                                                                        | No. Defaults to false.            |
| `orderPropertyFile`  | If specified, the list will sorted as if they were property names, and will be sorted in the order those properties appear in the given property file. Any properties in the `value` attribute which do not appear in the properties file will be inserted at the end of the list. This property is most useful when used in conjunction with the `<propertyselector>` task (see Example 2). | No.                               |
| `orderPropertyFilePrefix`  | If `orderPropertyFile` is specified, this value (with a trailing '.') will be prefixed to each property in the specified `orderPropertyFile` to determine sorting order                                                                                                                                                                                                                      | No.                               |

------------------------------------------------------------------------

Example 1
---------

```xml        
<property name="my.list" value="z,y,x,w,v,u,t" />
<sortlist property="my.sorted.list" value="${my.list}"
          delimiter="," />
<echo message="${my.sorted.list}" />
```

prints

```        
[echo] t,u,v,w,x,y,z
```        

## Example 2

test.properties

```properties
a.name=a
c.name=c
b.name=b
```

```xml     
<property file="test.properties" prefix="test" />
<propertyselector property="my.list"
                  delimiter=","
                  match="test\..*\.name"
                  select="\0" />
<sortlist property="my.sorted.list"
          value="${my.list}"
          delimiter=","
          orderPropertyFile="test.properties"
          orderPropertyFilePrefix="test" />

<echo message="${my.sorted.list}" />
```

prints

```        
[echo] test.a.name,test.c.name,test.b.name
```        

Notice that the test.properties file did not have `test.` prefixing all the properties.  The `orderPropertyFilePrefix` was used to do that.
