---
layout: home
---
 Math
----------------------------

The Math task provides support for all the basic mathematical operations provided by the java.lang.Math and java.lang.StrictMath classed. It supports int, long, float and double data types. Nesting of operations is supported to allow computation of formulas like (6 + (7.25 \* 3.9))/(2 \* 3 \* 3) or calculating the area of a circle given a radius (I'm sure this comes up often in builds controlled by Ant!).

In addition to the operations provided by the java.lang.Math and java.lang.StrictMath classes, the Math task provides several additional operations: "add", "subtract", "multiply", "divide", and "mod", which duplicate the basic Java mathematical operations "+", "-", "\*", "/", and "%", respectively. In fact, either notation can be used, that is, the operation can be set to "add" or "+", depending only on which you feel is more convenient.

**Table 11.1. Math Task Attributes**

| Attribute | Description                                                                                                                                                             | Default | Required                           |
|-----------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|------------------------------------|
| `result`  | The name of the property to hold the result of the operation.                                                                                                           | None    | Yes                                |
| `datatype`  | Sets the datatype of the calculation. Allowed values are "int", "long", "float", or "double". Optional, if used, will be applied to all numbers in this math operation. | double  | No                                 |
| `strict`  | If true, use the methods in the java.lang.StrictMath class.                                                                                                             | false   | No                                 |
| `operation`  | If used, any nested Ops will be ignored. This is for convenience for simple calculations.                                                                               | None    | No                                 |
| `operand1`  | A number to use with the operation specified in the 'operation' attribute.                                                                                              | None    | Depends on the specific operation. |
| `operand2`  | A number to use with the operation specified in the 'operation' attribute.                                                                                              | None    | Depends on the specific operation. |

The 'result' property is reusable.

The Math task supports nested "Op" elements. An Op element represents single mathematical operation, such as "min" or "add". As an alternate, if using Ant 1.5+, you can specify the operation in the tag name itself. However, you must use the text name (+,-,/,\*,% are not permitted as tag names)

```xml
<radians>
  <num value="90" />
</radians>
```
instead of

```xml
<op op="radians">
  <num value="90" />
</op>
```

**Table 11.2. Op Attributes**

| Attribute                                                                                                                          | Description                                                                                                                                                                                                                                                                                                                                 | Default                                                                        | Required                               |
|------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|----------------------------------------|
| `op`  | Set the name of this operation. Allowed values are one of the method names from java.lang.Math or java.lang.StrictMath, or one of "add", "subtract", "multiply", "divide", or "mod" (or "+", "-", "\*", "/", or "%", respectively). "toRadians" and "toDegrees" can be represented by "radians" and "degrees", respectively, as a shorthand | None                                                                           | Yes, if not specified in the tag name. |
| `datatype`  | Sets the datatype of this calculation. Allowed values are "int", "long", "float", or "double". Optional, default is "double". If the parent Math task has a datatype set, this value will be ignored and the datatype specifed in the task will be used.                                                                                    | "int"                                                                          | No                                     |
| arg1, arg2, arg3, arg4, arg5/td>                                                                                                
 The arguments for this operation. This is a shorthand to avoid having to use nested elements when performing a simple calculation.  | None                                                                                                                                                                                                                                                                                                                                        | No. However, these attributes are mutually exclusive with the and subelements. |                                        |

The Op element supports nested "Op" elements and nested "Num" elements. A Num represents a number. When an Op is nested in another Op, the result of the Op is treated as a Num. The nested elements can be any combination of Op (short form included as mentioned above) or Num as appropriate for the formula being calculated. Most of the operations provided by java.lang.Math and java.lang.StrictMath operate on one or two numbers. The "+", "-", "\*", "/", and "%" operations can task any number of nested numbers.

**Table 11.3. Num Attributes**

| Attribute | Description                                                                                                                                                                                                                                                                                                                          | Default | Required |
|-----------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|----------|
| `value`  | Set the value for this number. Must be able to parse to the datatype set by the parent element or the default datatype set by the task. Two special numbers, pi and e, can be represented by PI and E respectively. ("PI" is the ratio of the diameter of a circle to its radius, "E" is Euler's e, the base for natural logrithms.) | None    | Yes      |
| `datatype`  | Sets the datatype of this number. Allowed values are "int", "long", "float", or "double". Optional, default is "double". If the parent Math task has a datatype set, this value will be ignored and the datatype specifed in the task will be used.                                                                                  | double  | No       |

Some examples:

```xml
<var name="op1" value="12"/>
<var name="op2" value="6"/>
<var name="op" value="+"/>

<!-- demo plus -->
<math result="result" operand1="${op1}" operation="${op}" operand2="${op2}" datatype="int"/>
<echo>${op1} ${op} ${op2} = ${result}</echo>
<assert name="result" value="18"/>

<!-- demo reusing result -->
<math result="result" operand1="${result}" operation="${op}" operand2="${op2}" datatype="int"/>
<echo>${op1} ${op} ${op2} = ${result}</echo>
<assert name="result" value="24"/>

<!-- demo minus -->
<var name="op" value="-"/>
<math result="result" operand1="${op1}" operation="${op}" operand2="${op2}" datatype="int"/>
<echo>${op1} ${op} ${op2} = ${result}</echo>
<assert name="result" value="6"/>

<!-- demo multiply -->
<var name="op" value="*"/>
<math result="result" operand1="${op1}" operation="${op}" operand2="${op2}" datatype="int"/>
<echo>${op1} ${op} ${op2} = ${result}</echo>
<assert name="result" value="72"/>

<!-- demo divide -->
<var name="op" value="/"/>
<math result="result" operand1="${op1}" operation="${op}" operand2="${op2}" datatype="int"/>
<echo>${op1} ${op} ${op2} = ${result}</echo>
<assert name="result" value="2"/>

<!-- demo modulo -->
<var name="op" value="%"/>
<math result="result" operand1="${op1}" operation="${op}" operand2="${op2}" datatype="int"/>
<echo>${op1} ${op} ${op2} = ${result}</echo>
<assert name="result" value="0"/>

<!-- demo calculating the area of a circle -->
<!-- first, calculate the radius -->
<math result="radius">  <!-- defaults to double datatype -->
    <op type="*">
        <num value="1"/>
        <num value="2"/>
        <num value="3"/>
        <num value="4"/>
        <num value="5"/>
    </op>
</math>
<echo> 1 * 2 * 3 * 4 * 5 = ${radius}</echo>

<!-- now calculate the area -->
<math result="area" precision="float">
    <op type="*">
        <num value="PI"/>
        <op type="pow">
            <num value="${radius}"/>
            <num value="2"/>
        </op>
    </op>
</math>
<echo>area = PI * radius ^ 2 = ${area}</echo>

<!-- demo calculating a random number between 0 and 100 -->
<math result="result">
    <op op="rint">
        <op op="*">
            <num value="100"/>
            <op op="random"/>
        </op>
    </op>
</math>
<echo>a random number between 0 and 100: ${result}</echo>

<!-- demo another multiplication -->
<math result="result" operation="multiply" operand1="17" operand2="13"/>
<echo>${result}</echo>

<!-- demo shorthand notation for calculating sin of an angle, which is degrees -->
<math result="sin">
  <sin>
    <radians arg1="${angle_in_degrees}" />
  </sin>
</math>
<echo>${sin}</echo>
```
