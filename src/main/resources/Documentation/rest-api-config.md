@PLUGIN@ - /config/ REST API
==============================

This page describes the config related REST endpoints that are added by the
@PLUGIN@.

<a id="config-endpoints"> Config Endpoints
--------------------------------------------

### <a id="get-config"> Get Project Configuration

_GET /config/server/@PLUGIN@~config_

Gets the plugin configuration.

#### Request

```
  GET /projects/MyProject/@PLUGIN@~config HTTP/1.0
```

As result a [ChecksMockConfig](#config) entity is returned.

#### Response

```
  HTTP/1.1 200 OK
  Content-Disposition: attachment
  Content-Type: application/json; charset=UTF-8

  )]}'
  {
    "enable": true,
    "fetchPollingIntervalSeconds": 60
  }
```

### <a id="config"> ChecksMockConfig

The `ChecksMockConfig` contains plugin configuration.

* enable: If plugin is enabled, default false.
* fetchPollingIntervalSeconds: User interface update interval, default 60.

[Back to @PLUGIN@ documentation index][index]

[index]: index.html
