package pl.cd.project.gamesstore.model.pg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingBucket extends BaseSyncEntity<ShoppingBucket> {
    @ManyToMany
    @JoinTable(name="shoppingBuckets_gameBoxes",
    joinColumns = @JoinColumn(name = "shoppingBucket_id"), inverseJoinColumns = @JoinColumn(name = "gameBox_id"))
    private List<GameBox> gameBoxes;

    @PrePersist
    private void init() {
        uuid = UUID.randomUUID();
    }
}
