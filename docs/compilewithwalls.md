---
layout: home
---
<span id="javac">Compile With Walls Task</span>
-----------------------------------------------

### Deprecated: Use [verifydesign](verifydesign.html) task instead

Creator: Dean Hiller (<dean@xsoftware.biz>)

### Description

Puts up walls in a the same source tree to ensure that designs are not violated

This task helps someone separate out packages and prevent dependencies from occurring on accident. For example, if there are three packages in one source tree

-   biz.xsoftware.mod
-   biz.xsoftware.modA
-   biz.xsoftware.modB

and modB and modA should be able to compiled independently, you can put a wall up in between the two so that if anyone adds a dependency between modA and modB, the build will break. This is particularly good if the builds are automated.

This task is for low level design. For architectural walls like client and server, I would suggest using multiple source trees and compiling those source trees independently as two different ant compile targets.

One pattern I personally like to follow can be seen on the vmaster project on sourceforge. Instructions to check it out and look at are [HERE.](https://sourceforge.net/cvs/?group_id=46703) The interesting files in vmaster to look at our here....

-   vmaster/vmasterdiff/conf/build.xml(ant file using compilewithwalls)
-   vmaster/vmasterdiff/conf/dependencies.xml(The compilewithwalls task references this file as the walls)

Looking at some of the 2nd file(dependencies.xml), one can see apis separated out for many non-GUI and GUI components in these packages

-   api.biz.xsoftware.difflib.file.\*
-   api.biz.xsoftware.difflib.dir.\*
-   more api.\* packages
-   org.vmasterdiff.gui.dirdiff.impl.\*

Looking closely at the api.\* packages, each one has a Factory. This factory uses reflection to create the implementation components. Basically, the api should not know of the implementation so there are walls around the api. Reflection to instantiate the implementation gets around these walls. My bigger components that use the smaller one's use these factories. In my design you are guaranteed these components are replaceable. Feel free to checkout vmaster and look at the factories also.

### Parameters

|                      |                                                                                                                  |                                                   |
|----------------------|------------------------------------------------------------------------------------------------------------------|---------------------------------------------------|
| **Attribute**        | **Description**                                                                                                  | **Required**                                      |
| walls                | Specifies the external dependency file to use(see example below)                                                 | Either this or a nested walls element is required |
| intermediaryBuildDir | Specifies scratch area for the compilewithwalls task to do the building and ensure dependencies are not violated | required                                          |

### Parameters specified as nested elements

This task can contain one nested javac task and one nested walls task. See the [javac task](PUT%20JAVAC%20REF%20HERE) for it's attributes and nested elements.

### Walls element

The nested walls element or the walls attribute must be specified. Only one may be used. The walls element contains nested package elements. These nested package elements have the following attributes. If any package depends on another, it must be listed after the package it depends on in the walls element.

|               |                                                                                                                                                                 |              |
|---------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------|
| **Attribute** | **Description**                                                                                                                                                 | **Required** |
| name          | A smaller nickname for the package to reference in depends                                                                                                      | Required     |
| package       | The package to compile such as biz.xsoftware.\* to include the immediate package only or biz.xsoftware.\*\* to include biz.xsoftware and all subpackages.       | Required     |
| depends       | If a package need one of the previously specified packages to compile, it's name would be added here in a comma separated list. For example depends="modA,modB" | Optional     |

### Examples

In the examples, I will show the javac as a null element, because it's use is documented in the [javac task](PUT%20JAVAC%20REF%20HERE) documentation.

#### Walls Nested Element....

      <compilewithwalls>
         <walls>
            <package name="modA" package="biz.xsoftware.mod.modA.**"/>
            <package name="modB" package="biz.xsoftware.mod.modB.*"/>
            <package name="mod" package="biz.xsoftware.mod.modA.*" depends="modA,modB"/>
         </walls>
         <javac></javac>
      </compilewithwalls>

Notice that the package named mod had to come after the packages it depended on. Now if anybody puts code in modA that uses classes in modB, the build will break telling them they are violating a design constraint. I personally have had many a devoloper accidentally put dependencies in that we agreed in the design should not be there. This includes myself. This prevents this from happening as long as someone doesn't change the ant build file....If someone does though, at least you can view the package dependencies and now what they are.

#### Walls attribute......

    These next lines would be in build.xml.....
      <compilewithwalls walls="dependencies.xml">
         <javac></javac>
      </compilewithwalls>

    These lines would be in dependencies.xml.....
      <walls>
         <package name="modA" package="biz.xsoftware.mod.modA.**"/>
         <package name="modB" package="biz.xsoftware.mod.modB.*"/>
         <package name="mod" package="biz.xsoftware.mod.modA.*" depends="modA,modB"/>
      </walls>

------------------------------------------------------------------------

Copyright © 2002-2004 Ant-Contrib Project. All rights Reserved.
