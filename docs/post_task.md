---
layout: home
---
<span id="post"></span> HTTP Post
---------------------------------

The Post task is a companion to the standard Ant "Get" task. This task does a post and does not necessarily expect anything in return. Almost always, there will be some sort of returned data, this can be logged or written to a file if needed.

Basically, an HTTP POST sends name/value pairs to a web server. A very common usage is for html forms for submitting data. A typical use of this task is to send data to a servlet for updating a web page with the status of a build.

This task handles cookies correctly, which is useful for websites that set a session id to track logins or whatever. This means that if you do several posts in a row, cookies gathered from the first post will be returned with subsequent posts.

The Post task has three ways of specifying the data to be posted. Nested "prop" elements can be used. A "prop" element represents a single name/value pair. The second way is to specify a property file as an attribute to the Post. All properties from the file will be sent as part of the Post data. The third way is to just type in some defined Ant properties. Is it allowed to use all three ways at once, that is, read some properties from a file, specify others via "prop" elements, and just type in some Ant properties.

<span id="N10A61"></span>
**Table 12.1. Post Task Attributes**

Attribute
Description
Default
Required
to
The URL of the remote server to send the post.
None
Yes
encoding
Character encoding for the name/value pairs.
UTF-8
No
logfile
The name of a file to write any response to. Ignored if wantresponse is set to false.
None
No
property
The name of a property to write any response to. Ignored if wantresponse is set to false.
None
No
append
Should an existing log file be appended to or overwritten?
True, append to an existing file.
No
file
A file to read POST data from. All Ant properties contained in this file will be resolved (that is, ${} syntax will be expanded to their values) prior to sending the file contents.
None
No
rawFile
Should the file be trated as raw file instead of property-like file. True - send the content of the file directly to http-post, all properties set by 'property' are ignored!
Has only impact when the property 'file' is specified.
False, treat file as property-like
No
rawFileNoEncoding
Don't encode the raw file prior to sending http post request.
Has only impact when the property 'rawFile' is specified.
False, http-encode the content of the file
No
maxwait
The maximum amount of time in seconds to wait for the data to be sent or for a response from the remote server. Setting this to zero means wait forever.
180 (3 minutes)
No
wantresponse
Whether to wait for a response from the remote server or not. In many cases this can greatly improve the performance of the build script as the server response may be large and useless to the script. Use this with caution - while the response from the server may not be required for the client, the server may require that the client accept the response prior to processing the post data.
true
No
failonerror
Whether the build should fail if the post fails.
false
No

Post supports nested "prop" elements. As an HTTP POST basically sends a list of names and values, the "prop" element represents one name/value pair. A Post may contain any number of "prop" elements.

<span id="N10ABF"></span>
**Table 12.2. Prop Attributes**

| Attribute | Description                         | Default | Required |
|-----------|-------------------------------------|---------|----------|
| name      | The name of a property to post.     | None    | Yes      |
| value     | The value associated with the name. | None    | No       |

The "value" attribute is not strictly required. This provides a short-cut method in cases where the property data is an already-defined Ant property. Suppose the build file has this property defined:

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

    &lt;property name=&quot;src.dir&quot; value=&quot;/home/user/project/src&quot;/&gt;
</code></pre></td>
</tr>
</tbody>
</table>

Then the following are equivalent:

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

    &lt;prop name=&quot;src.dir&quot;/&gt;
    &lt;prop name=&quot;src.dir&quot; value=&quot;${src.dir}&quot;/&gt;
    &lt;prop name=&quot;src.dir&quot; value=&quot;/home/user/project/src&quot;/&gt;
</code></pre></td>
</tr>
</tbody>
</table>

Defined Ant properties can be entered directly into the post element. Again, suppose the build file has this property defined:

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

    &lt;property name=&quot;src.dir&quot; value=&quot;/home/user/project/src&quot;/&gt;
</code></pre></td>
</tr>
</tbody>
</table>

Then the following are equivalent:

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

    ${src.dir}
    &lt;prop name=&quot;src.dir&quot;/&gt;
    &lt;prop name=&quot;src.dir&quot; value=&quot;${src.dir}&quot;/&gt;
    &lt;prop name=&quot;src.dir&quot; value=&quot;/home/user/project/src&quot;/&gt;
</code></pre></td>
</tr>
</tbody>
</table>

I googled for the URL in the following example.

<table>
<colgroup>
<col width="100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><pre class="programlisting"><code>

    &lt;property name=&quot;test.val&quot; value=&quot;here&#39;s my test value&quot;/&gt;
    &lt;property name=&quot;test.val2&quot; value=&quot;second test value&quot;/&gt;
    &lt;post to=&quot;http://wwwj.cs.unc.edu:8888/tang/servlet/tangGetPostServlet&quot;
        verbose=&quot;true&quot;&gt;
        &lt;prop name=&quot;prop1&quot; value=&quot;val1 ${test.val}&quot;/&gt;
        &lt;prop name=&quot;prop2&quot; value=&quot;val1 value 2&quot;/&gt;
        &lt;prop name=&quot;prop3&quot; value=&quot;val got some spaces %funky ^$* chars&quot;/&gt;
        &lt;prop name=&quot;prop4&quot; value=&quot;&amp;amp; do an ampersand like this &amp;amp;amp; or
        Ant will whine&quot;/&gt;
        &lt;prop name=&quot;thanks&quot; value=&quot;dude, thanks for the echo server!&quot;/&gt;
        &lt;prop name=&quot;test.val&quot;/&gt;
        ${test.val2}
    &lt;/post&gt;
</code></pre></td>
</tr>
</tbody>
</table>

