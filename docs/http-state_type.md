---
layout: home
---
HttpState
=========

The `<httpState>` type allows the caller to create an HttpState instance, and add it as a reference, or be nested as a subelement of an `<httpClient>` element.

Parameters
----------

| Attribute | Description                                                      | Required |
|-----------|------------------------------------------------------------------|----------|
| `id`      | The reference id to store this HttpState under.                  | No.      |
| `refId`   | The reference id of the HttpState which this element references. | No.      |

Parameters specified as Nested Elements
---------------------------------------

`<cookie>`

Create a cookie.

| Attribute                  | Description | Required |
|----------------------------|-------------|----------|
| `domain`                   |             | No.      |
| `path`                     |             | No.      |
| `name`                     |             | No.      |
| `value`                    |             | No.      |
| `secure`                   |             | No.      |
| `comment`                  |             | No.      |
| `expiryDate`               |             | No.      |
| `version`                  |             | No.      |
| `domainAttributeSpecified` |             | No.      |
| `pathAttributeSpecified`   |             | No.      |

`<credentials>`

Create authentication credentials.

| Attribute  | Description   | Required |
|------------|---------------|----------|
| `host`     | The host.     | No.      |
| `port`     | The port.     | No.      |
| `realm`    | The realm.    | No.      |
| `scheme`   | The scheme.   | No.      |
| `username` | The username. | No.      |
| `password` | The password. | No.      |

`<proxyCredentials>`

Create proxy authentication credentials.

| Identitical to <credentials> element. |
|---------------------------------------------|

Examples
--------

```xml
<httpState id="myState">
  <cookie name="loginId" value="username" realm="sourceforge.net" />
</httpState>

<httpClient id="myClient" stateRefId="myState" />

<httpClient id="myClient>
  <httpState>
    <cookie name="loginId" value="username" realm="sourceforge.net" />
  </httpState>
</httpClient>
```
