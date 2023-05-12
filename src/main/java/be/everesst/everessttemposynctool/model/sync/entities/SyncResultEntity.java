package be.everesst.everessttemposynctool.model.sync.entities;

import jakarta.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "SYNC_RESULTS")
public class SyncResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "SYNC_RESULT_UUID")
    private UUID syncResultUUID;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "syncDayId")
    private Set<SyncDayEntity> syncDays;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "syncRecordId")
    private Set<SyncRecordEntity> syncRecords;

    protected SyncResultEntity() {
    }

    public SyncResultEntity(UUID syncResultUUID) {
        this.syncResultUUID = syncResultUUID;
    }

    public Set<SyncRecordEntity> getSyncRecords() {
        return syncRecords;
    }

    public void setSyncDays(Set<SyncDayEntity> syncDays) {
        this.syncDays = syncDays;
    }

    public void setSyncRecords(Set<SyncRecordEntity> syncRecords) {
        this.syncRecords = syncRecords;
    }

    public Set<SyncDayEntity> findAllSyncDayEntitiesByUUIDAndResourceIdAndDate(String resourceId, LocalDate date){
        LocalDate startDate = date.with(DayOfWeek.MONDAY);
        LocalDate endDate = date.with(DayOfWeek.FRIDAY);
        Set<SyncDayEntity> syncDayEntities = new HashSet<>();
        syncDays.forEach(syncDayEntity -> {
            if ((syncDayEntity.getDate().isAfter(startDate) || syncDayEntity.getDate().equals(startDate)) &&
                    (syncDayEntity.getDate().isBefore(endDate) || syncDayEntity.getDate().equals(endDate)) &&
                    syncDayEntity.getResourceId().matches(resourceId)) {
                syncDayEntities.add(syncDayEntity);
            }
        });
        return syncDayEntities;
    }

    public String getSlackInput(){
        StringBuilder result = new StringBuilder();
        for (SyncRecordEntity syncRecordEntity: syncRecords) {
            result.append(syncRecordEntity.toString());
        }
        return result.toString();
    }
}
