package be.everesst.everessttemposynctool.model.sync.entities;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "SYNC_RECORDS")
public class SyncRecordEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long syncRecordId;

    @Column(name = "RESOURCE_ID")
    private String resourceId;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;

    @Column(name = "SLACK_NAME")
    private String slackName;

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

    public SyncRecordEntity(String resourceId, String message, String employeeName, String slackName, int errorCode, LocalDate startDate, double hoursLogged, String workOrder) {
        this.resourceId = resourceId;
        this.message = message;
        this.employeeName = employeeName;
        this.slackName = slackName;
        this.startDate = startDate;
        this.errorCode = errorCode;

        this.workOrder = workOrder;
        this.hoursLogged = hoursLogged;
    }

    public long getId() {
        return syncRecordId;
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

    public String getSlackName() {
        return slackName;
    }

    public String getWorkOrder() {
        return workOrder;
    }

    public double getHoursLogged() {
        return hoursLogged;
    }

    public String slackInput(){
        if (slackName.isEmpty()){
            slackName = employeeName + " (NoCamisAccountMatched)";
        }
        return slackName + ": " + message + "\n";
    }
}
