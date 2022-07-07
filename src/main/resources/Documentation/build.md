Build
=====

Build in Gerrit tree
--------------------

Clone (or link) this plugin to the `plugins` directory of Gerrit's source tree
as `@PLUGIN@`.

Then issue

```
  bazel build //plugins/@PLUGIN@:@PLUGIN@
```

in the root of Gerrit's source tree to build using

The output is created in

```
  bazel-bin/plugins/@PLUGIN@/@PLUGIN@.jar
```

[Back to @PLUGIN@ documentation index][index]

[index]: index.html
