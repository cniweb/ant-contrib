---
layout: home
---
Foreach
=======

The foreach task iterates over a list, a list of paths, or both. If both, list and paths, are specified, the list will be evaluated first. Nested paths are evaluated in the order they appear in the task.

Parameters
----------

| Attribute   | Description                                                                                                                                                                                                                                                                                               | Required                                         |
|-------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------|
| `list`  | The list of values to process, with the delimiter character, indicated by the "delimiter" attribute, separating each value.                                                                                                                                                                               | Yes, unless a nested Fileset has been specified. |
| `target`  | Name of the target to call for each token or matched file.                                                                                                                                                                                                                                                | Yes                                              |
| `param`  | Name of the parameter to pass the tokens or files in as to the target.                                                                                                                                                                                                                                    | Yes                                              |
| `delimiter`  | The delimiter characters that separates the values in the "list" attribute. Each character in the supplied string can act as a delimiter. This follows the semantics of the StringTokenizer class.                                                                                                        | No, defaults to ",".                             |
| `inheritall`  | If `true`, pass all properties to the called target. Defaults to `false`.                                                                                                                                                                                                                                 | No                                               |
| `inheritrefs`  | If `true`, pass all references to the the called target. Defaults to `false`.                                                                                                                                                                                                                             | No                                               |
| `parallel`  | If `true`, all instances of the called target will execute in parallel. Defaults to `false`, which forces sequential execution of the targets. It is up to the caller to ensure that parallel execution is safe. This is accomplished through the means of the "parallel" task contained in the ANT core. | No                                               |
| `maxThreads`  | The maximum number of allowable threads when executing in parallel.                                                                                                                                                                                                                                       | No. Defaults to 5.                               |
| `trim`  | If `true`, any leading or trailing whitespace will be removed from the list item before it is passed to the requested target                                                                                                                                                                              | No. Defaults to false.                           |

Parameters specified as nested elements
---------------------------------------

### path

[Path](http://ant.apache.org/manual/using.html#path)s are used to select sets of files or directories to iterate over.

Using a path allows you to determine the order by which files are considered by using [filelist](http://ant.apache.org/manual/CoreTypes/filelist.html)s or explicit `pathelements`. You also can specify whether you want to iterate over files or directories by chosing either filesets or [dirset](http://ant.apache.org/manual/CoreTypes/dirset.html)s.

### fileset

[FileSet](http://ant.apache.org/manual/CoreTypes/fileset.html)s are used to select sets of files to iterate over. **This element is deprecated, use [nested path elements](#path) instead.**

### param

Specifies the properties to set before running the specified target. See [property](http://ant.apache.org/manual/CoreTasks/property.html) for usage guidelines.

### <span id="reference">reference</span>

Used to chose references that shall be copied into the new project, optionally changing their id.

| Attribute | Description                                     | Required                            |
|-----------|-------------------------------------------------|-------------------------------------|
| `refid`  | The id of the reference in the calling project. | Yes                                 |
| `torefid`  | The id of the reference in the called project.  | No, defaults to the value of refid. |

