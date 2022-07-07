The @PLUGIN@ plugin provides a simple integration of the new [checks
API](https://gerrit.int.com/Documentation/pg-plugin-checks-api.html) using
external REST API implementation.

The plugin delegates the entire JavaScript API to Gerrit REST API which
leverages the Gerrit authentication and then delegates this into external REST
API that can be written in any implementation.

This makes the implementation within Gerrit relatively static and simple.
While delegating the logic into developers' friendly environment.

The delegation is performed using `FetchDetails` entity.

An implementation of fetch endpoint is available at
[gerrit_checks_mock_fetch_endpoint](https://github.com/alonbl/gerrit_checks_mock_fetch_endpoint).

### <a id="fetch-endpoint"> Fetch
_POST [\{fetchEndpointURL\}](config.html)_

#### Request

```
  POST {fetchEndpointURL} HTTP/1.0
  Content-Type: application/json;charset=UTF-8

  {
    "accountId": 10000,
    "emailAddresses": ["nobody@nowhere"],
    "project": "project1",
    "changeId": 123,
    "revision": 2
  }
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
