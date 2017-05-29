---
layout: home
---
Importurl
=========

The Importurl task will download a file, and import it's contents into the current project. The file can be in the form of a standard ant .xml project file, or a .jar/.zip file.

In the case of an .xml file, the file is downloaded, and imported as is. In this case, the file itself is the only thing downloaded (ie. no corresponding .properties files, or other build files are downloaded).

In the case of a .jar/.zip file, the file is downloaded and then decompressed. After decompression, the file 'build.xml' at the root level of the jar is imported (the name of the imported file can be overriden with the 'resource' attribute). By importing a .jar/.zip file, one can package additional resources along with the build.xml file. However, you must be careful how you refer to these resources. The build.xml file must follow the same rules as any other file imported with the <import> task, in that references relative to the build.xml file must be made with the property: ant.file.<projectname> where <projectname> is the name of the project being imported, as specified in the project tag. Example:

```xml
<project name="common">
  <dirname property="ant.dir.common" file="${ant.file.common}" />
  <property file="${ant.dir.common}/build.properties" />
</project>
```        

This task should be compatible with older versions of ant, but has only been tested with Ant 1.6.x. The underlying implementation is done using the [Ivy](http://jayasoft.org/ivy) dependency resolver software, and thus, it needs available to the same classloader that loads this task.

Parameters
----------

| Attribute       | Description                                                                                                                 | Required                                                                                                                                                                                                                                                      |
|-----------------|-----------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `org`  | The organization that publishes the script.                                                                                 | Yes.                                                                                                                                                                                                                                                          |
| `module`  | The name of the module which is to be fetched.                                                                              | Yes.                                                                                                                                                                                                                                                          |
| `rev`  | The revision of the module to be fetched.                                                                                   | No. Defaults to "latest.integration". See the ivy details for more information on the possible wildcarding that can be used for this value.                                                                                                                   |
| `type`  | The type of file to be downloaded                                                                                           | No. Defaults to 'jar'. Can be any file extension. However, unless the type is 'xml', the file is assumed to be a compressed file, expandable by ant's <expand> task (which is aliased to unjar, unzip).                                                 |
| `ivyConfUrl`  | The URL of an ivy configuration file to use. We will use the default resolver in this file to find the requested resource.  | No. Defaults to the IvyRepResolver.                                                                                                                                                                                                                           |
| `ivyConfFile`  | The path of an ivy configuration file to use. We will use the default resolver in this file to find the requested resource. | No. Defaults to the IvyRepResolver.                                                                                                                                                                                                                           |
| `repositoryUrl`  | The URL base of the repository to use. This results in using Ivy's URLResolver to resolve the requested resource.           | No. Defaults to the IvyRepResolver.                                                                                                                                                                                                                           |
| `repositoryDir`  | The file base of the repository to use. This results in using Ivy's FileSystemResolver to resolve the requested resource.   | No. Defaults to the IvyRepResolver.                                                                                                                                                                                                                           |
| `artifactPattern`  | The pattern used to find artifacts in the repository.                                                                       | No. If repositoryUrl or repositoryDir are specified, this defaults to the standard repository pattern: "/\[org\]/\[module\]/\[ext\]s/\[module\]-\[revision\].\[ext\]". Please see the ivy documentation for more information on the contents of this pattern. |
| `ivyPattern`  | The pattern used to find ivy file for the artifact in the repository.                                                       | No. If repositoryUrl or repositoryDir are specified, this defaults to the standard repository pattern: "/\[org\]/\[module\]/ivy-\[revision\].xml". Please see the ivy documentation for more information on the contents of this pattern.                     |
| `resource`  | The name of the resource within a compressed file to import.                                                                | No. Defaults to "build.xml".                                                                                                                                                                                                                                  |

Example
-------

```xml
<project name="build" basedir="." default="build" xmlns:ac="antlib:net.sf.antcontrib">
    <ac:antcontrib:importurl org="antcontrib"
                             module="common"
                             rev="3.2" />
</project>
```
        

would look for the file "antcontrib/common/jars/common-3.2.jar" in the IvyRep repository.

```xml
<ac:antcontrib:importurl org="antcontrib"
                         module="common"
                         rev="3.2" 
                         type="xml" />
```
        

would look for the file "antcontrib/common/jars/common-3.2.xml" in the IvyRep repository.

```xml
<ac:antcontrib:importurl repositoryUrl="http://www.antcontrib.org/ivyrep"
                         org="antcontrib"
                         module="common"
                        rev="3.2" />
```

would look for the located at "http://www.antcontrib.org/ivyrep/antcontrib/common/jars/common-3.2.jar"

```xml
<ac:antcontrib:importurl ivyConfUrl="http://ivyrep.myorg.com/ivyconf.xml"
                         org="antcontrib"
                         module="common"
                        rev="3.2" />
```
        

would configure ivy using the ivy configuration file at the given ivyConfUrl.

The following build.xml may be packaged into a .jar with it's corresponding build.properties file:

```xml
<project name="common">
  <basedir property="ant.dir.common" file="${ant.file.common}" />
  <property file="${ant.dir.common}/build.properties" />
</project>
```
