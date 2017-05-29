---
layout: home
---
PropertyRegex
=============

Performs regular expression operations on an input string, and sets the results to a property. There are two different operations that can be performed:

1.  Replacement - The matched regular expression is replaced with a substitition pattern
2.  Selection - Groupings within the regular expression are selected via a selection expression.

Parameters
----------

Attribute

Description

Required

property

The name of the property to set.

Yes.

override

If the property is already set, should we change it's value. Can be `true` or `false`

No. Defaults to `false`

input

The input string to be processed

Yes.

regexp

The regular expression which is matched in the input string.

Yes (can be specified in a `<regexp>` subelement).

select

A pattern which indicates what selection pattern you want in the returned value. This uses the substitution pattern syntax to indicate where to insert groupings created as a result of the regular expression match.

Yes, unless a replace is specified

replace

A regular expression substitition pattern, which will be used to replace the given regular expression in the input string.

Yes, unless a select is specified

casesensitive

Should the match be case sensitive

No. default is "true".

global

Should a replacement operation be performed on the entire string, rather than just the first occurance

No. default is `false`.

defaultValue

The value to set the output property to, if the input string does not match the specific regular expression.

No.

Select expressions
------------------

Expressions are selected in a the same syntax as a regular expression substitution pattern.

-   `\0` indicates the entire property name (default).
-   `\1` indicates the first grouping
-   `\2` indicates the second grouping
-   etc...

Replacement
-----------

It is important to note that when doing a "replace" operation, if the input string does not match the regular expression, then the property is not set. You can change this behavior by supplying the "defaultValue" attribute. This attribute should contain the value to set the property to in this case.

Example
-------

        
        <propertyregex property="pack.name"
                  input="package.ABC.name"
                  regexp="package\.([^\.]*)\.name"
                  select="\1"
                  casesensitive="false" />
        
        yields ABC
        

        
        <propertyregex property="pack.name"
                  input="package.ABC.name"
                  regexp="(package)\.[^\.]*\.(name)"
                  replace="\1.DEF.\2"
                  casesensitive="false" />
        
        yields package.DEF.name
        

