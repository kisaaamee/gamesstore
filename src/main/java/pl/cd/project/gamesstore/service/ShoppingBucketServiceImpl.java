package pl.cd.project.gamesstore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.cd.project.gamesstore.dto.ShoppingBucketDTO;
import pl.cd.project.gamesstore.dto.ShoppingBucketDetailsDTO;
import pl.cd.project.gamesstore.model.pg.GameBox;
import pl.cd.project.gamesstore.model.pg.ShoppingBucket;
import pl.cd.project.gamesstore.model.pg.UserApp;
import pl.cd.project.gamesstore.repository.GameBoxRepository;
import pl.cd.project.gamesstore.repository.ShoppingBucketRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShoppingBucketServiceImpl implements ShoppingBucketService {
    private final ShoppingBucketRepository bucketRepository;
    private final GameBoxRepository gameBoxRepository;
    private final UserServiceImpl userService;

    @Override
    @Transactional
    public ShoppingBucket createBucket(UserApp user, List<Long> gameBoxesIds) {
        ShoppingBucket bucket = new ShoppingBucket();
        bucket.setUserApp(user);
        List<GameBox> gameBoxes = getCollectRefGameBoxesByIds(gameBoxesIds);
        bucket.setGameBoxes(gameBoxes);
        return bucketRepository.save(bucket);
    }

    private List<GameBox> getCollectRefGameBoxesByIds(List<Long> gameBoxesIds) {
        return gameBoxesIds.stream()
                // REMEMBER gets link to the object, find by id gets the whole object
                .map(gameBoxRepository::getOne)
                .collect(Collectors.toList());
    }


    @Override
    public void addGames(ShoppingBucket bucket, List<Long> gameBoxesIds) {
        List<GameBox> gameBoxes = bucket.getGameBoxes();
        List<GameBox> newGameBoxes = gameBoxes == null ? new ArrayList<>() : new ArrayList<>(gameBoxes);
        newGameBoxes.addAll(getCollectRefGameBoxesByIds(gameBoxesIds));
        bucket.setGameBoxes(newGameBoxes);
        bucketRepository.save(bucket);
    }

    @Override
    public ShoppingBucketDTO getBucketByUser(String email) {
        UserApp user = userService.findByEmail(email);
        if (user == null || user.getBucket() == null) {
            return new ShoppingBucketDTO();
        }

        ShoppingBucketDTO bucketDTO = new ShoppingBucketDTO();
        Map<Long, ShoppingBucketDetailsDTO> mapByGameBoxId = new HashMap<>();

        List<GameBox> gameBoxes = user.getBucket().getGameBoxes();
        for (GameBox gameBox : gameBoxes) {
            ShoppingBucketDetailsDTO detail = mapByGameBoxId.get(gameBox.getId());
            if (detail == null) {
                mapByGameBoxId.put(gameBox.getId(), new ShoppingBucketDetailsDTO(gameBox));
            } else {
                detail.setAmount(new BigDecimal(1.0));
                detail.setSum(detail.getSum() + Double.valueOf(gameBox.getUnitPrice()));
            }
        }
        bucketDTO.setBucketDetails(new ArrayList<>(mapByGameBoxId.values()));
        bucketDTO.aggregate();
        return bucketDTO;
    }
}
