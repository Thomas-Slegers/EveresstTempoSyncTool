package be.everesst.everessttemposynctool.model.sync.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

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

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "HOURS_LOGGED")
    private double hoursLogged;

    @Column(name = "WORK_ORDER")
    private String workOrder;

    protected SyncRecordEntity() {
    }

    public SyncRecordEntity(String message, String employeeName, LocalDate startDate, double hoursLogged, String workOrder) {
        this.message = message;
        this.startDate = startDate;
        this.employeeName = employeeName;
        this.workOrder = workOrder;
        this.hoursLogged = hoursLogged;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

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
        return "SyncEntity{id=" + id + '\'' + ", message=" + message + '\'' + ", employeeName='" + employeeName + '\'' + ", startDate=" + startDate + '\'' + ", hoursLogged=" + hoursLogged + '\'' + ", workOrder='" + workOrder + '\'' + '}';
    }
}
