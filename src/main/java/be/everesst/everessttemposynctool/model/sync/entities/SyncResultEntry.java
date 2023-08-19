package be.everesst.everessttemposynctool.model.sync.entities;

import com.cegeka.horizon.camis.domain.EmployeeIdentification;
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
    private UUID uuid;

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

    public SyncResultEntry(UUID uuid, EmployeeIdentification employee, String slackHandle, SyncResultType resultType, SyncResult syncResult) {
        this.uuid = uuid;
        this.employeeName = employee.name();
        this.slackHandle = slackHandle;
        this.resultType = resultType;
        this.syncResult = syncResult;
    }

    public Long id() {
        return id;
    }

    public SyncResult syncResult() {
        return syncResult;
    }
}
