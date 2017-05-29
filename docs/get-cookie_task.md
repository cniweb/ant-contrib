---
layout: home
---
GetCookie
=========

The &lt;getCookie&gt; task allows the caller to retrieve one or more cookies from an &lt;httpState&gt; reference.

Parameters
----------

| Attribute    | Description                                                                                                                                                                                                                   | Required                                             |
|--------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------|
| realm        | The realm of the cookie(s) to retrieve.                                                                                                                                                                                       | Yes.                                                 |
| port         | The port of the cookie(s) to retrieve.                                                                                                                                                                                        | No. defaults to 80                                   |
| path         | The path of the cookie(s) to retrieve.                                                                                                                                                                                        | Yes.                                                 |
| secure       | The secure flag of the cookie(s) to retrieve.                                                                                                                                                                                 | No. Default to false.                                |
| name         | The name of the cookie to retrieve.                                                                                                                                                                                           | No. If not specified, multiple cookies may be found. |
| cookiePolicy | The cookiePolicy to use to match cookies.                                                                                                                                                                                     | No. Default to 'rfc2109'.                            |
| property     | The property to retrieve the cookie into. This will only retrieve the first cookie found which matches the query. If no 'name' attribute is specified, there is no guarantee you will get the cookie you are expecting.       | No, unless 'prefix' is not specified.                |
| prefix       | The prefix to use when settings properties for the cookies. One property will be set for each cookie, where the property name will be of the pattern: ${prefix}${cookie.name} where ${cookie.name} is the name of the cookie. | No, unless 'property' is not specified.              |

Examples
--------


