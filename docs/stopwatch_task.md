---
layout: home
---
 Stopwatch
--------------------------------------

The Stopwatch task makes it easy to add performance timing to Ant targets. Stopwatches are named so that multiple watches can run simultaneously.

**Table 9.1. Stopwatch Task Attributes**

| Attribute | Description                                                                                                  | Default | Required |
|-----------|--------------------------------------------------------------------------------------------------------------|---------|----------|
| `name`  | The name for the stopwatch. The elapsed time or total time will be stored as an Ant property with this name. | None    | Yes      |
| `action`  | Valid values are "start", "stop", "elapsed", and "total".                                                    | "start" | No       |

The stopwatch is started with the "start" action. When the action is "elapsed" or "total", the running time of the stopwatch is printed out. Both "stop" and "total" stop the stopwatch and reset it to zero. "elapsed" prints out the current running time of the stopwatch without stopping it.

Example:

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

<stopwatch name="timer1"/>
<!-- do some tasks here... -->
<stopwatch name="timer1" action="elapsed"/> <!-- print the elapsed time -->
<!-- do some more tasks here... -->
<stopwatch name="timer1" action="total"/> <!-- print out the total time -->
</code></pre></td>
</tr>
</tbody>
</table>

