@PLUGIN@ Configuration
======================

Global Configuration
--------------------

Global configuration in `gerrit.config`:

```
  [plugin "@PLUGIN@"]
  enable = true
  fetchPollingIntervalSeconds = 60
  fetchEndpointURL = @URL@
  fetchEndpointURLConnectTimeout = 20
  fetchEndpointURLRequestTimeout = 20
```

plugin.@PLUGIN@.enable
:	Enable the plugin.

plugin.@PLUGIN@.fetchPollingIntervalSeconds
:	JavaScript checks API (client side) polling interval in seconds.

plugin.@PLUGIN@.fetchEndpointURL
:	URL to delegate the checks API to.

plugin.@PLUGIN@.fetchEndpointURLConnectTimeout
:	Connect timeout of URL delegation.

plugin.@PLUGIN@.fetchEndpointURLRequestTimeout
:	Request timeout of URL delegation.

Project Configuration
--------------------

Project configuration in `project.config` inheritable:

```
  [plugin "@PLUGIN@"]
  enable = true
```

plugin.@PLUGIN@.enable
:	Enable the plugin for the project.

[Back to @PLUGIN@ documentation index][index]

[index]: index.html
