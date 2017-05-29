---
layout: home
---
OutOfDate
=========

Task definition for the `outofdate` task. This is an extension of uptodate which allows multible targets and contains an embedded &lt;parallel&gt; or &lt;sequential&gt; element. If any of the target file's dates are earlier than any of the source file's dates, then the specified &lt;parallel&gt; or &lt;sequential&gt; block is executed. The task may also contain mappers to map source files to corresponding target files.

Parameters
----------

| Attribute         | Description                                                                                                                                                | Required               |
|-------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------|
| property          | The name of the property to set to the contents of the `value` parameter if any of the target files are out of date                                        | No                     |
| value             | The value to set the property specified by the parameter `property` to, if any of the target files are out of date                                         | No, defaults to "true" |
| force             | Force outofdate ("true"/"false"). Default is "false".                                                                                                      | No                     |
| verbose           | Set vebose logging level for this task ("true"/"false"). Default is "false".                                                                               | No                     |
| outputsources     | The name of a property to set containing the sources that are newer that their corresponding targets.                                                      | No                     |
| outputtargets     | The name of a property to set containing the targets that are outofDate with respect to their corresponding sources.                                       | No                     |
| alltargets        | The name of a property to set containing all the targets. This is usefull for debugging mapper nested elements.                                            | No                     |
| separator         | The separator used to separate the files in the properties above. If a filename contains the separator, double quotes will be placed aroudnd the filename. | No, defaults to “ “    |
| outputsourcespath | The id of a path to create containing the source files that are outofdate.                                                                                 | No                     |
| outputtargetspath | The id of a path to create containing the target files that need to be updated.                                                                            | No                     |
| alltargetspath    | The id of a path to create containing all the target files.                                                                                                | No                     |

Attributes specified as nested elements
---------------------------------------

**sourcefiles** - The list of files which are source files. This element is required.
**targetfiles** - The list of files which are target files.

Both of these nested elements are [Path](http://ant.apache.org/manual/using.html#path) elements which are are used to select sets or lists of files or directories

The *sourcefiles* may contain no files. In this case, outofdate will check the existance of the *targetfiles*.

**mapper –** This is used to map source files to target files.

As well as the regular attributes for mapper, there is a extra attribute to specify the relative directory of the sources.

|               |                                                                                      |              |
|---------------|--------------------------------------------------------------------------------------|--------------|
| **Attribute** | **Description**                                                                      | **Required** |
| dir           | The directory to the sources are relative to for the mapper. Default is ${base.dir}. | No           |

There may be a number of mapper nested elements.

**deletetargets –** This is used to delete targets if the corresponding sources are outofdate.

|               |                                                                                                                                                                                                                                                                                                                                  |              |
|---------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------|
| **Attribute** | **Description**                                                                                                                                                                                                                                                                                                                  | **Required** |
| all           | Whether to delete all the targets ("true"/"false"). Defaults to "false".                                                                                                                                                                                                                                                         | No           |
| quiet         | Do not display diagnostic messages when deleting targets ("true"/ "false"). Defaults to false. When set to "true", if a file or directory cannot be deleted, no error is reported. This setting emulates the -f option to the Unix rm command. Default is "false". Setting this to "true" implies setting failonerror to "false" | No           |
| failonerror   | Controls whether an error (such as a failure to delete a file) stops the build or is merely reported to the screen. Only relevant if quiet is "false". Default is "true". Controls whether a failure to delete a target stops the build or is merely reported to the screen.                                                     | No           |

Examples
--------

The following example creates the file ${jrun.file} if is older that build.xml, or any file in ${lib.dir}.

            <outofdate>
              <sourcefiles>
                <pathelement path="build.xml"/>
                <fileset dir="${lib.dir}"/>
              </sourcefiles>
              <targetfiles path="${jrun.file}"/>
              <sequential>
                <mkdir dir="${build.bin.dir}"/>
                <echo file="${jrun.file}" message="java -cp ${jrun.path} $*"/>
                <chmod file="${jrun.file}" perm="ugo+rx"/>
              </sequential>
            </outofdate> 

The following example check the generated files, MODULE.IDS, acme\_agent\_mib.h, acme\_agent\_mib.cpp are older that miblist.txt, or any file in ${mib.src}, and if so an embedded shellScript is invoked to update the files.

            <outofdate>
              <sourcefiles>
                <pathelement path="${agent.src}/miblist.txt"/>
                <fileset dir="${mib.src}"/>
              </sourcefiles>
              <targetfiles>
                <pathelement path="${rep}/MODULE.IDS"/>
                <pathelement path="${gen-agent}/acme_agent_mib.h"/>
                <pathelement path="${gen-agent}/acme_agent_mib.cpp"/>
              </targetfiles>
              <sequential>
                <shellscript shell="bash" dir="${agent.src}">
                        classname=com.agentpp.agentgen.AgentGenConsole
                        h1=${gen-agent}/acme_agent_mib.x
                        ag() {
                            java -cp ${lib.dir}/agentgen.jar $classname ${rep} $@
                        }
                        ag initialize
                        ag load miblist.txt
                        ag generate ACME-AGENT-MIB h > $h1
                        (head -16 $h1; echo "using namespace Agentpp;";
                        tail +16 $h1) > ${gen-agent}/acme_agent_mib.h
                        ag generate ACME-AGENT-MIB c >\
                            ${gen-agent}/acme_agent_mib.cpp
                </shellscript>
              </sequential>
            </outofdate>

The following example sets the project *manual.outofdate* if any of the xml files are newer than index.html, or if any of the xml files are newer than their corresponding .html file. A path identified by *sources.path*, is created which contains the sources that fullfilled these conditions.

        <outofdate property="manual.outofdate" outputsourcespath="sources.path">
          <sourcefiles>
            <fileset dir="${src.manual}" includes="**/*.xml"/>
          </sourcefiles>
          <targetfiles path="${doc.manual}/index.html"/>
          <mapper type="glob" dir="${src.manual}" from="*.xml" to="${doc.manual}/*.html"/>
        </outofdate>

The following assumes that there is a program called *gengrammer* that takes a grammer file as an input and generates a .h and a .c file in the current directory.

      <outofdate property="manual.outofdate"
                 outputsources="grammer.sources">
        <sourcefiles>
          <fileset dir="${src.grammer}" includes="**/*.y"/>
        </sourcefiles>
        <mapper type="glob" dir="${src.grammer}" from="*.y" to="${gen.grammer}/*.c"/>
        <mapper type="glob" dir="${src.grammer}" from="*.y" to="${gen.grammer}/*.h"/>
        <sequential>
          <shellscript shell="bash">
            cd ${gen.grammer}
            for g in ${grammer.sources}
            do
                gengrammer $g
            done
          </shellscript>
        </sequential>
      </outofdate>

