---
layout: "home"
---

Ant-Contrib Tasks
=================

Contents
--------

-   [What's this?](#intro)
-   [Installation](#install)
-   [Tasks](toc.html)

<span id="intro">What's this?</span>
------------------------------------

The Ant-Contrib project is a collection of tasks (and at one point maybe types and other tools) for [Apache Ant](http://ant.apache.org/).

This Software is distributed under the [Apache Software License](LICENSE.txt).

<span id="install">Installation</span>
--------------------------------------

First you must install Apache Ant itself, most of the Ant-Contrib tasks require Ant 1.5 or higher to work properly, however, there are some tasks, specifically `<for>` which require Ant 1.6. You can download Ant [from Apache](http://ant.apache.org/bindownload.cgi).

Then you need the Ant-Contrib tasks themselves. As there is no release of these tasks yet, you have to build them from sources. Fortunately this is easy, check out the sources (grab the `ant-contrib` module from [CVS](http://sourceforge.net/cvs/?group_id=36177)), change into the source directory of ant-contrib and type `ant`. After Ant has completed, you'll find `ant-contrib-version.jar` in the `lib` subdirectory.

You now have the choice:

Copy `ant-contrib-version.jar` to the `lib` directory of your Ant installation, or on your CLASSPATH environment variable. If you want to use one of the tasks in your project, add the line

```xml
<taskdef resource="net/sf/antcontrib/antlib.xml"/>
```

to your build file.

Keep `ant-contrib-version.jar` in a separate location. You now have to tell Ant explicitly where to find it (say in `/usr/share/java/lib`):

```xml
<taskdef resource="net/sf/antcontrib/antlib.xml">
  <classpath>
    <pathelement location="/usr/share/java/lib/ant-contrib-version.jar"/>
  </classpath>
</taskdef>
```

If you would like to use run with Ant Version 1.5 you must use the the .properties file instead. Keep in mind that some tasks will not be available to you , such as the <for> task:

```xml
<taskdef resource="net/sf/antcontrib/antcontrib.properties">
  <classpath>
    <pathelement location="/usr/share/java/lib/ant-contrib-version.jar"/>
  </classpath>
</taskdef>
```
