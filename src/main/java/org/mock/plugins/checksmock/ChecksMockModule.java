package org.mock.plugins.checksmock;

import static com.google.gerrit.server.change.RevisionResource.REVISION_KIND;
import static com.google.gerrit.server.config.ConfigResource.CONFIG_KIND;
import static com.google.gerrit.server.project.ProjectResource.PROJECT_KIND;

import com.google.gerrit.extensions.registration.DynamicSet;
import com.google.gerrit.extensions.restapi.RestApiModule;
import com.google.gerrit.extensions.webui.JavaScriptPlugin;
import com.google.gerrit.extensions.webui.WebUiPlugin;

public class ChecksMockModule extends RestApiModule {

  @Override
  protected void configure() {
    DynamicSet.bind(binder(), WebUiPlugin.class).toInstance(new JavaScriptPlugin("checks-mock.js"));

    get(CONFIG_KIND, "config").to(GetConfig.class);
    get(PROJECT_KIND, "config").to(GetProjectConfig.class);
    get(REVISION_KIND, "checks_fetch").to(GetChecksFetch.class);
  }
}
