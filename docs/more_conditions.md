---
layout: home
---
 More Conditions
-----------------------------------------------

These conditions are suitable for use in the `<bool>` element. Unfortunately, they cannot be used in the `<condition>` task, although all conditions for the `<condition>` task can be used with the `<bool>` and the `<bool>` can be used anywhere that `<condition>` can be used.

####  IfPropertyTrue

Given a property name, tests whether the value for that property equals "true" (or "yes" or "on").

**Table 5.2. IfPropertyTrue Attributes**

| Attribute | Description                                  | Required |
|-----------|----------------------------------------------|----------|
| `property`  | The name of a property to test the value of. | Yes      |

```xml
<ispropertytrue property="myprop"/>
<ispropertytrue property="${someprop}"/>
```

####  IfPropertyFalse

Given a property name, tests whether the value for that property equals "false" (or "no" or "off").

**Table 5.3. IfPropertyFalse Attributes**

| Attribute | Description                                  | Required |
|-----------|----------------------------------------------|----------|
| `property`  | The name of a property to test the value of. | Yes      |

```xml
<ispropertyfalse property="myprop"/>
<ispropertyfalse property="${someprop}"/>
```

####  StartsWith

Given a property name, tests whether the value for that property starts with a specified string.

**Table 5.4. StartsWith Attributes**

| Attribute | Description                               | Required |
|-----------|-------------------------------------------|----------|
| `string`  | The string to test.                       | Yes      |
| `with`  | Check if 'string' starts with this value. | Yes      |

```xml
<startswith string="abcdefg" with="abc"/>
<startswith string="${myprop}" with="foo"/>
```

####  EndsWith

Given a property name, tests whether the value for that ends with with a specified string.

**Table 5.5. EndsWith Attributes**

| Attribute | Description                             | Required |
|-----------|-----------------------------------------|----------|
| `string`  | The string to test.                     | Yes      |
| `with`  | Check if 'string' ends with this value. | Yes      |

```xml
<endswith string="abcdefg" with="efg"/>
<endswith string="${myprop}" with="bar"/>
```

####  IsGreaterThan

Tests whether the first argument is greater than the second argument. Will automatically treat the arguments as numbers if both arguments consists of only the characters 0 through 9 and optionally a decimal point. Otherwise, a String comparison is used.

**Table 5.6. IsGreaterThan Attributes**

| Attribute | Description          | Required |
|-----------|----------------------|----------|
| `arg1`  | The first argument.  | Yes      |
| `arg2`  | The second argument. | Yes      |

```xml
<!-- evaluates to true -->
<isgreaterthan arg1="6.02" arg2="4"/>

<!-- evaluates to false -->
<isgreaterthan arg1="bar" arg2="foo"/>
```

####  IsLessThan

Tests whether the first argument is less than the second argument. Will automatically treat the arguments as numbers if both arguments consists of only the characters 0 through 9 and optionally a decimal point. Otherwise, a String comparison is used.

**Table 5.7. IsLessThan Attributes**

| Attribute | Description          | Required |
|-----------|----------------------|----------|
| `arg1`  | The first argument.  | Yes      |
| `arg2`  | The second argument. | Yes      |

```xml
<!-- evaluates to false -->
<islessthan arg1="6.02" arg2="4"/>

<!-- evaluates to true -->
<islessthan arg1="bar" arg2="foo"/>
```
