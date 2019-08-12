---
layout: home
---
PropertySelector
================

Selects property names that match a given regular expression and returns them in a delimited list

Parameters
----------

| Attribute     | Description                                                                                                                                                                                                          | Required                |
|---------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------|
| `property`  | The name of the property you wish to set.                                                                                                                                                                            | Yes.                    |
| `override`  | If the property is already set, should we change it's value. Can be `true` or `false`                                                                                                                                | No. Defaults to `false` |
| `match`  | The regular expression which is used to select property names for inclusion in the list. This follows the standard regular expression syntax accepted by ant's regular expression tasks.                             | Yes.                    |
| `select`  | A pattern which indicates what selection pattern you want in the returned list. This used the substitution pattern syntax to indicate where to insert groupings created as a result of the regular expression match. | No. default is "\\0".   |
| `casesensitive`  | Should the match be case sensitive                                                                                                                                                                                   | No. default is "true".  |
| `delimiter`  | The delimiter used to seperate entries in the resulting property                                                                                                                                                     | No. default is ",".     |
| `distinct`  | Should the returned entries be a distinct set (no duplicate entries)                                                                                                                                                 | No. default is "false". |

Select expressions
------------------

Expressions are selected in a the same syntax as a regular expression substitution pattern.

-   `\0` indicates the entire property name (default).
-   `\1` indicates the first grouping
-   `\2` indicates the second grouping
-   etc...

Example
-------

The following code

```xml
<property name="package.ABC.name" value="abc pack name" />
<property name="package.DEF.name" value="def pack name" />
<property name="package.GHI.name" value="ghi pack name" />
<property name="package.JKL.name" value="jkl pack name" />

<propertyselector property="pack.list"
                  delimiter=","
                  match="package\.([^\.]*)\.name"
                  select="\1"
                  casesensitive="false" />
```     

would yield the results

```     
ABC,DEF,GHI,JKL
```        
        
You could then iterate through this list using the [ForEach Task](foreach.html) as follows:

```xml
<foreach list="${pack.list}"
         delimiter=","
         target="print.name"
         param="pack.id" />

<target name="print.name" >
  <propertycopy name="pack.name" value="package.${pack.id}.name" />
  <echo message="${pack.name}" />
</target>
```     

Would print

```     
[echo] abc pack name
[echo] def pack name
[echo] ghi pack name
[echo] jkl pack name
```
