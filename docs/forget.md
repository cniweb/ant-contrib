---
layout: home
---
Forget
======

The Forget task will execute a set of tasks sequentially as a background thread. Once the thread is started, control is returned to the calling target. This is useful in being able to kick off a background server process, such as a webserver. This allows you to not have to use the `parallel` task to start server processes.

Parameters
----------

| Attribute | Description                                                                                                                      | Required              |
|-----------|----------------------------------------------------------------------------------------------------------------------------------|-----------------------|
| `daemon`  | Should the created thread be a daemon thread. That is, should the ANT program be allowed to exit if the thread is still running. | No. Defaults to true. |

Example
-------

The following code

```xml
<forget>
  <exec executeable="${env.CATALINA_HOME}/bin/catalina.bat}">
    <arg line="start -security" />
  </exec>
</forget>

<waitfor maxwait="1" maxwaitunit="minute"
        checkevery="100" checkeveryunit="millisecond">
  <http url="http://localhost:8080" />
</waitfor>
```

Would start the Tomcat webserver as a background process, then waiting for the server to become available.
