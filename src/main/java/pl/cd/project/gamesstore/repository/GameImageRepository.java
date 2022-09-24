package pl.cd.project.gamesstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cd.project.gamesstore.model.pg.GameBoxImage;

public interface GameImageRepository extends JpaRepository<GameBoxImage, Long> {
}
