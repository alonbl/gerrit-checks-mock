package org.mock.plugins.checksmock;

import com.google.gerrit.extensions.restapi.Response;
import com.google.gerrit.extensions.restapi.RestReadView;
import com.google.gerrit.server.config.PluginConfigFactory;
import com.google.gerrit.server.project.NoSuchProjectException;
import com.google.gerrit.server.project.ProjectResource;
import com.google.gson.annotations.SerializedName;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
class GetProjectConfig implements RestReadView<ProjectResource> {
  private final PluginConfigFactory config;

  @Inject
  GetProjectConfig(PluginConfigFactory config) {
    this.config = config;
  }

  @Override
  public Response<ChecksMockProjectConfig> apply(ProjectResource project)
      throws NoSuchProjectException {
    ChecksMockProjectConfig result = new ChecksMockProjectConfig();
    result.enable =
        this.config
            .getFromProjectConfigWithInheritance(project.getNameKey(), "checks-mock")
            .getBoolean("enable", false);

    return Response.ok(result);
  }

  static class ChecksMockProjectConfig {
    @SerializedName("enable")
    Boolean enable;
  }
}
