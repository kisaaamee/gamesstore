package pl.cd.project.gamesstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.cd.project.gamesstore.repository.GameBoxRepository;
import pl.cd.project.gamesstore.service.GameBoxServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class GameBoxServiceTest {
    @Autowired
    private GameBoxServiceImpl gameBoxServiceImp;

    @MockBean
    private GameBoxRepository gameBoxRepository;



//    @Test
//    public void getExistHash() {
//        GameBox gameBoxEntity = new GameBox();
//        gameBoxEntity.setUuid(UUID.randomUUID());
//        gameBoxEntity.setTitle("11");
//        gameBoxEntity.setPublic(true);
//        when(gameBoxRepository.getByHash("1")).thenReturn(gameBoxEntity);
//
//        GameBoxResponse exprected = new GameBoxResponse("11", true);
//        GameBoxResponse actual = gameBoxServiceImp.getByUuid("1");
//        assertEquals(exprected, actual);
//    }
}
