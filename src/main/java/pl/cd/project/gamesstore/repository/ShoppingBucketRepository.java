package pl.cd.project.gamesstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cd.project.gamesstore.model.pg.ShoppingBucket;
import pl.cd.project.gamesstore.model.pg.UserApp;

public interface ShoppingBucketRepository extends JpaRepository<ShoppingBucket, Long> {
    ShoppingBucket findByUserApp(UserApp user);
}
