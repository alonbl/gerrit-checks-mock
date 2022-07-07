@PLUGIN@ - /projects/ REST API
==============================

This page describes the project related REST endpoints that are added by the
@PLUGIN@.

<a id="project-endpoints"> Project Endpoints
--------------------------------------------

### <a id="get-config"> Get Project Configuration

_GET /projects/[\{project-name\}](../../../Documentation/rest-api-projects.html#project-name)/@PLUGIN@~config_

Gets the plugin configuration.

#### Request

```
  GET /projects/MyProject/@PLUGIN@~config HTTP/1.0
```

As result a [ChecksMockProjectConfig](#projcet-config) entity is returned.

#### Response

```
  HTTP/1.1 200 OK
  Content-Disposition: attachment
  Content-Type: application/json; charset=UTF-8

  )]}'
  {
    "enable": true,
  }
```

### <a id="get-checks-fetch"> Get Project Checks Fetch

_GET /projects/[\{project-name\}](../../../Documentation/rest-api-projects.html#project-name)/@PLUGIN@~checks_fetch_

Gets the checks fetch result.

#### Request

```
  GET /projects/MyProject/@PLUGIN@~checks_fetch HTTP/1.0
```

As result a
[FetchResponse](https://gerrit.googlesource.com/gerrit/+/master/polygerrit-ui/app/api/checks.ts)
entity is returned.

#### Response

```
  HTTP/1.1 200 OK
  Content-Type: application/json; charset=UTF-8

  )]}'
  {
    "responseCode": "OK",
    "runs": [
      {
        "checkName": "Name1",
        "status": "COMPLETED",
        "results": [
          {
            "category": "ERROR",
            "summary": "Summary1",
            "message": "Message1"
          }
      }
    ]
  }
```

### <a id="projcet-config"> ChecksMockProjectConfig

The `ChecksMockProjectConfig` contains project width configuration.

* enable: If plugin is enabled for the project, default false.

[Back to @PLUGIN@ documentation index][index]

[index]: index.html
