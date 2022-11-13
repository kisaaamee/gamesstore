package pl.cd.project.gamesstore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.cd.project.gamesstore.model.esdocuments.EsGameBox;
import pl.cd.project.gamesstore.repository.es.EsGameBoxRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class EsGameBoxService {

    private final EsGameBoxRepository gameBoxRepository;

    public void save(final EsGameBox gameBox) {
        gameBoxRepository.save(gameBox);
    }

    public EsGameBox findById(final String id) {
        return gameBoxRepository.findById(id).orElse(null);
    }
}
