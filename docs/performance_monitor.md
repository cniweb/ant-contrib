---
layout: home
---

#Performance Monitoring

The "Performance Monitor" is a special Ant listener than can keep track of the amount of time that each target and task takes to execute. At the end of the build, these times will be sorted from fastest to slowest and displayed following the build output. This can be useful to pinpoint slow and/or inefficient spots in the build process and identify those areas that could benefit from optimization.

The performance listener can be used by passing a parameter to the command line for Ant:

```shell
ant -listener net.sf.antcontrib.perf.AntPerformanceListener target
```

Following is an example of the results from using the listener. The result format is projectname.targetname for targets and projectname.targetname.taskname for tasks. All times are shown to the nearest millisecond.

```shell
[danson@blackdog antelope]$ ant -listener net.sf.antcontrib.perf.AntPerformanceListener dist
Buildfile: build.xml

init:

clean:
   [delete] Deleting 170 files from /home/danson/apps/antelope/build

compile:
    [javac] Compiling 61 source files to /home/danson/apps/antelope/build

all:

-build_number:

prep_files:
   [delete] Deleting 3 files from /home/danson/apps/antelope/config
     [copy] Copying 3 files to /home/danson/apps/antelope/config

combined:
     [echo] basedir = /home/danson/apps/antelope
      [jar] Building jar: /home/danson/apps/antelope/Antelope_1.208.jar

dist:
   [delete] Deleting 4 files from /home/danson/apps/antelope/dist
      [zip] Building zip: /home/danson/apps/antelope/dist/Antelope_1.208.zip
     [echo] Created zip file.

-zip_docs:
      [zip] Building zip: /home/danson/apps/antelope/dist/Antelope_docs_1.208.zip
     [echo] Zipped docs to Antelope_docs_1.208.zip.

-zip_tasks:
      [jar] Building jar: /tmp/Antelope_tasks_1.208.jar
      [zip] Building zip: /home/danson/apps/antelope/dist/Antelope_tasks_1.208.zip
   [delete] Deleting: /tmp/Antelope_tasks_1.208.jar
     [echo] Zipped tasks to Antelope_tasks_1.208.zip.
     [copy] Copying 1 file to /home/danson/apps/antelope/dist

BUILD SUCCESSFUL
Total time: 8 seconds

-------------- Target Results -----------------------
Antelope.all: 0.000 sec
Antelope.init: 0.011 sec
Antelope.-build_number: 0.014 sec
Antelope.clean: 0.233 sec
Antelope.-zip_tasks: 0.297 sec
Antelope.prep_files: 0.311 sec
Antelope.-zip_docs: 0.546 sec
Antelope.combined: 1.290 sec
Antelope.compile: 1.724 sec
Antelope.dist: 2.162 sec

-------------- Task Results -----------------------
Antelope.init.mkdir: 0.000 sec
Antelope.init.mkdir: 0.001 sec
Antelope.dist.echo: 0.002 sec
Antelope.prep_files.delete: 0.004 sec
Antelope.combined.echo: 0.005 sec
Antelope.dist.delete: 0.006 sec
Antelope.-zip_tasks.echo: 0.007 sec
Antelope.dist.copy: 0.011 sec
Antelope.-build_number.buildnumber: 0.014 sec
Antelope.compile.copy: 0.016 sec
Antelope.prep_files.copy: 0.020 sec
Antelope.prep_files.replace: 0.071 sec
Antelope.-zip_tasks.zip: 0.122 sec
Antelope.-zip_tasks.jar: 0.161 sec
Antelope.prep_files.replace: 0.216 sec
Antelope.clean.delete: 0.233 sec
Antelope.dist.antcall: 0.421 sec
Antelope.-zip_docs.zip: 0.540 sec
Antelope.dist.antcall: 0.685 sec
Antelope.dist.zip: 1.036 sec
Antelope.combined.jar: 1.284 sec
Antelope.compile.javac: 1.708 sec

-------------- Totals -----------------------
Start time: Thu, 5 Dec 2002 17:18:30
Stop time: Thu, 5 Dec 2002 17:18:39
Total time: 8.476 sec
```
