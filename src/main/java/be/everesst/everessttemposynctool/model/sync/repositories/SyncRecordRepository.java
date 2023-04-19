package be.everesst.everessttemposynctool.model.sync.repositories;

import be.everesst.everessttemposynctool.model.sync.entities.SyncRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface SyncRecordRepository extends JpaRepository<SyncRecordEntity, Long> {

    Set<SyncRecordEntity> findBySyncResultSyncResultUUID(UUID syncResultUUID);

    SyncRecordEntity findBySyncResultSyncResultUUIDAndId(UUID syncResultUUID, long id);
}
