---
layout: home
---
VerifyDesign Ant Task
---------------------

-   Creator: Dean Hiller (<dean@xsoftware.biz>)
-   Contributor: Matt Inger (thanks for some really awesome changes)
-   Contributor: Anthony Y Robins

Feedback on task and documentation are welcome!

### Description

Describe your design dependencies in an xml file, and this task will enforce them so they are not violated

For example, if there are three packages in one source tree

-   biz.xsoftware.presentation
-   biz.xsoftware.business
-   biz.xsoftware.dataaccess

and naturally presentation should only depend on business package, and business should depend on dataaccess. If you define your design this way and it is violated the build will fail when the verifydesign ant task is called. For example, if I created a class in biz.xsoftware.presentation and that class depended on a class in biz.xsoftware.dataaccess, the build would fail. This ensures the design actually follows what is documented(to some degree at least). This is especially nice with automated builds

### Getting Started

Download bcel jar from this link [Bcel download](http://jakarta.apache.org/site/binindex.cgi#bcel) as this ant task uses the jar built from the [bcel project](http://jakarta.apache.org/bcel/) on jakarta. Choose a directory to put in place of the XXXXXX below and add the ant-contrib jar as well as the bcel jar to that directory. You should now be all set to use the verifydesign element in your build.xml file as well as any other ant-contrib tasks. If you want to use this with 5.0jdk, you must download the bcel from the head of cvs until something better than 5.1 is released. This version of ant-contrib will work with both 5.0jdk and 1.4 jdk. 1.3 and before is not tested.

```xml
<taskdef resource="net/sf/antcontrib/antlib.xml">
  <classpath>
    <fileset dir="XXXXXX">
      <include name="**/*.jar"/>
    </fileset>
  </classpath>
</taskdef>
```

Now, you can skip to the [VerifyDesign Legacy Project Tutorial](verifylegacytutorial.html) which guides you through how to use this task if you already have alot of existing code, or you can start with the [VerifyDesign New Project Tutorial](verifynewprojtutorial.html) where you don't have much code or any at all.

### Parameters

|                         |                                                                                                                                                                                                                                                                                                                                    |                         |
|-------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------|
| **Attribute**           | **Description**                                                                                                                                                                                                                                                                                                                    | **Required**            |
| `design`  | The file that specifies the design in xml format(Examples below)                                                                                                                                                                                                                                                                   | required                |
| `jar`  | The jar file of who's design we want to validate                                                                                                                                                                                                                                                                                   | required                |
| `circularDesign`  | I strongly encourage you not to use this. This turns on allowing circular dependencies. There is always a way to get around circular dependencies, and I suggest you use it instead of introducing a circular dependency. If you think you have found one you can't work around, send me mail and maybe I can give you some ideas. | optional(default=false) |
| `deleteFiles`  | Deletes jar/class files upon build failure to prevent usage. Use this option for new projects so the binaries are not used without the design being met.                                                                                                                                                                           | optional(default=false) |
| `fillInBuildException`  | Fills the BuildException with all the same errors reported in the logs. This is for products like cruisecontrol who only see standard ant task logs and would miss reporting these errors otherwise(ie. instead it just reports build failed with look at the design errors)                                                       | optional(default=false) |
| `needDeclarationsDefault`  | false is saying that for this particular package, no other package needs to declare that they depend on this package. It is basically saying the needDeclarations attribute in the package element in the design file is whatever the value of this attribute is in the build.xml file if the design file does not override it.    | optional(default=true)  |
| `needDependsDefault`  | false is saying that by default no package in the design file has to declare it's dependencies. It is basically saying the needDepends attribute in the package element in the design file is whatever the value of this attribute is in the build.xml file if the design file does not override it.                               | optional(default=true)  |

### Parameters specified as nested elements

No nested elements allowed

Design File
-----------

The design file is an xml based file specifying dependencies that are ok. Any dependencies not specified will not be allowed and will make sure the build fails. Examples of the contents of the design file can be found below.

### design Root Element

The root element of the design file is 'design'. Here are design's allowed subelements.

|                |                                                         |                      |
|----------------|---------------------------------------------------------|----------------------|
| **Subelement** | **Description**                                         | **Required**         |
| `package`  | subelement representing a package and it's dependencies | One or more Required |

### package SubElement

Here are package elements allowed attributes and subelements

| Attribute    | Description | Required   |
|--------------|-------------|------------|
| `name`  | A smaller nickname for the package to reference in the depends subelement or attribute                                                                                                                                                                                                                                                                                                                                                                                                                                        | Required                          |
| `package`  | The package to compile such as biz.xsoftware.presentation                                                                                                                                                                                                                                                                                                                                                                                                                                                                     | Required                          |
| `depends`  | Contains one and only one 'name' of a package to depend on(taken from name attribute above). If you want to specify more, use the depends subelement                                                                                                                                                                                                                                                                                                                                                                          | Optional(Default=no dependencies) |
| `subpackages`  | Can be set to include or exclude. Basically allows classes in subpackages to be part of the package specified.(see examples below)                                                                                                                                                                                                                                                                                                                                                                                            | Optional(default=exclude)         |
| `needdeclarations`  | Can be set to true or false. True means if other packages depend on this package, a depends subelement or attribute must exist stating the dependency. False means other packages need not declare they depend on this package. This should be used sparingly for things like java.lang. By default "java" package and all subpackages are set to false. This can be overridden if you need however so you can make sure only certain packages depend on java.util or something if you really need that. (see examples below) | Optional(default=true)            |
| `needdepends`  | Can be set to true or false. True means if this package depends on other packages, those dependencies must be defined(unless those other packages have needdeclarations set to false). False means this package must list all the packages it depends on. This is typically for legacy code where you don't want to deal with what this package depends on right now. On a new project, I never use this.                                                                                                                     | Optional(default=true)            |

|                |                                                                                             |                      |
|----------------|---------------------------------------------------------------------------------------------|----------------------|
| **Subelement** | **Description**                                                                             | **Required**         |
| `depends`  | Contains one and only one 'name' of a package to depend on(taken from name attribute above) | One or more Optional |

### depends SubElement

Contents contain the 'name' of a package found in the package element's name attribute

### Examples

#### Ant's build.xml File

      <verifydesign jar="application.jar" design="design.xml"/>

That is simple enough. application.jar is the jar I am verifying the design of. design.xml contains the design I want the jar file to adhere to. There is no need to include third party jars(unless you want to verify their design which you shouldn't). The design file can still define what your stuff depends on in those third party libraries without requiring those libraries to be present. If it does not adhere to that design, the build fails. Examples of a design.xml can be found further below.

Another example equivalent to the above is below. verifydesign takes a path like structure(see ant documentation). **I highly advise breaking the design on purpose and verifying that the build fails. This ensures you have the ant task set up correctly.**

```xml
<verifydesign design="design.xml">
  <path>
    <pathelement jar="application.jar"/>
  </path>
</verifydesign>
```
One last example would be like so

```xml
<verifydesign design="design.xml">
  <path>
    <fileset dir="${classesDir}">
        <include name="**/*.class"/>
    </fileset>
  </path>
</verifydesign>
```

Now let's move on to define the contents of design.xml file.

#### design.xml File

These lines would be in dependencies.xml.....

```xml
<design>
  <package name="alljavax" package="javax" subpackages="include" needdeclarations="false"/>
  <package name="util" package="biz.xsoftware.util" subpackages="include"/>
  <package name="dataaccess" package="biz.xsoftware.dataaccess"/>
  <package name="business" package="biz.xsoftware.business" depends="dataaccess"/>
  <package name="presentation" package="biz.xsoftware.presentation">
    <depends>business</depends>
    <depends>util</depends>
  </package>
</design>
```

Notice in this example, if biz.xsoftware.dataaccess.XYZClass depended on biz.xsoftware.util.Util, the build would fail since that package dependency is not defined. Similarly, any class in biz.xsoftware.presentation cannot depend on any class in biz.xsoftware.dataaccess

Also, notice that biz.xsoftware.presentation.Gui is allowed to depend on biz.xsoftware.util.pres.ClassInSubpackage because subpackages is set to include. This allows subpackages of biz.xsoftware.util to also be included in the design without having to define every subpackage.

Lastly, notice the first line so javax and all javax subpackages can be depended on without declaring them. Use this sparingly though as sometimes you might want to isolate dependencies like depending on JMX to a certain package. For example, you may want only biz.xsoftware.management to depend on JMX and nothing else to depend on it. If you declare the same declaration I declared here for javax, you will not be able to guarantee that.

#### The wad design.xml file

If you really want to, you could design for a wad. This is not suggested and if you want to do this, don't use this ant task please.

```xml
<design>
  <package name="wad" package="<default package>" subpackages="include"/>
</design>
```
#### Including subpackages design.xml

```xml
<design>
  <package name="service1" package="biz.xsoftware.service1" subpackages="include"/>
  <package name="client1"  package="biz.xsoftware.client1"  depends="service1"/>
  <package name="service2" package="biz.xsoftware.service2"/>
  <package name="client2"  package="biz.xsoftware.client2"  depends="service2" subpackages="include"/>
</design>
```
Note that here for service 1, classes in package biz.xsoftware.client1 can depend on any classes in biz.xsoftware.service1 and can also depend on classes in subpackages of biz.xsoftware.service1.

Note that for service 2, classes in biz.xsoftware.client2 and client2's subpackages are all allowed to depend on classes in biz.xsoftware.service2.

#### One Design Note

One big note to consider, there is one design dependency that verifydesign cannot see from the class file. This is due to the String constants(This only happens with static final **Strings** and static final **primitives** being compiled into the class file. Here is example code demonstrating this task cannot catch these dependencies....

```java
public class Client {
    public void fakeMethod() {
          String s = Dependency.CONSTANT;  //verifydesign task can't tell this depends on
                                          //DependencyClass as that info is lost after compiling
    }
}

public class DependencyClass {
    public static final String CONSTANT = "asdf"; 
}
```
