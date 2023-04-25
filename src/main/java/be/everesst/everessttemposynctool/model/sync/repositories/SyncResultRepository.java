package be.everesst.everessttemposynctool.model.sync.repositories;

import be.everesst.everessttemposynctool.model.sync.entities.SyncResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SyncResultRepository extends JpaRepository<SyncResultEntity, Long> {

    SyncResultEntity findSyncResultEntityBySyncResultUUID(UUID syncResultUUID);

}
