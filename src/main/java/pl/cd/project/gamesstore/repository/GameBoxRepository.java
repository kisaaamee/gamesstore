package pl.cd.project.gamesstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cd.project.gamesstore.model.pg.GameBox;

import java.util.List;

public interface GameBoxRepository extends JpaRepository<GameBox, Long> {
    List<GameBox> findByTitle(String title);
}
