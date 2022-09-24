package pl.cd.project.gamesstore.model.pg;

import lombok.Data;
import pl.cd.project.gamesstore.model.common.SyncStatus;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class BaseSyncEntity<T> extends BaseEntity{
   protected Long versionId;

   @Column(name="uuid", nullable=false)
   protected UUID uuid;

   protected Boolean active = true;

   protected Boolean deleted = false;

   @ManyToOne(fetch = FetchType.LAZY)
   protected UserApp userApp;

   protected SyncStatus syncStatus = SyncStatus.NEW;

}
