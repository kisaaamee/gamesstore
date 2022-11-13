package pl.cd.project.gamesstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.cd.project.gamesstore.service.IndexService;

@RestController
@RequestMapping("/api/index")
@RequiredArgsConstructor
public class IndexController {
    private final IndexService indexService;

    @PostMapping("/recreate")
    public void recreateIndices() {
        indexService.recreateIndices(true);
    }
}
