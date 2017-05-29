---
layout: home
---
Throw
=====

Extension of Ant's built-in `<fail>` task that can throw an exception that is given by a reference. This may be useful if you want to rethrow the exception that has been caught by a `<trycatch>` task in the `<catch>` block.

Parameters
----------

| Attribute | Description                     | Required |
|-----------|---------------------------------|----------|
| `refid`  | Id of the referenced exception. | No.      |

In addition, all attributes of the [`<fail>` task](http://jakarta.apache.org/ant/manual/CoreTasks/fail.html) are supported.
