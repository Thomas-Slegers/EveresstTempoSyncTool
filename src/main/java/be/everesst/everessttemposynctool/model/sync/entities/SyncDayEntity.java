package be.everesst.everessttemposynctool.model.sync.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "SYNC_DAYS")
public class SyncDayEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long syncDayId;

    @Column(name = "RESOURCE_ID")
    private String resourceId;

    @Column(name = "DATE")
    private LocalDate date;
    @Column(name = "WORK_ORDER")
    private String workOrder;

    @Column(name = "HOURS_LOGGED_CAMIS")
    private double hoursLoggedCamis;

    @Column(name = "HOURS_LOGGED_TEMPO")
    private double hoursLoggedTempo;

    protected SyncDayEntity() {
    }

    public SyncDayEntity(String resourceId, LocalDate date, String workOrder, double hoursLoggedCamis, double hoursLoggedTempo) {
        this.resourceId = resourceId;
        this.date = date;
        this.workOrder = workOrder;
        this.hoursLoggedCamis = hoursLoggedCamis;
        this.hoursLoggedTempo = hoursLoggedTempo;
    }

    public long getId() {
        return syncDayId;
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

    public double getHoursLoggedTempo() {
        return hoursLoggedTempo;
    }
}