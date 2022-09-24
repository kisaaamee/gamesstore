package pl.cd.project.gamesstore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.cd.project.gamesstore.api.request.GameBoxRequest;
import pl.cd.project.gamesstore.api.response.GameBoxResponse;
import pl.cd.project.gamesstore.api.response.GameBoxUrlResponse;
import pl.cd.project.gamesstore.model.pg.GameBox;
import pl.cd.project.gamesstore.model.pg.GameBoxImage;
import pl.cd.project.gamesstore.model.pg.ShoppingBucket;
import pl.cd.project.gamesstore.model.pg.UserApp;
import pl.cd.project.gamesstore.repository.GameBoxRepository;
import pl.cd.project.gamesstore.repository.UserAppRepository;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app")
public class GameBoxServiceImpl implements GameBoxService {

    private String host = "http://abc.pl";
    private int publicListSize = 10;
    private final GameBoxRepository gameBoxRepository;
    private final UserAppRepository userAppRepository;
    private final ShoppingBucketServiceImpl bucketService;
    private final UserServiceImpl userService;

    public List<GameBox> listGameBoxes(String title) {
        if (title != null) {
            return gameBoxRepository.findByTitle(title);
        } else {
            return gameBoxRepository.findAll();
        }
    }

    @Override
    public GameBoxResponse getByUuid(String uuid) {
        return null;
    }

    @Override
    public GameBoxUrlResponse create(GameBoxRequest request) {
        GameBox gameBoxEntity = new GameBox();
        gameBoxEntity.setTitle(request.getName());
        gameBoxEntity.setPublic(true);
        gameBoxEntity.setLifeTime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        return new GameBoxUrlResponse(host + "/" + gameBoxEntity.getUuid());
    }

    public void saveGameBox(Principal principal, GameBox gameBox, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        gameBox.setUser(getUserByPrincipal(principal));
        GameBoxImage image1;
        GameBoxImage image2;
        GameBoxImage image3;
        if(file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            gameBox.addImageToGameBox(image1);
        }
        if(file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            gameBox.addImageToGameBox(image2);
        }
        if(file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            gameBox.addImageToGameBox(image3);
        }

        log.info("Saving new GameBox. Title {}; Author email {}", gameBox.getTitle(), gameBox.getUser().getEmail());
        gameBox.setUuid(UUID.randomUUID());
        GameBox gameBoxFromDb = gameBoxRepository.save(gameBox);
        gameBoxFromDb.setPreviewImageId(gameBoxFromDb.getImages().get(0).getId());
        gameBoxRepository.save(gameBox);
    }

    public UserApp getUserByPrincipal(Principal principal) {
        if (principal == null) {
            return new UserApp();
        }
        return userAppRepository.findByEmail(principal.getName());
    }

    public GameBoxImage toImageEntity(MultipartFile file) throws IOException {
        GameBoxImage image = new GameBoxImage();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteGameBox(UserApp user, Long id) {
        GameBox gameBox = gameBoxRepository.findById(id)
                .orElse(null);
        log.info("GameBox id{} ", gameBox.getId());
        if (gameBox != null) {
            if (gameBox.getUser().getId().equals(user.getId())) {
                gameBoxRepository.delete(gameBox);
                log.info("GameBox with id = {} was deleted", id);
            } else {
                log.error("User: {} haven't this GameBox with id = {}", user.getEmail(), id);
            }
        } else {
            log.error("GameBox with id = {} is not found", id);
        }
    }

    @Override
    @Transactional
    public void addToUserBucket(Long gameBoxId, UserApp user) {
        ShoppingBucket bucket = user.getBucket();
        if(bucket == null) {
            ShoppingBucket newBucket = bucketService.createBucket(user, Collections.singletonList(gameBoxId));
            user.setBucket(newBucket);
            userAppRepository.save(user);
        } else {
            bucketService.addGames(bucket, Collections.singletonList(gameBoxId));
        }

    }

    public GameBox getGameBoxById(Long id) {
     return gameBoxRepository.findById(id).orElse(null);
    }
}
