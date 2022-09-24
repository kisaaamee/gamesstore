package pl.cd.project.gamesstore.service;

import pl.cd.project.gamesstore.dto.ShoppingBucketDTO;
import pl.cd.project.gamesstore.model.pg.ShoppingBucket;
import pl.cd.project.gamesstore.model.pg.UserApp;


import java.util.List;

public interface ShoppingBucketService {
    ShoppingBucket createBucket(UserApp user, List<Long> gameBoxesIds);
    void addGames(ShoppingBucket bucket, List<Long> gameBoxesIds);
    ShoppingBucketDTO getBucketByUser(String email);
}
