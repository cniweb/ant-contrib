---
layout: home
---
For
===

The for task iterates over a list, a list of paths, or any type that has a public `iterator()` method. The list will be evaluated first. Nested paths are evaluated in the order they appear in the task. Nested types will then be evalulated.

This task is the same as the `<foreach>` task, except that

-   it uses a nested sequential for each iteration; and
-   it implements an additional "keepgoing" attribute.

`<for>` makes use of ant's macrodef task, so the `@{}` notation is used for parameter substition.

*This task only works for ant version greater than or equal to ant 1.6.0.*

Parameters
----------

| Attribute | Description | Required | 
|--|--|--|
| `list` | The list of values to process, with the delimiter character, indicated by the `delimiter` attribute, separating each value. | Yes, one of these need to be set, unless a nested path has been specified. |
| `end` | Sets the ending index value. If this attribute is set, the `<for>` task will loop from `start` (default 1) to `end`, using `step` (default 1) increments. |  |
| `param` | Name of the parameter to pass the tokens or files in as to the sequential. | Yes |
| `delimiter` | The delimiter characters that separates the values in the `list` attribute. Each character in the supplied string can act as a delimiter. This follows the semantics of the StringTokenizer class. | No, defaults to `,`. |
| `parallel` | If `true`, all iterations of the nested `<sequential>` will execute in parallel. Defaults to `false`, which forces sequential execution of the iterations. It is up to the caller to ensure that parallel execution is safe. | No |
| `keepgoing` | If `true`, all iterations of the called `<sequential>` will be executed, even if a task in one or more of them fails. Defaults to `false`, which forces execution to stop as soon as a task fails. At the end, if any iterator has failed, the `<for>` task will fail, otherwise `<for>` will succeed. Note that execution does not proceed keepgoing from one task to the next within the `<sequential>`, but rather from one iteration to the next. It is up to the caller to ensure that keepgoing execution is safe. | No |
| `threadCount` | The maximum number of allowable threads when executing in parallel. | No. Defaults to 5. |
| `trim` | If `true`, any leading or trailing whitespace will be removed from the list item before it is passed to the sequential. | No. Defaults to false. |
| `begin` | Sets the starting index value. This in only used if the `end` attribute is set. | No. Defaults to "1". |
| `step` | Sets the index increment step. This in only used if the `end` attribute is set. | No. Defaults to "1". |

Parameters specified as nested elements
---------------------------------------

<span id="path"></span>

### path

[Path](http://ant.apache.org/manual/using.html#path)s are used to select sets of files or directories to iterate over.

Using a path allows you to determine the order by which files are considered by using [filelist](http://ant.apache.org/manual/CoreTypes/filelist.html)s or explicit `pathelements`. You also can specify whether you want to iterate over files or directories by chosing either filesets or [dirset](http://ant.apache.org/manual/CoreTypes/dirset.html)s.

<span id="fileset"></span>

### fileset

[FileSet](http://ant.apache.org/manual/CoreTypes/fileset.html)s are used to select sets of files to iterate over.

<span id="fileset"></span>

### dirset

[DirSet](http://ant.apache.org/manual/CoreTypes/dirset.html)s are used to select sets of directories to iterate over.

<span id="seqential"></span>

### sequential

This is the list of tasks to be run for each iteration of the list.

### Example

To print out the first five letters of the latin alphabet:

```xml
<echo message="The first five letters of the alphabet are:"/>
<for list="a,b,c,d,e" param="letter">
  <sequential>
    <echo>Letter @{letter}</echo>
  </sequential>
</for>
```        

A more complicated example to to iterate over a set of c++ source files and invoke the `<cc>` task on them:

```xml
<for param="file">
  <path>
    <fileset dir="${test.dir}/mains" includes="*.cpp"/>
  </path>
  <sequential>
    <propertyregex override="yes"
      property="program"  input="@{file}"
      regexp=".*/([^\.]*)\.cpp" replace="\1"/>
    <mkdir dir="${obj.dir}/${program}"/>
    <mkdir dir="${build.bin.dir}"/>
    <cc link="executable" objdir="${obj.dir}/${program}"
        outfile="${build.bin.dir}/${program}">
      <compiler refid="compiler.options"/>
      <fileset file="@{file}"/>
      <linker refid="linker-libs"/>
    </cc>
  </sequential>
</for>
```     

The preceding example will stop as soon as one of the `<cc>` tasks fails. If you change the first line of the example to

```xml
<for param="file" keepgoing="true">
```

All iterations will be executed, and then `<for>` will fail if any one or more of the `<cc>` tasks failed.

The following example embeds an outofdate type and iterates over the sources that are newer than their corresponding targets.

```xml
<ac:for param="xmlfile" xmlns:ac="antlib:net.sf.antcontrib">
  <ac:outofdate>
    <ac:sourcefiles>
      <ac:fileset dir="${basedir}/xdocs" includes="**/*.xml"/>
    </ac:sourcefiles>
    <ac:mapper dir="${basedir}/xdocs"
                type="glob" from="*.xml" to="${basedir}/docs/*.html"/>
  </ac:outofdate>
  <ac:sequential>
    <echo>Need to generate a target html file from source file @{xmlfile}</echo>
  </ac:sequential>
</ac:for>
```   

The following example loops from one to ten.

```xml
<ac:for param="i" end="10">
  <sequential>
    <echo>i is @{i}</echo>
  </sequential>
</ac:for>
```  

The following example counts down from 10 to 0 in steps of -2.

```xml
<ac:for param="i" begin="10" step="-2" end="0">
  <sequential>
    <echo>i is @{i}</echo>
  </sequential>
</ac:for>
```
