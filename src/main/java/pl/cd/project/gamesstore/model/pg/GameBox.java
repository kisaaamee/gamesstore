package pl.cd.project.gamesstore.model.pg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameBox extends BaseSyncEntity<GameBox> {

    private String title;
    private UUID uuid;
    @Column(columnDefinition = "text")
    private String description;
    private double unitPrice;
    private LocalDateTime lifeTime;
    private boolean isPublic;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
    mappedBy = "gameBox")
    private List<GameBoxImage> images = new ArrayList<>();
    private Long previewImageId;
    private LocalDateTime dateofCreated;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private UserApp user;

    @PrePersist
    private void init() {
        dateofCreated = LocalDateTime.now();
    }

    public void addImageToGameBox(GameBoxImage image) {
        image.setGameBox(this);
        images.add(image);
    }

    public void addImageToGameBoxAtIndex(GameBoxImage image, int index) {
        image.setGameBox(this);
        images.add(index, image);
    }
}
