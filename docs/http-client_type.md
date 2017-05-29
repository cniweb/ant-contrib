---
layout: home
---
HttpClient
==========

The <httpClient> type allows the caller to create an HttpClient instance, and add it as a reference, or be nested as a subelement of an HTTP method call.

Parameters
----------

| Attribute    | Description                                                | Required                      |
|--------------|------------------------------------------------------------|-------------------------------|
| *id*         | The reference id to store this HttpClient under.           | No.                           |
| *refId*      | The reference id of the HttpClient this element refers to. | No.                           |
| *stateRefId* | The HttpState object to use.                               | No. Uses a default HttpState. |

Parameters specified as Nested Elements
---------------------------------------

**<clientParams>**

Create http client params.

| Attribute                  | Description                                                         | Required |
|----------------------------|---------------------------------------------------------------------|----------|
| *strict*                   | Should we be strict on the protocol.                                | No.      |
| *authenticationPreemptive* | Should we pre-emptively try to authenticate?                        | No.      |
| *connectionManagerTimeout* | The timeout for the connection manager.                             | No.      |
| *contentCharSet*           | The content character set                                           | No.      |
| *cookiePolicy*             | The cookie policy (IGNORE\_COOKIES, RFC\_2109, NETSCAPE or DEFAULT) | No.      |
| *credentialCharSet*        |                                                                     | No.      |
| *httpElementCharSet*       |                                                                     | No.      |
| *soTimeout*                |                                                                     | No.      |
| *version*                  | The HTTP version.                                                   | No.      |

Additional **<clientParams>** subelements:
<double>,<int>,<long>,<boolean> ,<string>

Create a client parameter.

| Attribute | Description          | Required |
|-----------|----------------------|----------|
| *name*    | The parameter name   | Yes.     |
| *value*   | The parameter value. | Yes.     |

**<hostConfig>**

Create a host configuration.

| Attribute   | Description                  | Required |
|-------------|------------------------------|----------|
| *host*      | The host to connect to.      | No.      |
| *port*      |                              | No.      |
| *protocol*  |                              | No.      |
| *address*   |                              | No.      |
| *proxyHost* | The proxyHost to connect to. | No.      |
| *proxyPort* |                              | No.      |

Additional **<hostConfig>** subelements:
<hostParams>

Specify HostParams.

| <hostParams> subelements are identical to those of <clientParams> |
|-------------------------------------------------------------------------------|

**<httpState>**

Create (or reference an existing) HttpState for use with this HTTP client. This is necessary if you wish to enable authentication, or retain state across multiple method calls.

| Please see the [httpState](http-state_type.html) documentation for more details on this element |
|-------------------------------------------------------------------------------------------------|

Examples
--------

        
        <httpClient id="client1">
            <clientParams cookiePolicy="RFC_2109" />
        </httpClient>
        
        

