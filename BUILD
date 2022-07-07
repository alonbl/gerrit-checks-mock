load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "checks-mock",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: checks-mock",
        "Gerrit-Module: org.mock.plugins.checksmock.ChecksMockModule",
        "Implementation-Title: Gerrit checks mock plugin",
        "Implementation-URL: https://github.com/alonbl/gerrit-checks-mock",
    ],
    resources = glob(["src/main/resources/**/*"]),
    resource_jars = ["//plugins/checks-mock/web:checks-mock"],
)
