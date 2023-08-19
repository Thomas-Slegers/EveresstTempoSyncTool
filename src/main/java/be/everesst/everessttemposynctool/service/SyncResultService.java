package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.sync.entities.SyncResultEntry;
import be.everesst.everessttemposynctool.model.sync.repositories.SyncResultEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<SyncResultEntry> findSyncResultEntityByUUID(UUID syncUUID){
        return syncResultEntryRepository.findSyncResultEntryByUuid(syncUUID);
    }

    public String findSyncResultEntityByUUIDAsSlackInput(UUID syncTableUUID) {
        return "";
    }

    public List<SyncResultEntry> findSyncResultEntityByUUID(UUID syncUUID, String resourceId, LocalDate parse) {
        return List.of();
    }

    public List<SyncResultEntry> findSyncResultEntityByUUID(UUID syncTableUUID, String resourceId) {
        return List.of();
    }
}
