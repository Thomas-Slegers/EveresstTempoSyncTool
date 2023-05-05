package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.sync.entities.SyncDayEntity;
import be.everesst.everessttemposynctool.model.sync.repositories.SyncDayRepository;
import be.everesst.everessttemposynctool.model.sync.repositories.SyncResultRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class SyncDayService {
    private final SyncDayRepository syncDayRepository;
    private final SyncResultRepository syncResultRepository;

    public SyncDayService(SyncDayRepository syncDayRepository, SyncResultRepository syncResultRepository) {
        this.syncDayRepository = syncDayRepository;
        this.syncResultRepository = syncResultRepository;
    }

    public Set<SyncDayEntity> findAllSyncDayEntitiesByUUIDAndResourceIdAndDate(UUID syncResultUUID, String resourceId, LocalDate date) {
        return syncDayRepository.findBySyncResultSyncResultUUIDAndResourceIdAndDateBetween(syncResultUUID, resourceId, date.with(DayOfWeek.MONDAY), date.with(DayOfWeek.FRIDAY));
    }

    public void saveSyncDays(UUID syncResultUUID, List<SyncDayEntity> syncDayEntities) {
        for (SyncDayEntity syncDayEntity : syncDayEntities) {
            syncDayEntity.setSyncResult(syncResultRepository.findSyncResultEntityBySyncResultUUID(syncResultUUID));
            syncDayRepository.save(syncDayEntity);
        }
    }
}

