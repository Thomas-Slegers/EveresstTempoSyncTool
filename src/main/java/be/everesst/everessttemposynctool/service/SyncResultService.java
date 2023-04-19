package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.sync.entities.SyncResultEntity;
import be.everesst.everessttemposynctool.model.sync.repositories.SyncResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SyncResultService {
    @Autowired
    SyncResultRepository syncResultRepository;

    public void createSyncResult(UUID syncResultUUID) {
        syncResultRepository.save(new SyncResultEntity(syncResultUUID));
    }
}
