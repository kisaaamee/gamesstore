package pl.cd.project.gamesstore.api.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.cd.project.gamesstore.api.request.GameStatus;

@Data
@RequiredArgsConstructor
public class GameBoxResponse {
    private final String name;
    private final boolean isPublic;
}
