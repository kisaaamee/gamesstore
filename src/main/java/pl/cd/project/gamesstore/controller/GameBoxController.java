package pl.cd.project.gamesstore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.cd.project.gamesstore.configurations.MockMultipartFile;
import pl.cd.project.gamesstore.model.pg.GameBox;
import pl.cd.project.gamesstore.model.pg.GameBoxImage;
import pl.cd.project.gamesstore.model.pg.UserApp;
import pl.cd.project.gamesstore.repository.GameBoxRepository;
import pl.cd.project.gamesstore.repository.UserAppRepository;
import pl.cd.project.gamesstore.service.GameBoxServiceImpl;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.security.Principal;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GameBoxController {
    private final GameBoxServiceImpl gameBoxServiceImp;
    private final GameBoxRepository gameBoxRepository;

    @GetMapping("/")
    public String gameBoxes(@RequestParam(name = "searchWorld", required = false) String title, Principal principal, Model model) {
        model.addAttribute("gameBoxes", gameBoxServiceImp.listGameBoxes(title));
        model.addAttribute("user", gameBoxServiceImp.getUserByPrincipal(principal));
        model.addAttribute("searchWorld",title);
        return "gameBoxes";
    }

    @GetMapping("/gameBox/{id}")
    public String gameBoxDetails(@PathVariable Long id, Model model, Principal principal) {
        GameBox game = gameBoxServiceImp.getGameBoxById(id);
        model.addAttribute("user", gameBoxServiceImp.getUserByPrincipal(principal));
        model.addAttribute("gameBox", game);
        model.addAttribute("images", game.getImages());
        return "gameBoxDetails";
    }

    @PostMapping("/gameBox/create")
    public String createGameBox(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3,
                                GameBox gameBox, Principal principal) throws IOException {
        gameBox.setUuid(UUID.randomUUID());
        gameBoxServiceImp.saveGameBox(principal, gameBox, file1, file2, file3);
        return "redirect:/my/products";
    }

    @PostMapping("/gameBox/createSample")
    public String createSampleGameBox(Principal principal) throws IOException {
        UserApp user = gameBoxServiceImp.getUserByPrincipal(principal);

        GameBox gameBox1 = new GameBox();
        gameBox1.setUser(user);
        gameBox1.setTitle("Bears Are Not What They Seems To Be");
        gameBox1.setUnitPrice(70);
        gameBox1.setDescription("Nice adventure hunting game");
        File file1 = new File("src/main/resources/manual/demoImages/bearsAreNotWhatTheySeems/bear1.jpg");
        File file2 = new File("src/main/resources/manual/demoImages/bearsAreNotWhatTheySeems/bear1.jpg");
        File file3 = new File("src/main/resources/manual/demoImages/bearsAreNotWhatTheySeems/bear1.jpg");
        List<File> list1 = new ArrayList<>();
        list1.add(file1);
        list1.add(file2);
        list1.add(file3);
        List<GameBoxImage> gameBoxImages1 = createImages(list1);
        for (GameBoxImage image : gameBoxImages1) {
            gameBox1.addImageToGameBox(image);
        }
        gameBox1.setUuid(UUID.randomUUID());
        GameBox gameBoxFromDb = gameBoxRepository.save(gameBox1);
        gameBoxFromDb.setPreviewImageId(gameBoxFromDb.getImages().get(0).getId());
        gameBoxRepository.save(gameBox1);

        GameBox gameBox2 = new GameBox();
        gameBox2.setUser(user);
        gameBox2.setTitle("Test Task Creator Simulator");
        gameBox2.setUnitPrice(20);
        gameBox2.setDescription("Crazy weekend adventure");
        File file4 = new File("src/main/resources/manual/demoImages/testTaskCreatorSimulator/test1.jpg");
        File file5 = new File("src/main/resources/manual/demoImages/testTaskCreatorSimulator/test2.jpg");
        File file6 = new File("src/main/resources/manual/demoImages/testTaskCreatorSimulator/test3.jpg");
        List<File> list2 = new ArrayList<>();
        list2.add(file4);
        list2.add(file5);
        list2.add(file6);
        List<GameBoxImage> gameBoxImages2 = createImages(list2);
        for (GameBoxImage image : gameBoxImages2) {
            gameBox2.addImageToGameBox(image);
        }
        gameBox2.setUuid(UUID.randomUUID());
        GameBox gameBoxFromDb1 = gameBoxRepository.save(gameBox2);
        gameBoxFromDb1.setPreviewImageId(gameBoxFromDb1.getImages().get(0).getId());
        gameBoxRepository.save(gameBox2);

        GameBox gameBox3 = new GameBox();
        gameBox3.setUser(user);
        gameBox3.setTitle("Future Loves Gucci");
        gameBox3.setUnitPrice(100);
        gameBox3.setDescription("Crazy weekend adventure");
        File file7 = new File("src/main/resources/manual/demoImages/futureLovesGucci/future1.jpg");
        File file8 = new File("src/main/resources/manual/demoImages/futureLovesGucci/future2.jpg");
        List<File> list3 = new ArrayList<>();
        list3.add(file7);
        list3.add(file8);
        List<GameBoxImage> gameBoxImages3 = createImages(list3);
        for (GameBoxImage image : gameBoxImages3) {
            gameBox3.addImageToGameBox(image);
        }
        gameBox3.setUuid(UUID.randomUUID());
        GameBox gameBoxFromDb2 = gameBoxRepository.save(gameBox3);
        gameBoxFromDb2.setPreviewImageId(gameBoxFromDb2.getImages().get(0).getId());
        gameBoxRepository.save(gameBox3);


        GameBox gameBox4 = new GameBox();
        gameBox4.setUser(user);
        gameBox4.setTitle("Samurai Never Die");
        gameBox4.setUnitPrice(90);
        gameBox4.setDescription("The fastest sword in the world");
        File file10 = new File("src/main/resources/manual/demoImages/samuraiNeverDie/samurai1.jpg");
        File file11 = new File("src/main/resources/manual/demoImages/samuraiNeverDie/samurai2.jpg");
        List<File> list4 = new ArrayList<>();
        list4.add(file10);
        list4.add(file11);
        List<GameBoxImage> gameBoxImages4 = createImages(list4);
        for (GameBoxImage image : gameBoxImages4) {
            gameBox4.addImageToGameBox(image);
        }
        gameBox4.setUuid(UUID.randomUUID());
        GameBox gameBoxFromDb3 = gameBoxRepository.save(gameBox4);
        gameBoxFromDb3.setPreviewImageId(gameBoxFromDb3.getImages().get(0).getId());
        gameBoxRepository.save(gameBox4);


        return "redirect:/my/products";
    }

    private List<GameBoxImage> createImages(List<File> files) throws IOException {
        List<GameBoxImage> fileList = new ArrayList<>();
        for (File file : files) {
            FileInputStream input1 = new FileInputStream(file);
            MultipartFile multipartFile1 = new MockMultipartFile("file",
                    file.getName(), "image/jpeg", IOUtils.toByteArray(input1));

            GameBoxImage image = new GameBoxImage();
            image.setName("bear1");
            image.setOriginalFileName("bear1.jpg");
            image.setContentType("image/jpeg");
            image.setSize(multipartFile1.getSize());
            image.setBytes(multipartFile1.getBytes());
            fileList.add(image);
        }
        return fileList;
    }

    @PostMapping("/gameBox/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Principal principal) {
        gameBoxServiceImp.deleteGameBox(gameBoxServiceImp.getUserByPrincipal(principal), id);
        return "redirect:/my/products";
    }

    @GetMapping("/gameBox/edit/{id}")
    public String editGameBox(@PathVariable Long id, Principal principal, Model model) {
        GameBox game = gameBoxServiceImp.getGameBoxById(id);
        model.addAttribute("user", gameBoxServiceImp.getUserByPrincipal(principal));
        model.addAttribute("gameBox", game);
        model.addAttribute("images", game.getImages());
        return "gameBoxDetailsEdit";
    }

    @PostMapping("/gameBox/saveEdited")
    public String saveEditedGameBox(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3, @RequestParam("gameBoxId") GameBox gameBoxOrig,
                                GameBox gameBox, Principal principal) throws IOException {
        gameBox.setUuid(UUID.randomUUID());
        log.info("Original title: {}", gameBoxOrig.getTitle());
        if (!gameBox.getTitle().isEmpty()) {
            gameBoxOrig.setTitle(gameBox.getTitle());
        }
        if (!gameBox.getDescription().isEmpty()) {
            gameBoxOrig.setDescription(gameBox.getDescription());
        }
        gameBoxOrig.setUnitPrice(gameBox.getUnitPrice());
        GameBoxImage image1;
        GameBoxImage image2;
        GameBoxImage image3;
        if(file1.getSize() != 0) {
            image1 = gameBoxServiceImp.toImageEntity(file1);
            gameBoxOrig.addImageToGameBoxAtIndex(image1, 0);

        }
        if(file2.getSize() != 0) {
            image2 = gameBoxServiceImp.toImageEntity(file2);
            gameBoxOrig.addImageToGameBoxAtIndex(image2, 1);
        }
        if(file3.getSize() != 0) {
            image3 = gameBoxServiceImp.toImageEntity(file3);
            gameBoxOrig.addImageToGameBoxAtIndex(image3, 2);
        }
        gameBoxOrig.setPreviewImageId(null);
        GameBox gameBoxFromDb = gameBoxRepository.save(gameBoxOrig);
        gameBoxFromDb.setPreviewImageId(gameBoxFromDb.getImages().get(0).getId());
        gameBoxRepository.save(gameBoxOrig);
        return "redirect:/my/products";
    }

    @GetMapping("/my/products")
    public String userGameBoxes(Principal principal, Model model) {
        UserApp user = gameBoxServiceImp.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("gameBoxes", user.getProducts());
        return "my-products";
    }

    @GetMapping("/shoppingBucket/{id}")
    public String addBucket(@PathVariable Long id, Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/";
        }
        UserApp user = gameBoxServiceImp.getUserByPrincipal(principal);
        log.info("User's email is " + user.getEmail());
        gameBoxServiceImp.addToUserBucket(id, user);
        return "redirect:/";
    }

}
