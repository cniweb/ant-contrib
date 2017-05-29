---
layout: home
---
ShellScript
===========

Task definition for the `shellscript` task. This task allows the user to execute a script against a particular shell program on a machine. It is an extension of the "exec" task, and as such, supports the same attributes. One can however use "shell" instead of "executable". Also the "command" attribute is not allowed. See the ANT documentation for a description of the <exec> task parameters.

Parameters
----------

| Attribute | Description  | Required |
|-----------|--------------|----------|
| `shell` | The name of the shell executable which is to be executed. This shell must support taking a single parameter whose value is a script file which is to be executed. | Yes |
| `executable` | Same as “shell” |  |
| `tmpsuffix` | The contents of the script are placed in a temporary file. This attribute is the extension to use. <strong>note:</strong> The value must contain any dots required. This attribute is usefull for using script files with windows | No |
| `inputstring` | This is placed in the script file. | No |

Nested Text
-----------

Any nested text is treated as the contents of the script that is to be executed within the shell. Embedded ant properties will be converted.

Examples
--------

```xml
<shellscript shell="bash" dir="${src.mib.dir}">
    mibgen -i ../include mib.mib -c ${build.gen.dir}/generated.cpp
    mibgen -i ../include mib.mib -h ${build.gen.dir}/generated.h
</shellscript>

<shellscript shell="sed" outputproperty="sed.output">
  <arg value="-e"/>
  <arg value="s/FOO/BAR/g"/>
  FOO bar bar bar FOO bar bar
</shellscript>

<shellscript shell="cmd.exe" tmpsuffix=".bat">
  <arg value="/c"/>
  <arg value="call"/>
  echo hello world
</shellscript>

<shellscript shell="bash"
  dir="${build.bin.dir}"
  inputstring="ls -rt | tail -n 1"
  outputproperty="last.bin.file"/>

<shellscript executable="perl">
  print STDOUT "Hello World!\n";
</shellscript>

<shellscript shell="sh" dir="${thirdparty.dist.dir}/lib">
  rm *.so
  for file in *.0
  do
    x=`echo $file | sed -e's/.0.1.0//'`
    ln -s $file $x
  done
</shellscript>
```

**Warning:**

One should be carefull in using *shellscript*, as overuse will make your build files difficult to understand, to maintain and to support multiplatform builds. Use of *cygwin* in a windows environment will help. However one should strive to use the java tasks whereever possible.
