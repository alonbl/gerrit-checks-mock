@PLUGIN@ - /changes/ REST API
==============================

This page describes the changes related REST endpoints that are added by the
@PLUGIN@.

<a id="changes-endpoints"> Changes Endpoints
--------------------------------------------

### <a id="get-checks-fetch"> Get Changes Checks Fetch

_GET /changes/[\{change-id\}](../../../Documentation/rest-api-changes.html#change-id)/revisions/[\{revision-id\}](../../../Documentation/rest-api-changes.html#revision-id)/@PLUGIN@~checks_fetch_

Gets the checks fetch result.

#### Request

```
  GET /changes/{change-id}/revisions/{revision-id}/@PLUGIN@~checks_fetch HTTP/1.0
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

[Back to @PLUGIN@ documentation index][index]

[index]: index.html
