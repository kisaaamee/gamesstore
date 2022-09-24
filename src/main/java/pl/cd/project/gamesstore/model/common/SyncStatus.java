package pl.cd.project.gamesstore.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SyncStatus {
    @JsonProperty("new")
    NEW("new"),
    @JsonProperty("error")
    ERROR("error");

    private final String label;

    SyncStatus(String label) {this.label = label;}

}
