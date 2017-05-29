---
layout: home
---
VerifyDesign New Project Tutorial
---------------------------------

This is by far the easiest tutorial. Before you have any code, add this to your build.xml file.

        <verifydesign jar="yourjarname.jar"
                      design="design.xml" 
                      />

Create your design.xml file from the design that is in your head or in documentation like so

      <design>
         <package name="service1" package="biz.xsoftware.service1" subpackages="include"/>
         <package name="client1"  package="biz.xsoftware.client1"  depends="service1"/>
         <package name="service2" package="biz.xsoftware.service2"/>
         <package name="client2"  package="biz.xsoftware.client2"  depends="service2" subpackages="include"/>
      </design>

From now on, when you run the build, if this is violated like service1 depending on client2 or something, the build will fail and you will catch the errors of violating the design before it is too late. You can then guarantee things like only this package will depend on the JMS technology. This way if you change JMS to something later, you know you only have to change that one package.

------------------------------------------------------------------------

Copyright © 2002-2004 Ant-Contrib Project. All rights Reserved.
