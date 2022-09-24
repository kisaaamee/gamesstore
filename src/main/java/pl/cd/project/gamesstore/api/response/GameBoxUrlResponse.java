package pl.cd.project.gamesstore.api.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GameBoxUrlResponse {
    private final String url;
}
