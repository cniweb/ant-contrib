---
layout: home
---
If
==

Perform some tasks based on whether a given condition holds true or not.

This task is heavily based on the Condition framework that can be found in Ant 1.4 and later, therefore it cannot be used inconjunction with versions of Ant prior to 1.4. Due to numeruos bugs in Ant 1.4(.1) that affect this task, we recommend to use Ant 1.5 or later.

Parameters
----------

This task doesn't have any attributes, the condition to test is specified by a nested element - see the documentation of your `<condition>` task (see [the online documentation](http://ant.apache.org/manual/CoreTasks/condition.html) for example) for a complete list of nested elements.

Just like the `<condition>` task, only a single condition can be specified - you combine them using `<and>` or `<or>` conditions.

In addition to the condition, you can specify three different child elements, `<elseif>`, `<then>` and `<else>`. All three subelements are optional. Both `<then>` and `<else>` must not be used more than once inside the if task. Both are containers for Ant tasks, just like Ant's `<parallel>` and `<sequential>` tasks - in fact they are implemented using the same class as Ant's `<sequential>` task.

The `<elseif>` behaves exactly like an `<if>` except that it cannot contain the `<else>` element inside of it. You may specify as may of these as you like, and the order they are specified is the order they are evaluated in. If the condition on the `<if>` is false, then the first `<elseif>` who's conditional evaluates to true will be executed. The `<else>` will be executed only if the `<if>` and all `<elseif>` conditions are false.

Example
-------

    <if>
     <equals arg1="${foo}" arg2="bar" />
     <then>
       <echo message="The value of property foo is bar" />
     </then>
     <else>
       <echo message="The value of property foo is not bar" />
     </else>
    </if>

    <if>
     <equals arg1="${foo}" arg2="bar" />
     <then>
       <echo message="The value of property foo is 'bar'" />
     </then>

     <elseif>
      <equals arg1="${foo}" arg2="foo" />
      <then>
       <echo message="The value of property foo is 'foo'" />
      </then>
     </elseif>


     <else>
       <echo message="The value of property foo is not 'foo' or 'bar'" />
     </else>
    </if>


