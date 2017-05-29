---
layout: home
---
Ant-Contrib Server Tasks
========================

The following tasks exist for running Ant server on one machine, and calling that server from another (or possibly the same) machine, to execute tasks.

AntServer
=========

Starts an ANT server in current process. This server will wait for client connections, and when received, it will execute the commands that the client has sent. NOTE: This is a blocking call, and this task will not return until someone sends the server a shutdown command.

Parameters
----------

| Attribute | Description                               | Required              |
|-----------|-------------------------------------------|-----------------------|
| `port`  | The port on which the server will listen. | No. Defaults to 17000 |

Example:
--------

```xml
<antserver port="12345" />
```     

RemoteAnt
=========

Sends command requests to a running instance of an AntServer which was started using the <antserver> task. These commands are executed in the space of the server, and therefore have no access to any variables or references in the currently executing project.

Parameters
----------

| Attribute   | Description                                                                                                                                            | Required                    |
|-------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------|
| `machine`  | The machine name on which the server is running.                                                                                                       | No. Defaults to "localhost" |
| `port`  | The port on which the server is listening.                                                                                                             | No. Defaults to 17000       |
| `persistant`  | Should we execute all commands, regardless of whether or not one of them fails. If false, as soon as a failure is encountered, we will stop execution. | No. Defaults to false       |
| `failonerror`  | If any of the sent commands encounters a build failure on the server, should we fail this task.                                                        | No. Defaults to true.       |

Parameters Specified as Nested Elements
---------------------------------------

The commands to send are represented as nested elements as described below

### runtarget

Runs a target which is contained in the same buildfile where the <antserver> task was called. This element may contain nested <property> elements for sending parameters to the target, and nested <reference> elements for sending references to the target.

#### Parameters

| Attribute | Description                    | Required |
|-----------|--------------------------------|----------|
| `target`  | The name of the target to run. | Yes.     |

### runant

Runs a target in an arbitrary buildfile on the machine where the <antserver> task was called. If a relative pathname is given, then the path of the buildfile is relative to the base directory of the project where the <antserver> task was called. This element may contain nested <property> elements for sending text parameters to the target, and nested <reference> elements for sending references to the target.

#### Parameters

| Attribute   | Description                                                                                                                                                                                                                                                | Required                                                                                                        |
|-------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| `antfile`  | The path of the ant file to run (if relative, then the filename is computed relative to the buildfile of the server task's base directory                                                                                                                  | No. Defaults to "build.xml" in the directory where the buildfile is to execute (specified by the dir attribute) |
| `target`  | The name of the target to run.                                                                                                                                                                                                                             | No. Defaults to the default target of the specified antfile.                                                    |
| `dir`  | the directory to use as a basedir for the new Ant project. Defaults to the server project's basedir, unless inheritall has been set to false, in which case it doesn't have a default value. This will override the basedir setting of the called project. | No.                                                                                                             |
| `inheritall`  | Should the target task inherit all of the server's properties. This is equivalent to the flag of the same name on the <ant> task.                                                                                                                    | No. Defaults to false                                                                                           |
| `inheritrefs`  | Should the target task inherit all of the server's references. This is equivalent to the flag of the same name on the <ant> task.                                                                                                                    | No. Defaults to false                                                                                           |

### shutdown

Instructs the <antserver> task to shut itself down. Control will return to the ANT engine and will procede as necessary in the server's buildfile.

Example
-------

```xml
<remoteant machine="localhost" port="12345">
  <runtarget target="execute.build">
    <property name="build.type" value="full" />
  </runtarget>
  <runant dir="tests" target="build.tests">
    <property name="build.type" value="full" />
    <reference refid="my.ref" torefid="inherited.ref" />
  </runtarget>
</remoteant>
```          

would be the equivalent of running the following directly on the server machine, from within the same buildfile where the <antserver> task was run

```xml
<antcall target="execute.build">
  <param name="build.type" value="full" />
</antcall>
<ant dir="tests">
  <property name="build.type" value="full" />
  <reference refid="my.ref" torefid="inherited.ref" />
</antcall>
```             

### sendfile

Sends a file from the client to the server

#### Parameters

| Attribute | Description                                                                                                                                                                                                      | Required                   |
|-----------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------|
| `file`  | The path of the file to send.                                                                                                                                                                                    | Yes.                       |
| `tofile`  | The filename where the file is to be stored on the server, if a relative path, then it is stored relative to the server project's base directory.                                                                | No. If todir is specified  |
| `tofile`  | The directory where the file is to be stored on the server, if a relative path, then it is stored relative to the server project's base directory. The name of the file will be the same name as the source file | No. If tofile is specified |
