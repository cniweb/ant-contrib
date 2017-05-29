---
layout: home
---
\*Method
========

The `<method>` tasks allows the caller to use the various HTTP methods (current GET, HEAD and POST are supported). These methods have some common attributes, and sub-elements which are are as shown below:

Parameters
----------

| Attribute            | Description                                                                                                                                                                                                                                                                                                                     | Required                                                                                                                                                        |
|----------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| *url*                | The url that is being called.                                                                                                                                                                                                                                                                                                   | No, if the client host configuration is defined, and the path attribute is specified.                                                                           |
| *path*               | The path which we are going to connect to. If this is used, you must declare an httpClient subelement, or set the clientRefId attribute for an HttpClient instance with configured host parameters.                                                                                                                             | No.                                                                                                                                                             |
| *queryString*        | The queryString which we are posting to. If this is used, you must declare an httpClient subelement, or set the clientRefId attribute for an HttpClient instance with configured host parameters.                                                                                                                               | No.                                                                                                                                                             |
| *clientRefId*        | The reference id of a previously declared <httpClient> data type instance. This is necessary if you want to retain state (cookies) across multiple requests, or you want specific client configuration and host configuration parameters. If not specified, we create a new <httpClient> with the default settings. | No.                                                                                                                                                             |
| `responseDataFile`  | The path of the file where the response data will be placed.                                                                                                                                                                                                                                                                    | No.                                                                                                                                                             |
| `responseDataProperty`  | The property where the response data will be placed.                                                                                                                                                                                                                                                                            | No.                                                                                                                                                             |
| *statusCodeProperty* | The name of the property to set with the HTTP response status code.                                                                                                                                                                                                                                                             | No.                                                                                                                                                             |
| *doAuthentication*   | Should we perform authentication.                                                                                                                                                                                                                                                                                               | No. If set, you must either declare an <httpClient> instance, or set the clientRefId attribute for an HttpClient which has credentials installed into it. |
| *followRedirects*    | Should we automatically follow redirects.                                                                                                                                                                                                                                                                                       | No.                                                                                                                                                             |

Parameters specified as Nested Elements
---------------------------------------

**<httpClient>**

Create (or reference an existing) HttpClient for use with this HTTP method call. This is necessary if you wish to configure the client beyond the default settings, enable authentication, or retain state across multiple method calls.

| Please see the [httpClient](http-client_type.html) documentation for more details on this element |
|---------------------------------------------------------------------------------------------------|

**<header>**

Create a request header to be sent.

| Attribute | Description       | Required |
|-----------|-------------------|----------|
| *name*    | The header name.  | Yes.     |
| *value*   | The header value. | Yes.     |

**<responseHeader>**

Specify a response header to be retrieved into a property.

| Attribute  | Description                                   | Required |
|------------|-----------------------------------------------|----------|
| *name*     | The header name.                              | Yes.     |
| *property* | The property to be set with the header value. | Yes.     |

**<params>**

Create http method paramaters.

| Attribute            | Description                                                         | Required |
|----------------------|---------------------------------------------------------------------|----------|
| *contentCharSet*     | The content character set                                           | No.      |
| *cookiePolicy*       | The cookie policy (IGNORE\_COOKIES, RFC\_2109, NETSCAPE or DEFAULT) | No.      |
| *credentialCharSet*  |                                                                     | No.      |
| *httpElementCharSet* |                                                                     | No.      |
| *soTimeout*          |                                                                     | No.      |
| *version*            | The HTTP version.                                                   | No.      |

Additional **<params>** subelements:
<double>,<int>,<long>,<boolean> ,<string>

Create a method parameter.

| Attribute | Description          | Required |
|-----------|----------------------|----------|
| *name*    | The parameter name   | Yes.     |
| *value*   | The parameter value. | Yes.     |
