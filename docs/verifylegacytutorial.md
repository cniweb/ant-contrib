---
layout: home
---
VerifyDesign Legacy System Tutorial
-----------------------------------

If you have a legacy system, it can be overwhelming as a typical system is a mess when it comes to package dependencies. This tutorial shows a way to ease into the verifydesign task instead of fixing everything all at once.

First, in your build.xml file, use this line to verify your jar(or you can modify it to verify multiple jars)

```xml
<verifydesign jar="yourjarname.jar"
              design="design.xml"/>
```

Now is the hardest part, go ahead and define every package and set the needDepends attribute to false for all of them so your design.xml should look like so

```xml
<design>
  <package name="first" package="your.first.package" needDepends="false"/>
  <package name="second" package="your.second.package" needDepends="false"/>
  <package name="third" package="your.third.package" needDepends="false"/>
  <package name="fourth" package="your.fourth.package" needDepends="false"/>
  <package name="fifth" package="your.fifth.package" needDepends="false"/>
</design>
```

Please give them better names then first, second, third, etc. You may have 100 packages on some projects and this may take a while to get started, but keep in mind once you are done with this, you are done with the majority of the work and the build will pass once you are done with this too! Now comes the fun part, learning about your design. Take a package that you want to start restricting dependencies on and erase the needDepends(by default it's value will be true). Let's take your.first.package and create the new design.xml file like so...

```xml
<design>
  <package name="first" package="your.first.package"/>
  <package name="second" package="your.second.package" needDepends="false"/>
  <package name="third" package="your.third.package" needDepends="false"/>
  <package name="fourth" package="your.fourth.package" needDepends="false"/>
  <package name="fifth" package="your.fifth.package" needDepends="false"/>
</design>
```
Now we run the build and we get errors that your.first.package depends on second, third, and fourth. Let's pretend we only wanted to depend on second and third. We then change our design file to so...

```xml
<design>
  <package name="first" package="your.first.package">
      <depends>second</depends>
      <depends>third</depends>
  </package>
  <package name="second" package="your.second.package" needDepends="false"/>
  <package name="third" package="your.third.package" needDepends="false"/>
  <package name="fourth" package="your.fourth.package" needDepends="false"/>
  <package name="fifth" package="your.fifth.package" needDepends="false"/>
</design>
```

Now we run the build and clean up all the code so that first doesn't depend on fourth anymore. This first step can typically take a full release if you are doing this in the margins. That is ok and now forever your.first.package will only depend on second and third until the design file is changed. You have made major progress. I would suggest a package a release. It can clean up dependencies and you will start finding it can be easier to add more and more features and not end up with a wad or mess on your hands. Good luck designing. Refactoring a legacy system can be very challenging and very long with or without this task. This ant task guarantees that you are actually heading in your defined direction. Whether the direction is correct or not is another story :).
