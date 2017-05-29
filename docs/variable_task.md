---
layout: home
---
<span id="Variable"></span> Chapter 8. Variable Task
----------------------------------------------------

The Variable task provides a mutable property to Ant and works much like variable assignment in Java. This task is similar to the standard Ant Property task, except that THESE PROPERTIES ARE MUTABLE. While this goes against the standard Ant use of properties, occasionally it is useful to be able to change a property value within the build. <span class="bold"> **In general, use of this task is DISCOURAGED, and the standard Ant Property should be used if possible.** </span> Having said that, in real life I use this a lot.

Variables can be set individually or loaded from a standard properties file. A 'feature' of variables is that they can override properties, but properties cannot override variables. So if an already established property exists, its value can be reassigned by use of this task.

<span id="N108CD"></span>
**Table 8.1. Variable Task Attributes**

| Attribute | Description                                                        | Default | Required                    |
|-----------|--------------------------------------------------------------------|---------|-----------------------------|
| name      | The name of the property to set.                                   | None    | Yes, unless 'file' is used. |
| value     | The value of the property.                                         | ""      | No                          |
| unset     | Removes the property from the project as if it had never been set. | false   | No                          |
| file      | The name of a standard properties file to load variables from.     | None    | No                          |

In the following example, the property ` x` is first set to "6", then evaluated by the ` if` , and reassigned the value "12". The ` echo` task will print out 12.

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

    &lt;var name=&quot;x&quot; value=&quot;6&quot;/&gt;
    &lt;if&gt;
        &lt;equals arg1=&quot;${x}&quot; arg2=&quot;6&quot; /&gt;
        &lt;then&gt;
            &lt;var name=&quot;x&quot; value=&quot;12&quot;/&gt;
        &lt;/then&gt;
    &lt;/if&gt;
    &lt;echo&gt;${x}&lt;/echo&gt;   &lt;!-- will print 12 --&gt;
</code></pre></td>
</tr>
</tbody>
</table>

The next example shows a property being set, echoed, unset, then reset:

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

    &lt;property name=&quot;x&quot; value=&quot;6&quot;/&gt;
    &lt;echo&gt;${x}&lt;/echo&gt;   &lt;!-- will print 6 --&gt;
    &lt;var name=&quot;x&quot; unset=&quot;true&quot;/&gt;
    &lt;property name=&quot;x&quot; value=&quot;12&quot;/&gt;
    &lt;echo&gt;${x}&lt;/echo&gt;   &lt;!-- will print 12 --&gt;

</code></pre></td>
</tr>
</tbody>
</table>

The following shows some more uses of the Variable task. It is especially handy for property appending. Notice a couple of things: the property task can't override a var value, in general, you should use var with the unset attribute to change the value of a property.

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

    &lt;var name=&quot;x&quot; value=&quot;6&quot;/&gt;
    &lt;echo&gt;x = ${x}&lt;/echo&gt;   &lt;!-- print: 6 --&gt;

    &lt;var name=&quot;x&quot; value=&quot;12&quot;/&gt;
    &lt;echo&gt;x = ${x}&lt;/echo&gt;   &lt;!-- print: 12 --&gt;

    &lt;var name=&quot;x&quot; value=&quot;6 + ${x}&quot;/&gt;
    &lt;echo&gt;x = ${x}&lt;/echo&gt;   &lt;!-- print: 6 + 12 --&gt;

    &lt;var name=&quot;str&quot; value=&quot;I &quot;/&gt;
    &lt;var name=&quot;str&quot; value=&quot;${str} am &quot;/&gt;
    &lt;var name=&quot;str&quot; value=&quot;${str} a &quot;/&gt;
    &lt;var name=&quot;str&quot; value=&quot;${str} string.&quot;/&gt;
    &lt;echo&gt;${str}&lt;/echo&gt;     &lt;!-- print: I am a string. --&gt;

    &lt;var name=&quot;x&quot; value=&quot;6&quot;/&gt;
    &lt;echo&gt;x = ${x}&lt;/echo&gt;   &lt;!-- print: 6 --&gt;

    &lt;property name=&quot;x&quot; value=&quot;12&quot;/&gt;
    &lt;echo&gt;x = ${x}&lt;/echo&gt;   &lt;!-- print: 6 (property can&#39;t override) --&gt;

    &lt;var name=&quot;x&quot; value=&quot;blue&quot;/&gt;
    &lt;tstamp&gt;
        &lt;format property=&quot;x&quot; pattern=&quot;EEEE&quot;/&gt;
    &lt;/tstamp&gt;
    &lt;echo&gt;Today is ${x}.&lt;/echo&gt; &lt;!-- print: Today is blue. --&gt;

    &lt;var name=&quot;x&quot; value=&quot;&quot; unset=&quot;true&quot;/&gt;
    &lt;tstamp&gt;
        &lt;format property=&quot;x&quot; pattern=&quot;EEEE&quot;/&gt;
    &lt;/tstamp&gt;
    &lt;echo&gt;Today is ${x}.&lt;/echo&gt; &lt;!-- print: Today is Friday. --&gt;

</code></pre></td>
</tr>
</tbody>
</table>

------------------------------------------------------------------------

Copyright © 2003-2004 Ant-Contrib Project. All rights Reserved.
