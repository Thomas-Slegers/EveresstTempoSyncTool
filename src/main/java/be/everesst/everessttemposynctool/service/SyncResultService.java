package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.sync.entities.SyncResultEntry;
import be.everesst.everessttemposynctool.model.sync.repositories.SyncResultEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SyncResultService {

    SyncResultEntryRepository syncResultEntryRepository;

    public SyncResultService(SyncResultEntryRepository syncResultRepository) {
        this.syncResultEntryRepository = syncResultRepository;
    }

    public void save(SyncResultEntry syncResultEntity) {
        syncResultEntryRepository.save(syncResultEntity);
    }

    public List<SyncResultEntry> findSyncResultEntry(UUID syncUUID){
        return syncResultEntryRepository.findSyncResultEntryBySyncUUID(syncUUID);
    }

    public String findSyncResultEntityByUUIDAsSlackInput(UUID syncUUID) {
        return "";
    }

    public List<SyncResultEntry> findSyncResultEntry(UUID syncUUID, String resourceId) {
        return syncResultEntryRepository.findSyncResultEntryBySyncUUIDAndResourceId(syncUUID, resourceId);
    }
}
