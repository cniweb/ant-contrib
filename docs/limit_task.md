---
layout: home
---
<span id="Limit"></span> Limit
------------------------------

The Limit task is a task container (that is, it holds other tasks) and sets a time limit on how long the nested tasks are allowed to run. This is useful for unit tests that go awry, hung socket connections, or other potentially long running tasks that need to be shut off without stopping the build.

<span id="N1096A"></span>
**Table 10.1. Limit Task Attributes**

| Attribute    | Description                                                                                      | Default                 | Required |
|--------------|--------------------------------------------------------------------------------------------------|-------------------------|----------|
| maxwait      | How long to wait for nested tasks to finish.                                                     | 180 seconds (3 minutes) | No       |
| maxwaitunit  | The unit for maxwait. Valid values are "millisecond", "second", "minute", "hour", "day", "week". | seconds                 | No       |
| failonerror  | Should the build fail if the time limit has been exceeded?                                       | false                   | No       |
| property     | The name of a property to set if the max wait time is exceeded.                                  | none                    | No       |
| value        | The value to set for the property if the max wait time is exceeded.                              | true                    | No       |
| milliseconds | How long to wait in milliseconds.                                                                | 3 minutes               | No       |
| seconds      | How long to wait in seconds.                                                                     | 3 minutes               | No       |
| minutes      | How long to wait in minutes.                                                                     | 3 minutes               | No       |
| hours        | How long to wait in hours.                                                                       | 3 minutes               | No       |
| days         | How long to wait in days.                                                                        | 3 minutes               | No       |
| weeks        | How long to wait in weeks.                                                                       | 3 minutes               | No       |

Examples:

Neither the echo nor the fail will happen in this example. The build will continue once the time has expired.

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

&lt;limit maxwait=&quot;3&quot;&gt;
   &lt;sleep seconds=&quot;10&quot;/&gt;
   &lt;echo&gt;This won&#39;t happen...&lt;/echo&gt;
   &lt;fail&gt;This won&#39;t happen either...&lt;/fail&gt;
&lt;/limit&gt;
</code></pre></td>
</tr>
</tbody>
</table>

This is identical to the above example, but uses the convenience "seconds" attribute:

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

&lt;limit seconds=&quot;3&quot;&gt;
   &lt;sleep seconds=&quot;10&quot;/&gt;
   &lt;echo&gt;This won&#39;t happen...&lt;/echo&gt;
   &lt;fail&gt;This won&#39;t happen either...&lt;/fail&gt;
&lt;/limit&gt;
</code></pre></td>
</tr>
</tbody>
</table>

Neither the echo nor the fail will happen in this example. The build will not continue once the time has expired.

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

&lt;limit maxwait=&quot;3&quot; failonerror=&quot;true&quot;&gt;
   &lt;sleep seconds=&quot;10&quot;/&gt;
   &lt;echo&gt;This won&#39;t happen...&lt;/echo&gt;
   &lt;fail&gt;This won&#39;t happen either...&lt;/fail&gt;
&lt;/limit&gt;
</code></pre></td>
</tr>
</tbody>
</table>

The limit will be reached and a property will be set indicating so.

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

&lt;limit minutes=&quot;3&quot; property=&quot;limit_reached&quot;&gt;
   &lt;sleep minutes=&quot;10&quot;/&gt;
   &lt;echo&gt;This won&#39;t happen...&lt;/echo&gt;
   &lt;fail&gt;This won&#39;t happen either...&lt;/fail&gt;
&lt;/limit&gt;
&lt;echo&gt;limit_reached = ${limit_reached)&lt;/echo&gt;</code></pre></td>
</tr>
</tbody>
</table>

------------------------------------------------------------------------

Copyright © 2003-2004 Ant-Contrib Project. All rights Reserved.
