package pl.cd.project.gamesstore.model.pg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameBoxImage extends BaseSyncEntity<GameBoxImage> {

    private String name;
    private String originalFileName;
    private Long size;
    private String contentType;
    private boolean isPreviewImage;
//    @Lob
    private byte[] bytes;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private GameBox gameBox;

    @PrePersist
    private void init() {
        uuid = UUID.randomUUID();
    }

    @Column(length = 16000000)
    @Basic(fetch = FetchType.LAZY)
    public byte[] getBytes() {
        return bytes;
    }
}
