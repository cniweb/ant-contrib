---
layout: home
---
TimestampSelector
=================

The TimestampSelector task takes either a nested &lt;path&gt; element, or a path reference, and sets either a named property, or a `path` instance to absolute pathnames of the files with either the N latest or earliest modification dates (based on the `age` attribute)

Parameters
----------

| Attribute   | Description                                                                                                                                                    | Required                                 |
|-------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------|
| property    | The property to set with the most recently modified file. Mutually exclusive with the `outputsetid` attribute.                                                 | Yes, if `outputsetid` is not specified.  |
| outputsetid | The id of a `path` instance which will contain the resulting list of files. This id should not already exist. Mutually exclusive with the `property` attribute | Yes, if `property` is note specified.    |
| count       | The number of files to find. If more than 1, than the files in the output appear in the order indicated by the `age` attribute.                                | No. Defaults to `1              `        |
| age         | The age of the files to retrieve, either `eldest` or `youngest`. Defaults to `youngest`.                                                                       | No. Defaults to `1              `        |
| pathSep     | The path separator to separate paths with when using the `property` attribute in conjunction with the `count` attribute                                        | No. Defaults to `,              `        |
| pathref     | Id of the path to find the most recently modified file in.                                                                                                     | No, if a `path` subelement is specified. |

Parameters specified as nested elements
---------------------------------------

<span id="path"></span>

### path

[Path](http://ant.apache.org/manual/using.html#path) is used to select sets of files or directories in which to find the most recently modified file

Example
-------

### Using a path reference

        

        <path id="mypath">
           <fileset dir="${log.dir}">
             <include name="update*.log" />
           </fileset>
        <path>
        <timestampselector property="most.recent.logs"
                            pathref="mypath" count="3"
                            pathsep=";" />

        <echo message="${most.recent.logs}" />
        
        

### Using a nested `path` element

        

        <timestampselector property="most.recent.logs"
                            count="3"
                            pathsep=";" >
          <path>
             <fileset dir="${log.dir}">
               <include name="update*.log" />
             </fileset>
          <path>
        </timestampselector>

        <echo message="${most.recent.logs}" />
        
        

### Outputing to a `path` element

        

        <timestampselector outputsetref="most.recent.logs"
                            pathref="mypath" count="3">
          <path>
             <fileset dir="${log.dir}">
               <include name="update*.log" />
             </fileset>
          <path>
        </timestampselector>

        <copy todir="somedir">
          <path refid="most.recent.logs" />
        </copy>
        
        


