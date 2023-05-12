package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.sync.entities.SyncResultEntity;
import be.everesst.everessttemposynctool.model.sync.repositories.SyncResultRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class SyncResultService {

    SyncResultRepository syncResultRepository;

    public SyncResultService(SyncResultRepository syncResultRepository) {
        this.syncResultRepository = syncResultRepository;
    }

    public void save(SyncResultEntity syncResultEntity) {
        syncResultRepository.save(syncResultEntity);
    }

    public SyncResultEntity getSyncResultEntityByUUID(UUID syncResultUUID){
        return syncResultRepository.findSyncResultEntityBySyncResultUUID(syncResultUUID);
    }
}
