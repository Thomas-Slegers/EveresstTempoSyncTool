package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.sync.entities.SyncRecordEntity;
import be.everesst.everessttemposynctool.model.sync.repositories.SyncRecordRepository;
import be.everesst.everessttemposynctool.model.sync.repositories.SyncResultRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class SyncRecordService {

    SyncRecordRepository syncRecordRepository;

    SyncResultRepository syncResultRepository;

    public SyncRecordService(SyncRecordRepository syncRecordRepository, SyncResultRepository syncResultRepository) {
        this.syncRecordRepository = syncRecordRepository;
        this.syncResultRepository = syncResultRepository;
    }

    public Set<SyncRecordEntity> findAllSyncRecordsByUUID(UUID syncResultUUID) {
        return syncRecordRepository.findBySyncResultSyncResultUUID(syncResultUUID);
    }

    public SyncRecordEntity findSyncRecordByUUIDAndSyncRecordId(UUID syncResultUUID, long id) {
        return syncRecordRepository.findBySyncResultSyncResultUUIDAndId(syncResultUUID, id);
    }

    public void saveRecord(UUID syncResultUUID, SyncRecordEntity syncRecord) {
        syncRecord.setSyncResult(syncResultRepository.findSyncResultEntityBySyncResultUUID(syncResultUUID));
        syncRecordRepository.save(syncRecord);
    }
}

