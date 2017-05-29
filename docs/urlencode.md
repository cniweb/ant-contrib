---
layout: home
---
Foreach
=======

The URLEncode task will encode a given property for use within a a URL string. This value which is actually set will be encoded via the `java.net.URLEncoder.encode()` method. Typically, you must do this for all parameter values within a URL.

Parameters
----------

| Attribute         | Description                                                                           | Required                                 |
|-------------------|---------------------------------------------------------------------------------------|------------------------------------------|
| property          | The name of the property to set.                                                      | Yes.                                     |
| override          | If the property is already set, should we change it's value. Can be `true` or `false` | No. Defaults to `false`                  |
| name *Deprecated* | The name of the property to set.                                                      | No. Use the `property` attribute instead |
| value             | The value of the property.                                                            | No, if refid or location is specified    |
| location          | The location of a file whose absolute path will be the value of the property.         | No, if value or refid is specified.      |
| refid             | The id of a saved reference whose value will be the value of the property.            | No, defaults to ",".                     |

Example
-------

The following code

        
        <urlencode name="file.location" location="C:\\wwwhome\\my reports\\report.xml" />
        
        

would set the "file.location" property to the value: ` C%3A%5Cwwwhome%5Cmy+reports%5Creport.xml` which could then be used in a URL.


