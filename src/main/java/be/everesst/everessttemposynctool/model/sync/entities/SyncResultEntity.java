package be.everesst.everessttemposynctool.model.sync.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "SYNC_RESULTS")
public class SyncResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "SYNC_RESULT_UUID")
    private UUID syncResultUUID;

    protected SyncResultEntity() {
    }

    public SyncResultEntity(UUID syncResultUUID) {
        this.syncResultUUID = syncResultUUID;
    }
}
