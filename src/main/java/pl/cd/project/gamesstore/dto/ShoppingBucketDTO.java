package pl.cd.project.gamesstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingBucketDTO {
    private int amountGameBoxes;
    private Double sum;
    private List<ShoppingBucketDetailsDTO> bucketDetails = new ArrayList<>();

    public void aggregate() {
        this.amountGameBoxes = bucketDetails.size();
        this.sum = bucketDetails.stream()
                .map(ShoppingBucketDetailsDTO::getSum)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
