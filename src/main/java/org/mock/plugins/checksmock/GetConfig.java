package org.mock.plugins.checksmock;

import com.google.gerrit.extensions.restapi.Response;
import com.google.gerrit.extensions.restapi.RestReadView;
import com.google.gerrit.server.config.ConfigResource;
import com.google.gerrit.server.config.PluginConfigFactory;
import com.google.gson.annotations.SerializedName;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
class GetConfig implements RestReadView<ConfigResource> {
  private final PluginConfigFactory config;

  @Inject
  GetConfig(PluginConfigFactory config) {
    this.config = config;
  }

  @Override
  public Response<ChecksMockConfig> apply(ConfigResource config) {
    ChecksMockConfig result = new ChecksMockConfig();
    result.enable = this.config.getFromGerritConfig("checks-mock").getBoolean("enable", false);
    result.fetchPollingIntervalSeconds =
        this.config.getFromGerritConfig("checks-mock").getInt("fetchPollingIntervalSeconds", 60);

    return Response.ok(result);
  }

  static class ChecksMockConfig {
    @SerializedName("enable")
    Boolean enable;

    @SerializedName("fetchPollingIntervalSeconds")
    Integer fetchPollingIntervalSeconds;
  }
}
