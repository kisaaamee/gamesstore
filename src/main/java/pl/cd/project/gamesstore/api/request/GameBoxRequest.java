package pl.cd.project.gamesstore.api.request;

import lombok.Data;

@Data
public class GameBoxRequest {
    private String name;
    private long expirationTimeSeconds;
    private GameStatus gameStatus;

}
