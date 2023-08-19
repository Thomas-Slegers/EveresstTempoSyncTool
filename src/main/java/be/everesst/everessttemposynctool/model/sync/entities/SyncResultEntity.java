package be.everesst.everessttemposynctool.model.sync.entities;

import jakarta.persistence.*;
import org.threeten.extra.LocalDateRange;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return syncDays.stream().filter(syncDayEntity ->
                        LocalDateRange.of(date, date.plusDays(7)).contains(syncDayEntity.getDate()))
                        .filter(syncDayEntity -> syncDayEntity.getResourceId().equals(resourceId))
                                .collect(Collectors.toSet());
    }

    public Set<SyncDayEntity> findAllSyncDayEntitiesByUUIDAndResourceIdAndDate(String resourceId) {
        return syncDays.stream()
                .filter(syncDayEntity -> syncDayEntity.getResourceId().equals(resourceId))
                .collect(Collectors.toSet());
    }

    public String getSlackInput(){
        StringBuilder result = new StringBuilder();
        for (SyncRecordEntity syncRecordEntity: syncRecords) {
            if (!syncRecordEntity.getMessage().toLowerCase().contains("no sync action needed for")) {
                result.append(syncRecordEntity.slackInput());
            }
        }
        return result.toString();
    }


}
