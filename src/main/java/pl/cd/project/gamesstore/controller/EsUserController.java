package pl.cd.project.gamesstore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.cd.project.gamesstore.model.esdocuments.EsUser;
import pl.cd.project.gamesstore.service.EsUserService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/users")
public class EsUserController {
    private final EsUserService userService;

    @PostMapping
    public void index(@RequestBody final EsUser user) {
        userService.index(user);
    }

    @GetMapping("/{id}")
    public EsUser getById(@PathVariable final String id) {
        return userService.getById(id);
    }
}
