package be.everesst.everessttemposynctool.model.sync;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface SyncRepository extends CrudRepository<SyncEntity, Long> {
    List<SyncEntity> findAll();

    SyncEntity findSyncEntitiesBySyncTableUUIDAndAndId(UUID syncTableUUID, Long id);

    List<SyncEntity> findSyncEntitiesBySyncTableUUID(UUID syncTableUUID);
}
