load("//tools/js:eslint.bzl", "plugin_eslint")
load("//tools/bzl:js.bzl", "gerrit_js_bundle")
load("@npm//@bazel/typescript:index.bzl", "ts_config", "ts_project")

package_group(
    name = "visibility",
    packages = ["//plugins/checks-mock/..."],
)

package(default_visibility = [":visibility"])

ts_config(
    name = "tsconfig",
    src = "tsconfig.json",
    deps = [
        "//plugins:tsconfig-plugins-base.json",
    ],
)

ts_project(
    name = "checks-mock-ts",
    srcs = glob(
        ["**/*.ts"],
    ),
    incremental = True,
    out_dir = "_bazel_ts_out",
    tsc = "//tools/node_tools:tsc-bin",
    tsconfig = ":tsconfig",
    deps = [
        "@plugins_npm//@gerritcodereview/typescript-api",
    ],
)

gerrit_js_bundle(
    name = "checks-mock",
    srcs = [":checks-mock-ts"],
    entry_point = "_bazel_ts_out/plugin.js",
)

plugin_eslint()
