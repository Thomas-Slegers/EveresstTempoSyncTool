package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.sync.SyncEntity;
import be.everesst.everessttemposynctool.model.sync.SyncRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SyncService {
    @Autowired
    SyncRepository syncRepository;

    public List<SyncEntity> findAllSyncEntities() {
        return syncRepository.findAll();
    }

    public SyncEntity findSyncEntitiesBySyncTableUUIDAndId(UUID syncTableUUID, Long id) {
        return syncRepository.findSyncEntitiesBySyncTableUUIDAndAndId(syncTableUUID, id);
    }

    public List<SyncEntity> findAllSyncEntitiesBySyncTableUUID(UUID syncTableUUID) {
        return syncRepository.findSyncEntitiesBySyncTableUUID(syncTableUUID);
    }
}
