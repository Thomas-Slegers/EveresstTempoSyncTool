package be.everesst.everessttemposynctool.model.sync.entities;

import com.cegeka.horizon.camis.domain.ResourceId;
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

    @Column(name = "SYNC_UUID")
    private UUID syncUUID;

    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;

    @Column(name = "SLACK_NAME")
    private String slackHandle;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="value", column=@Column(name = "RESOURCE_ID"))
    })
    private ResourceId resourceId;

    @Column(name = "RESULT_TYPE")
    @Enumerated(EnumType.STRING)
    private SyncResultType resultType;

    @Lob
    @Column(name = "JSON_SYNC_RESULT")
    private SyncResult syncResult;

    SyncResultEntry(){}

    public SyncResultEntry(UUID uuid, String slackHandle, SyncResult syncResult) {
        this.syncUUID = uuid;
        this.slackHandle = slackHandle;
        this.resourceId = syncResult.employee().resourceId();
        this.employeeName = syncResult.employee().name();
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

    public ResourceId resourceId() {
        return resourceId;
    }
}
