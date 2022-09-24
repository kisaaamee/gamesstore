package pl.cd.project.gamesstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cd.project.gamesstore.model.pg.UserApp;

public interface UserAppRepository extends JpaRepository<UserApp, Long> {
    UserApp findByEmail(String email);

}
