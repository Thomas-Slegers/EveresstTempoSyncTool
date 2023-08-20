package be.everesst.everessttemposynctool.model.sync.entities;

import com.cegeka.horizon.camis.sync.logger.model.result.SyncResult;
import com.cegeka.horizon.camis.sync.logger.model.result.SyncResultType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "SYNC_RESULT_ENTRY")
public class SyncResultEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "SYNC_RESULT_UUID")
    private UUID syncUUID;

    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;

    @Column(name = "SLACK_NAME")
    private String slackHandle;

    @Column(name = "RESOURCE_ID")
    private String resourceId;

    @Column(name = "RESULT_TYPE")
    private SyncResultType resultType;

    @Lob
    @Column(name = "JSON_SYNC_RESULT")
    private SyncResult syncResult;

    public SyncResultEntry(UUID uuid, String slackHandle, SyncResult syncResult) {
        this.syncUUID = uuid;
        this.slackHandle = slackHandle;
        this.slackHandle = syncResult.employee().name();
        this.resultType = syncResult.type();
        this.syncResult = syncResult;
    }

    public Long id() {
        return id;
    }

    public SyncResult syncResult() {
        return syncResult;
    }

    public SyncResult contents(){
        return syncResult;
    }
}
