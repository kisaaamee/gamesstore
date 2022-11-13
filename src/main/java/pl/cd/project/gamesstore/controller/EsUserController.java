package pl.cd.project.gamesstore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.cd.project.gamesstore.dto.es.SearchRequestDTO;
import pl.cd.project.gamesstore.model.esdocuments.User;
import pl.cd.project.gamesstore.service.EsUserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/users")
public class EsUserController {
    private final EsUserService userService;

    @PostMapping
    public void index(@RequestBody final User user) {
        userService.index(user);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable final String id) {
        return userService.getById(id);
    }

    @PostMapping("/search")
    public List<User> search(@RequestBody final SearchRequestDTO dto) {
        return userService.search(dto);
    }
}
