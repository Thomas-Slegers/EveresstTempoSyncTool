package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.sync.entities.SyncRecordEntity;
import be.everesst.everessttemposynctool.model.sync.repositories.SyncRecordRepository;
import be.everesst.everessttemposynctool.model.sync.repositories.SyncResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void saveSyncRecords(UUID syncResultUUID, List<SyncRecordEntity> syncDaysEntities) {
        syncDaysEntities.forEach(syncRecordEntity -> {
            syncRecordEntity.setSyncResult(syncResultRepository.findSyncResultEntityBySyncResultUUID(syncResultUUID));
            syncRecordRepository.save(syncRecordEntity);
        });
    }


    public String findSlackInputBySyncResultUUID(UUID syncResultUUID){
        StringBuilder result = new StringBuilder();
        for (SyncRecordEntity syncRecordEntity: this.findAllSyncRecordsByUUID(syncResultUUID)) {
            result.append(syncRecordEntity.toString());
        }
        return result.toString();
    }
}

