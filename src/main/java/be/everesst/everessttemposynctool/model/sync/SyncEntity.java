package be.everesst.everessttemposynctool.model.sync;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "SYNC_TABLE")
public class SyncEntity {

    @Column(name = "SYNC_TABLE_UUID")
    private UUID syncTableUUID;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "TEAM")
    private String team;
    @Column(name = "DATE")
    private LocalDate date;
    @Column(name = "CAMIS_RESOURCE")
    private String camisResource;
    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;
    @Column(name = "TIME_CODE")
    private String timeCode;
    @Column(name = "WORK_ORDER")
    private String workOrder;
    @Column(name = "HOURS_LOGGED")
    private double hoursLogged;
    @Column(name = "ERROR_CODE")
    private Integer errorCode;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "SOLUTION")
    private String solution;

    protected SyncEntity() {
    }

    public SyncEntity(UUID syncTableId, String team, LocalDate date, String camisResource, String employeeName, String timeCode, String workOrder, double hoursLogged, int errorCode, String description, String solution) {
        this.syncTableUUID = syncTableId;
        this.team = team;
        this.date = date;
        this.camisResource = camisResource;
        this.employeeName = employeeName;
        this.timeCode = timeCode;
        this.workOrder = workOrder;
        this.hoursLogged = hoursLogged;
        this.errorCode = errorCode;
        this.description = description;
        this.solution = solution;
    }

    public UUID getSyncTableUUID() {
        return syncTableUUID;
    }

    public String getTeam() {
        return team;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCamisResource() {
        return camisResource;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getTimeCode() {
        return timeCode;
    }

    public String getWorkOrder() {
        return workOrder;
    }

    public double getHoursLogged() {
        return hoursLogged;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }

    public String getSolution() {
        return solution;
    }

    @Override
    public String toString() {
        return "SyncEntity{" +
                "syncTableUUID=" + syncTableUUID +
                ", id=" + id +
                ", team='" + team + '\'' +
                ", date=" + date +
                ", camisResource='" + camisResource + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", timeCode='" + timeCode + '\'' +
                ", workOrder='" + workOrder + '\'' +
                ", hoursLogged=" + hoursLogged +
                ", errorCode=" + errorCode +
                ", description='" + description + '\'' +
                ", solution='" + solution + '\'' +
                '}';
    }
}
