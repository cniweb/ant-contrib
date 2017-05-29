---
layout: home
---
# Limit

The Limit task is a task container (that is, it holds other tasks) and sets a time limit on how long the nested tasks are allowed to run. This is useful for unit tests that go awry, hung socket connections, or other potentially long running tasks that need to be shut off without stopping the build.

**Table 10.1. Limit Task Attributes**

| Attribute    | Description                                                                                      | Default                 | Required |
|--------------|--------------------------------------------------------------------------------------------------|-------------------------|----------|
| `maxwait`  | How long to wait for nested tasks to finish.                                                     | 180 seconds (3 minutes) | No       |
| `maxwaitunit`  | The unit for maxwait. Valid values are "millisecond", "second", "minute", "hour", "day", "week". | seconds                 | No       |
| `failonerror`  | Should the build fail if the time limit has been exceeded?                                       | false                   | No       |
| `property`  | The name of a property to set if the max wait time is exceeded.                                  | none                    | No       |
| `value`  | The value to set for the property if the max wait time is exceeded.                              | true                    | No       |
| `milliseconds`  | How long to wait in milliseconds.                                                                | 3 minutes               | No       |
| `seconds`  | How long to wait in seconds.                                                                     | 3 minutes               | No       |
| `minutes`  | How long to wait in minutes.                                                                     | 3 minutes               | No       |
| `hours`  | How long to wait in hours.                                                                       | 3 minutes               | No       |
| `days`  | How long to wait in days.                                                                        | 3 minutes               | No       |
| `weeks`  | How long to wait in weeks.                                                                       | 3 minutes               | No       |

## Examples

Neither the echo nor the fail will happen in this example. The build will continue once the time has expired.

```xml
<limit maxwait="3">
   <sleep seconds="10"/>
   <echo>This won&#39;t happen...</echo>
   <fail>This won&#39;t happen either...</fail>
</limit>
```

This is identical to the above example, but uses the convenience "seconds" attribute:

```xml
<limit seconds="3">
   <sleep seconds="10"/>
   <echo>This won&#39;t happen...</echo>
   <fail>This won&#39;t happen either...</fail>
</limit>
```

Neither the echo nor the fail will happen in this example. The build will not continue once the time has expired.

```xml
<limit maxwait="3" failonerror="true">
   <sleep seconds="10"/>
   <echo>This won&#39;t happen...</echo>
   <fail>This won&#39;t happen either...</fail>
</limit>
```

The limit will be reached and a property will be set indicating so.

```xml
<limit minutes="3" property="limit_reached">
   <sleep minutes="10"/>
   <echo>This won&#39;t happen...</echo>
   <fail>This won&#39;t happen either...</fail>
</limit>
<echo>limit_reached = ${limit_reached)</echo>
```
