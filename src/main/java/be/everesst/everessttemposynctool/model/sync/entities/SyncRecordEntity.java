package be.everesst.everessttemposynctool.model.sync.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "SYNC_RECORDS")
public class SyncRecordEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SYNC_RESULT_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SyncResultEntity syncResult;

    @Column(name = "RESOURCE_ID")
    private String resourceId;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;

    @Column(name = "ERROR_CODE")
    private int errorCode;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "HOURS_LOGGED")
    private double hoursLogged;

    @Column(name = "WORK_ORDER")
    private String workOrder;

    protected SyncRecordEntity() {
    }

    public SyncRecordEntity(String resourceId, String message, String employeeName, int errorCode, LocalDate startDate, double hoursLogged, String workOrder) {
        this.resourceId = resourceId;
        this.message = message;
        this.startDate = startDate;
        this.errorCode = errorCode;
        this.employeeName = employeeName;
        this.workOrder = workOrder;
        this.hoursLogged = hoursLogged;
    }

    public long getId() {
        return id;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getMessage() {
        return message;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getErrorCode() { return errorCode; }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getWorkOrder() {
        return workOrder;
    }

    public double getHoursLogged() {
        return hoursLogged;
    }

    public void setSyncResult(SyncResultEntity syncResult) {
        this.syncResult = syncResult;
    }

    @Override
    public String toString() {
        return resourceId + " : " + employeeName  + " : " + message +  "\n";
    }
}
