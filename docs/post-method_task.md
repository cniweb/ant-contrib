---
layout: home
---
Post-Method
===========

The <post-method> task allows the caller to use the HTTP POST method to send data to an arbitrary url. This data can be one of the following:

-   Name/Value pairs
-   File content
-   Text content
-   Multi-part content

This method inherits the [Common Method](method_task_common.html) attributes and subelements. It also contains the following additional attributes and subelements:

Parameters
----------

| Attribute   | Description                                                                               | Required |
|-------------|-------------------------------------------------------------------------------------------|----------|
| `multipart` | Should multipart content be forced, even if only a single file or text part is specified. | No.      |
| `parameters`  | A java .properties file which contains post parameters.                                   | No.      |

Parameters specified as Nested Elements
---------------------------------------

`<parameter>`

Create a text post parameter.

| Attribute | Description          | Required |
|-----------|----------------------|----------|
| `name`    | The parameter name.  | Yes.     |
| `value`   | The parameter value. | Yes.     |

`<file>`

Add a File part to the request.

| Attribute     | Description                   | Required |
|---------------|-------------------------------|----------|
| `name`        | The parameter name.           | Yes.     |
| `path`        | The file path to send.        | Yes.     |
| `contentType` | The content type of the file. | No.      |
| `charSet`     | The character set.            | No.      |

`<text>`

Add a Text part to the request.

| Attribute     | Description                                                                          | Required |
|---------------|--------------------------------------------------------------------------------------|----------|
| `name`        | The parameter name.                                                                  | Yes.     |
| `value`       | The string value to send. This may also be specified as nested text to this element. | Yes.     |
| `contentType` | The content type of the file.                                                        | No.      |
| `charSet`     | The character set.                                                                   | No.      |
