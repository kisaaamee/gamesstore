package pl.cd.project.gamesstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.cd.project.gamesstore.model.pg.GameBox;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class ShoppingBucketDetailsDTO {
    private String title;
    private Long gameBoxId;
    private double unitPrice;
    private BigDecimal amount;
    private double sum;

    public ShoppingBucketDetailsDTO(GameBox gameBox) {
        this.title = gameBox.getTitle();
        this.gameBoxId = gameBox.getId();
        this.unitPrice = gameBox.getUnitPrice();
        this.amount = new BigDecimal(1.0);
        this.sum = Double.valueOf(gameBox.getUnitPrice());
    }
}
