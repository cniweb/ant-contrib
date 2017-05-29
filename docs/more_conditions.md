---
layout: home
---
<span id="conditionals"></span> More Conditions
-----------------------------------------------

These conditions are suitable for use in the &lt;bool&gt; element. Unfortunately, they cannot be used in the &lt;condition&gt; task, although all conditions for the &lt;condition&gt; task can be used with the &lt;bool&gt; and the &lt;bool&gt; can be used anywhere that &lt;condition&gt; can be used.

#### <span id="N10708"></span> IfPropertyTrue

Given a property name, tests whether the value for that property equals "true" (or "yes" or "on").

<span id="N1070F"></span>
**Table 5.2. IfPropertyTrue Attributes**

| Attribute | Description                                  | Required |
|-----------|----------------------------------------------|----------|
| property  | The name of a property to test the value of. | Yes      |

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

&lt;ispropertytrue property=&quot;myprop&quot;/&gt;
&lt;ispropertytrue property=&quot;${someprop}&quot;/&gt;
</code></pre></td>
</tr>
</tbody>
</table>

#### <span id="N1072C"></span> IfPropertyFalse

Given a property name, tests whether the value for that property equals "false" (or "no" or "off").

<span id="N10733"></span>
**Table 5.3. IfPropertyFalse Attributes**

| Attribute | Description                                  | Required |
|-----------|----------------------------------------------|----------|
| property  | The name of a property to test the value of. | Yes      |

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

&lt;ispropertyfalse property=&quot;myprop&quot;/&gt;
&lt;ispropertyfalse property=&quot;${someprop}&quot;/&gt;
</code></pre></td>
</tr>
</tbody>
</table>

#### <span id="N10750"></span> StartsWith

Given a property name, tests whether the value for that property starts with a specified string.

<span id="N10757"></span>
**Table 5.4. StartsWith Attributes**

| Attribute | Description                               | Required |
|-----------|-------------------------------------------|----------|
| string    | The string to test.                       | Yes      |
| with      | Check if 'string' starts with this value. | Yes      |

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

&lt;startswith string=&quot;abcdefg&quot; with=&quot;abc&quot;/&gt;
&lt;startswith string=&quot;${myprop}&quot; with=&quot;foo&quot;/&gt;
</code></pre></td>
</tr>
</tbody>
</table>

#### <span id="N1077B"></span> EndsWith

Given a property name, tests whether the value for that ends with with a specified string.

<span id="N10782"></span>
**Table 5.5. EndsWith Attributes**

| Attribute | Description                             | Required |
|-----------|-----------------------------------------|----------|
| string    | The string to test.                     | Yes      |
| with      | Check if 'string' ends with this value. | Yes      |

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

&lt;endswith string=&quot;abcdefg&quot; with=&quot;efg&quot;/&gt;
&lt;endswith string=&quot;${myprop}&quot; with=&quot;bar&quot;/&gt;
</code></pre></td>
</tr>
</tbody>
</table>

#### <span id="N107A6"></span> IsGreaterThan

Tests whether the first argument is greater than the second argument. Will automatically treat the arguments as numbers if both arguments consists of only the characters 0 through 9 and optionally a decimal point. Otherwise, a String comparison is used.

<span id="N107AD"></span>
**Table 5.6. IsGreaterThan Attributes**

| Attribute | Description          | Required |
|-----------|----------------------|----------|
| arg1      | The first argument.  | Yes      |
| arg2      | The second argument. | Yes      |

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

&lt;!-- evaluates to true --&gt;
&lt;isgreaterthan arg1=&quot;6.02&quot; arg2=&quot;4&quot;/&gt;

&lt;!-- evaluates to false --&gt;
&lt;isgreaterthan arg1=&quot;bar&quot; arg2=&quot;foo&quot;/&gt;
</code></pre></td>
</tr>
</tbody>
</table>

#### <span id="N107D1"></span> IsLessThan

Tests whether the first argument is less than the second argument. Will automatically treat the arguments as numbers if both arguments consists of only the characters 0 through 9 and optionally a decimal point. Otherwise, a String comparison is used.

<span id="N107D8"></span>
**Table 5.7. IsLessThan Attributes**

| Attribute | Description          | Required |
|-----------|----------------------|----------|
| arg1      | The first argument.  | Yes      |
| arg2      | The second argument. | Yes      |

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

&lt;!-- evaluates to false --&gt;
&lt;islessthan arg1=&quot;6.02&quot; arg2=&quot;4&quot;/&gt;

&lt;!-- evaluates to true --&gt;
&lt;islessthan arg1=&quot;bar&quot; arg2=&quot;foo&quot;/&gt;
</code></pre></td>
</tr>
</tbody>
</table>

