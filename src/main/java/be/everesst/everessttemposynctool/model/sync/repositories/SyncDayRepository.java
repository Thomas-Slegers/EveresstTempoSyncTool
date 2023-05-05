package be.everesst.everessttemposynctool.model.sync.repositories;

import be.everesst.everessttemposynctool.model.sync.entities.SyncDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Repository
public interface SyncDayRepository extends JpaRepository<SyncDayEntity, Long> {
    Set<SyncDayEntity> findBySyncResultSyncResultUUIDAndResourceIdAndDateBetween(UUID syncResultUUID, String resourceId, LocalDate startDate, LocalDate endDate);
}