---
layout: home
---
Pathtofileset
=============

Coverts a path to a fileset. This is usefull if you have a path but need to use a fileset as input in a ant task.

Parameters
----------

| Attribute         | Description                                                                                                                                                                                                                                                  | Required               |
|-------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------|
| `dir`  | The root of the directory tree of this FileSet                                                                                                                                                                                                               | Yes                    |
| `pathrefid`  | The reference to the path to convert from                                                                                                                                                                                                                    | Yes                    |
| `ignorenonrelative`  | This boolean controls what will happen if any of the files in the path are not in the directory for the fileset. If this is "true" the files are ignored, if this is "false" a build exception is thrown. (Note: if files are not present no check is made). | No, default is "false" |
| `name`  | This is the identifier of the fileset to create. This fileset will contain the files that are relative to the directory root. Any files that are not present will not be placed in the set.                                                                  | Yes                    |

Example
-------

        <outofdate outputsourcespath="modified.sources.path">
          <sourcefiles>
            <fileset dir="a/b/c" includes="**/*.java"/>
          </sourcefiles>
          <mapper dir="a/b/c" type="glob" from="*.java" to="output/*.xml"/>
          <sequential>
            <pathtofileset name="modified.sources.fileset"
                           pathrefid="modified.sources.path"
                           dir="a/b/c"/>
            <copy todir="output">
              <fileset refid="modified.sources.fileset"/>
              <mapper type="glob" from="*.java" to="*.xml"/>
            </copy>
          </sequential>
        </outofdate>
        

