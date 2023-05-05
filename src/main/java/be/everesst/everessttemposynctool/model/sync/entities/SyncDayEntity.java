package be.everesst.everessttemposynctool.model.sync.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "SYNC_DAYS")
public class SyncDayEntity {

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

    @Column(name = "DATE")
    private LocalDate date;
    @Column(name = "WORK_ORDER")
    private String workOrder;

    @Column(name = "HOURS_LOGGED_CAMIS")
    private double hoursLoggedCamis;

    @Column(name = "HOURS_LOGGED_JIRA")
    private double hoursLoggedJira;

    protected SyncDayEntity() {
    }

    public SyncDayEntity(String resourceId, LocalDate date, String workOrder, double hoursLoggedCamis, double hoursLoggedJira) {
        this.resourceId = resourceId;
        this.date = date;
        this.workOrder = workOrder;
        this.hoursLoggedCamis = hoursLoggedCamis;
        this.hoursLoggedJira = hoursLoggedJira;
    }

    public long getId() {
        return id;
    }

    public String getResourceId() {
        return resourceId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getWorkOrder() {
        return workOrder;
    }

    public double getHoursLoggedCamis() {
        return hoursLoggedCamis;
    }

    public double getHoursLoggedJira() {
        return hoursLoggedJira;
    }

    public void setSyncResult(SyncResultEntity syncResult) {
        this.syncResult = syncResult;
    }

}