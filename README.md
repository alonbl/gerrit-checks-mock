# Gerrit Checks Mock

A simple project to be used as skelleton for gerrit checks integration.

Please refer to the inline documentation:

* [About](./src/main/resources/Documentation/about.md)
* [Config](./src/main/resources/Documentation/config.md)
* [Build](./src/main/resources/Documentation/build.md)

## Build

### Full

Checkout Gerrit including and recursive submodules.

At Gerrit root, execute:

```sh
$ bazel build plugins/checks-mock:checks-mock
```
Output resides at `bazel-bin/plugins/checks-mock/checks-mock.jar`

## RestAPI

* http://localhost:8080/config/server/checks-mock~config
* http://localhost:8080/projects/test1/checks-mock~config
* http://localhost:8080/changes/21/revisions/2/checks-mock~checks_fetch

## Java code format

```sh
find src/ -name '*.java' | xargs java -jar google-java-format-1.15.0-all-deps.jar -r
```
