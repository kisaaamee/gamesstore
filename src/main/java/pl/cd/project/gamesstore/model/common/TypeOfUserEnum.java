package pl.cd.project.gamesstore.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

public enum TypeOfUserEnum implements GrantedAuthority {
    @JsonProperty("1")
    BUYER("1", "buyer"),
    @JsonProperty("2")
    SELLER("2", "seller"),
    @JsonProperty("3")
    ADMIN("3", "admin");

    @Getter
    private final String code;

    @Getter
    private final String type;

    TypeOfUserEnum(String code, String type) {
        this.code = code;
        this.type = type;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
