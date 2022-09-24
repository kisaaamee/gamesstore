package pl.cd.project.gamesstore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.cd.project.gamesstore.dto.ShoppingBucketDTO;
import pl.cd.project.gamesstore.dto.ShoppingBucketDetailsDTO;
import pl.cd.project.gamesstore.model.pg.GameBox;
import pl.cd.project.gamesstore.model.pg.ShoppingBucket;
import pl.cd.project.gamesstore.model.pg.UserApp;
import pl.cd.project.gamesstore.repository.ShoppingBucketRepository;
import pl.cd.project.gamesstore.service.ShoppingBucketServiceImpl;
import pl.cd.project.gamesstore.service.UserServiceImpl;

import java.security.Principal;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.ListIterator;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ShoppingBucketController {
    private final ShoppingBucketServiceImpl bucketService;
    private final ShoppingBucketRepository bucketRepository;
    private final UserServiceImpl gameBoxServiceImp;

    @GetMapping("/shoppingBucket/show/{id}")
    public String aboutBucket(@PathVariable Long id, Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("bucket", new ShoppingBucketDTO());
        } else {
//            UserApp user = gameBoxServiceImp.getUserByPrincipal(principal);
            UserApp user = gameBoxServiceImp.getUserByid(id);
            ShoppingBucket bucket = bucketRepository.findByUserApp(user);
            ShoppingBucketDTO bucketDTO = bucketService.getBucketByUser(user.getEmail());
            model.addAttribute("buckets", bucketDTO.getBucketDetails());
            model.addAttribute("bucket", bucket);
            model.addAttribute("user", gameBoxServiceImp.getUserByPrincipal(principal));
        }
        return "shoppingBucket";
    }

    @PostMapping("/shoppingBucket/delete/{id}")
    public String deleteBucketDetailDTO(@PathVariable Long id, Principal principal) {
        UserApp user = gameBoxServiceImp.getUserByPrincipal(principal);
        ShoppingBucketDTO bucketDTO = bucketService.getBucketByUser(principal.getName());
        ShoppingBucket bucket = bucketRepository.findByUserApp(user);
        List<GameBox> gameBoxes = bucket.getGameBoxes();
        for (ListIterator<GameBox> gameBox = gameBoxes.listIterator(); gameBox.hasNext();) {
            GameBox value;
            try {
                value = gameBox.next();
            } catch (ConcurrentModificationException ex) {
                return "redirect:/shoppingBucket/show/" + user.getId();
            }
            if (value.getId().equals(id)) {
                try {
                    gameBox.remove();
                    bucketRepository.save(bucket);
                } catch (ConcurrentModificationException ex) {
                    return "redirect:/shoppingBucket/show/" + user.getId();
                }
            }
        }
        return "redirect:/shoppingBucket/show/" + user.getId();
    }

}
