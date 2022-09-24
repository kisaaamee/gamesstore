package pl.cd.project.gamesstore.service;

import pl.cd.project.gamesstore.api.request.GameBoxRequest;
import pl.cd.project.gamesstore.api.response.GameBoxResponse;
import pl.cd.project.gamesstore.api.response.GameBoxUrlResponse;
import pl.cd.project.gamesstore.model.pg.UserApp;


public interface GameBoxService {

    GameBoxResponse getByUuid(String uuid);
    GameBoxUrlResponse create(GameBoxRequest request);
    void addToUserBucket(Long gameBoxId, UserApp user);
}
