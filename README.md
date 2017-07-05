# ant-contrib [![Build Status](https://travis-ci.org/cniweb/ant-contrib.svg?branch=master)](https://travis-ci.org/cniweb/ant-contrib)
A home for additional useful tasks and types for Ant .
The Ant-Contrib project is a collection of tasks (and at one point maybe types and other tools) for [Apache Ant](http://ant.apache.org).

This Software is distributed under the [Apache Software License](LICENSE.md).

## Installation

First you must install Apache Ant itself, most of the Ant-Contrib tasks require Ant 1.5 or higher to work properly.
You can download Ant from [ant.apache.org](http://ant.apache.org/bindownload.cgi)

See the cc tasks for installation instructions for cpptasks.

###To install ant-contrib:

Copy ant-contrib-x.x.jar to the lib directory of your Ant installation. If you want to use one of the tasks in your own project, add the lines

    <taskdef resource="net/sf/antcontrib/antcontrib.properties"/>

to your build file.
Keep ant-contrib-x.x.jar in a separate location.
You now have to tell Ant explicitly where to find it (say in ./ant-extra-libs):

    <taskdef resource="net/sf/antcontrib/antcontrib.properties">
      <classpath>
        <pathelement location="/usr/share/java/lib/ant-contrib-x.x.jar"/>
      </classpath>
    </taskdef>

## Ducumentation

[Ant-Contrib Tasks](https://cniweb.github.io/ant-contrib)
