package pl.cd.project.gamesstore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.cd.project.gamesstore.model.esdocuments.EsGameBox;
import pl.cd.project.gamesstore.service.EsGameBoxService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/gameboxs")
public class EsGameBoxController {

    private final EsGameBoxService gameBoxService;

    @PostMapping
    public void save(@RequestBody final EsGameBox gameBox) {
        gameBoxService.save(gameBox);
    }

    @GetMapping("/{id}")
    public EsGameBox findById(@PathVariable final String id) {
        return gameBoxService.findById(id);
    }
}
