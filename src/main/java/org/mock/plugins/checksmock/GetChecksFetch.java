package org.mock.plugins.checksmock;

import com.google.common.flogger.FluentLogger;
import com.google.gerrit.extensions.restapi.Response;
import com.google.gerrit.extensions.restapi.RestReadView;
import com.google.gerrit.json.OutputFormat;
import com.google.gerrit.server.change.RevisionResource;
import com.google.gerrit.server.config.PluginConfig;
import com.google.gerrit.server.config.PluginConfigFactory;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;
import java.util.Set;

@Singleton
class GetChecksFetch implements RestReadView<RevisionResource> {
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  private final PluginConfigFactory config;

  @Inject
  GetChecksFetch(PluginConfigFactory config) {
    this.config = config;
  }

  @Override
  public Response<Object> apply(RevisionResource rsrc) {
    try {
      PluginConfig gerritconf = this.config.getFromGerritConfig("checks-mock");

      if (gerritconf.getBoolean("enabled", false)) {
        return Response.ok(new FetchResponse("OK", null));
      }

      HttpRequest request =
          HttpRequest.newBuilder()
              .uri(
                  URI.create(
                      Optional.ofNullable(gerritconf.getString("fetchEndpointURL"))
                          .orElseThrow(
                              () ->
                                  new RuntimeException("Missing fetchEndpointURL configuration"))))
              .timeout(Duration.ofSeconds(gerritconf.getInt("fetchEndpointURLRequestTimeout", 20)))
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .POST(
                  HttpRequest.BodyPublishers.ofString(
                      OutputFormat.JSON.newGson().toJson(new FetchDetails(rsrc))))
              .build();
      HttpClient client =
          HttpClient.newBuilder()
              .version(HttpClient.Version.HTTP_1_1)
              .followRedirects(HttpClient.Redirect.NORMAL)
              .connectTimeout(
                  Duration.ofSeconds(gerritconf.getInt("fetchEndpointURLConnectTimeout", 20)))
              .build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() != 200) {
        throw new RuntimeException(
            String.format("Fetch endpoint HTTP status %d", response.statusCode()));
      }
      return Response.ok(OutputFormat.JSON.newGson().fromJson(response.body(), JsonObject.class));
    } catch (Exception e) {
      logger.atSevere().withCause(e).log("Cannot interact with fetch endpoint");
      return Response.ok(new FetchResponse("ERROR", e.getMessage()));
    }
  }

  static class FetchDetails {
    @SerializedName("accountId")
    Integer accountId;

    @SerializedName("emailAddresses")
    Set<String> emailAddresses;

    @SerializedName("project")
    String project;

    @SerializedName("changeId")
    Integer changeId;

    @SerializedName("revision")
    Integer revision;

    FetchDetails(RevisionResource rsrc) {
      accountId = rsrc.getAccountId().get();
      emailAddresses = rsrc.getUser().getEmailAddresses();
      project = rsrc.getProject().get();
      changeId = rsrc.getPatchSet().id().changeId().get();
      revision = rsrc.getPatchSet().id().get();
    }
  }

  static class FetchResponse {
    @SerializedName("responseCode")
    String responseCode;

    @SerializedName("errorMessage")
    String errorMessage;

    FetchResponse(String responseCode, String errorMessage) {
      this.responseCode = responseCode;
      this.errorMessage = errorMessage;
    }
  }
}
