---
layout: home
---
<span id="math"></span> Math
----------------------------

The Math task provides support for all the basic mathematical operations provided by the java.lang.Math and java.lang.StrictMath classed. It supports int, long, float and double data types. Nesting of operations is supported to allow computation of formulas like (6 + (7.25 \* 3.9))/(2 \* 3 \* 3) or calculating the area of a circle given a radius (I'm sure this comes up often in builds controlled by Ant!).

In addition to the operations provided by the java.lang.Math and java.lang.StrictMath classes, the Math task provides several additional operations: "add", "subtract", "multiply", "divide", and "mod", which duplicate the basic Java mathematical operations "+", "-", "\*", "/", and "%", respectively. In fact, either notation can be used, that is, the operation can be set to "add" or "+", depending only on which you feel is more convenient.

<span id="N109AE"></span>
**Table 11.1. Math Task Attributes**

| Attribute | Description                                                                                                                                                             | Default | Required                           |
|-----------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|------------------------------------|
| result    | The name of the property to hold the result of the operation.                                                                                                           | None    | Yes                                |
| datatype  | Sets the datatype of the calculation. Allowed values are "int", "long", "float", or "double". Optional, if used, will be applied to all numbers in this math operation. | double  | No                                 |
| strict    | If true, use the methods in the java.lang.StrictMath class.                                                                                                             | false   | No                                 |
| operation | If used, any nested Ops will be ignored. This is for convenience for simple calculations.                                                                               | None    | No                                 |
| operand1  | A number to use with the operation specified in the 'operation' attribute.                                                                                              | None    | Depends on the specific operation. |
| operand2  | A number to use with the operation specified in the 'operation' attribute.                                                                                              | None    | Depends on the specific operation. |

The 'result' property is reusable.

The Math task supports nested "Op" elements. An Op element represents single mathematical operation, such as "min" or "add". As an alternate, if using Ant 1.5+, you can specify the operation in the tag name itself. However, you must use the text name (+,-,/,\*,% are not permitted as tag names) ``
      <radians>
         <num value="90" />
      </radians>

instead of ``
      <op op="radians">
         <num value="90" />
      </op>

<span id="N109FB"></span>
**Table 11.2. Op Attributes**

| Attribute                                                                                                                          | Description                                                                                                                                                                                                                                                                                                                                 | Default                                                                        | Required                               |
|------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|----------------------------------------|
| op                                                                                                                                 | Set the name of this operation. Allowed values are one of the method names from java.lang.Math or java.lang.StrictMath, or one of "add", "subtract", "multiply", "divide", or "mod" (or "+", "-", "\*", "/", or "%", respectively). "toRadians" and "toDegrees" can be represented by "radians" and "degrees", respectively, as a shorthand | None                                                                           | Yes, if not specified in the tag name. |
| datatype                                                                                                                           | Sets the datatype of this calculation. Allowed values are "int", "long", "float", or "double". Optional, default is "double". If the parent Math task has a datatype set, this value will be ignored and the datatype specifed in the task will be used.                                                                                    | "int"                                                                          | No                                     |
| arg1, arg2, arg3, arg4, arg5/td&gt;                                                                                                
 The arguments for this operation. This is a shorthand to avoid having to use nested elements when performing a simple calculation.  | None                                                                                                                                                                                                                                                                                                                                        | No. However, these attributes are mutually exclusive with the and subelements. |                                        |

The Op element supports nested "Op" elements and nested "Num" elements. A Num represents a number. When an Op is nested in another Op, the result of the Op is treated as a Num. The nested elements can be any combination of Op (short form included as mentioned above) or Num as appropriate for the formula being calculated. Most of the operations provided by java.lang.Math and java.lang.StrictMath operate on one or two numbers. The "+", "-", "\*", "/", and "%" operations can task any number of nested numbers.

<span id="N10A23"></span>
**Table 11.3. Num Attributes**

| Attribute | Description                                                                                                                                                                                                                                                                                                                          | Default | Required |
|-----------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|----------|
| value     | Set the value for this number. Must be able to parse to the datatype set by the parent element or the default datatype set by the task. Two special numbers, pi and e, can be represented by PI and E respectively. ("PI" is the ratio of the diameter of a circle to its radius, "E" is Euler's e, the base for natural logrithms.) | None    | Yes      |
| datatype  | Sets the datatype of this number. Allowed values are "int", "long", "float", or "double". Optional, default is "double". If the parent Math task has a datatype set, this value will be ignored and the datatype specifed in the task will be used.                                                                                  | double  | No       |

Some examples:

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

    &lt;var name=&quot;op1&quot; value=&quot;12&quot;/&gt;
    &lt;var name=&quot;op2&quot; value=&quot;6&quot;/&gt;
    &lt;var name=&quot;op&quot; value=&quot;+&quot;/&gt;

    &lt;!-- demo plus --&gt;
    &lt;math result=&quot;result&quot; operand1=&quot;${op1}&quot; operation=&quot;${op}&quot; operand2=&quot;${op2}&quot; datatype=&quot;int&quot;/&gt;
    &lt;echo&gt;${op1} ${op} ${op2} = ${result}&lt;/echo&gt;
    &lt;assert name=&quot;result&quot; value=&quot;18&quot;/&gt;

    &lt;!-- demo reusing result --&gt;
    &lt;math result=&quot;result&quot; operand1=&quot;${result}&quot; operation=&quot;${op}&quot; operand2=&quot;${op2}&quot; datatype=&quot;int&quot;/&gt;
    &lt;echo&gt;${op1} ${op} ${op2} = ${result}&lt;/echo&gt;
    &lt;assert name=&quot;result&quot; value=&quot;24&quot;/&gt;

    &lt;!-- demo minus --&gt;
    &lt;var name=&quot;op&quot; value=&quot;-&quot;/&gt;
    &lt;math result=&quot;result&quot; operand1=&quot;${op1}&quot; operation=&quot;${op}&quot; operand2=&quot;${op2}&quot; datatype=&quot;int&quot;/&gt;
    &lt;echo&gt;${op1} ${op} ${op2} = ${result}&lt;/echo&gt;
    &lt;assert name=&quot;result&quot; value=&quot;6&quot;/&gt;

    &lt;!-- demo multiply --&gt;
    &lt;var name=&quot;op&quot; value=&quot;*&quot;/&gt;
    &lt;math result=&quot;result&quot; operand1=&quot;${op1}&quot; operation=&quot;${op}&quot; operand2=&quot;${op2}&quot; datatype=&quot;int&quot;/&gt;
    &lt;echo&gt;${op1} ${op} ${op2} = ${result}&lt;/echo&gt;
    &lt;assert name=&quot;result&quot; value=&quot;72&quot;/&gt;

    &lt;!-- demo divide --&gt;
    &lt;var name=&quot;op&quot; value=&quot;/&quot;/&gt;
    &lt;math result=&quot;result&quot; operand1=&quot;${op1}&quot; operation=&quot;${op}&quot; operand2=&quot;${op2}&quot; datatype=&quot;int&quot;/&gt;
    &lt;echo&gt;${op1} ${op} ${op2} = ${result}&lt;/echo&gt;
    &lt;assert name=&quot;result&quot; value=&quot;2&quot;/&gt;

    &lt;!-- demo modulo --&gt;
    &lt;var name=&quot;op&quot; value=&quot;%&quot;/&gt;
    &lt;math result=&quot;result&quot; operand1=&quot;${op1}&quot; operation=&quot;${op}&quot; operand2=&quot;${op2}&quot; datatype=&quot;int&quot;/&gt;
    &lt;echo&gt;${op1} ${op} ${op2} = ${result}&lt;/echo&gt;
    &lt;assert name=&quot;result&quot; value=&quot;0&quot;/&gt;

    &lt;!-- demo calculating the area of a circle --&gt;
    &lt;!-- first, calculate the radius --&gt;
    &lt;math result=&quot;radius&quot;&gt;  &lt;!-- defaults to double datatype --&gt;
        &lt;op type=&quot;*&quot;&gt;
            &lt;num value=&quot;1&quot;/&gt;
            &lt;num value=&quot;2&quot;/&gt;
            &lt;num value=&quot;3&quot;/&gt;
            &lt;num value=&quot;4&quot;/&gt;
            &lt;num value=&quot;5&quot;/&gt;
        &lt;/op&gt;
    &lt;/math&gt;
    &lt;echo&gt; 1 * 2 * 3 * 4 * 5 = ${radius}&lt;/echo&gt;

    &lt;!-- now calculate the area --&gt;
    &lt;math result=&quot;area&quot; precision=&quot;float&quot;&gt;
        &lt;op type=&quot;*&quot;&gt;
            &lt;num value=&quot;PI&quot;/&gt;
            &lt;op type=&quot;pow&quot;&gt;
                &lt;num value=&quot;${radius}&quot;/&gt;
                &lt;num value=&quot;2&quot;/&gt;
            &lt;/op&gt;
        &lt;/op&gt;
    &lt;/math&gt;
    &lt;echo&gt;area = PI * radius ^ 2 = ${area}&lt;/echo&gt;

    &lt;!-- demo calculating a random number between 0 and 100 --&gt;
    &lt;math result=&quot;result&quot;&gt;
        &lt;op op=&quot;rint&quot;&gt;
            &lt;op op=&quot;*&quot;&gt;
                &lt;num value=&quot;100&quot;/&gt;
                &lt;op op=&quot;random&quot;/&gt;
            &lt;/op&gt;
        &lt;/op&gt;
    &lt;/math&gt;
    &lt;echo&gt;a random number between 0 and 100: ${result}&lt;/echo&gt;

    &lt;!-- demo another multiplication --&gt;
    &lt;math result=&quot;result&quot; operation=&quot;multiply&quot; operand1=&quot;17&quot; operand2=&quot;13&quot;/&gt;
    &lt;echo&gt;${result}&lt;/echo&gt;

    &lt;!-- demo shorthand notation for calculating sin of an angle, which is degrees --&gt;
    &lt;math result=&quot;sin&quot;&gt;
      &lt;sin&gt;
        &lt;radians arg1=&quot;${angle_in_degrees}&quot; /&gt;
      &lt;/sin&gt;
    &lt;/math&gt;
    &lt;echo&gt;${sin}&lt;/echo&gt;
</code></pre></td>
</tr>
</tbody>
</table>

