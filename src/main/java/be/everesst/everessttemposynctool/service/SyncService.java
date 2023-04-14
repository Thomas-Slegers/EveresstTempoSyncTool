package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.error.SyncEntity;
import be.everesst.everessttemposynctool.model.error.SyncRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyncService {
    @Autowired
    SyncRepository syncRepository;

    public List<SyncEntity> findAllSyncEntities() {
        return syncRepository.findAll();
    }

    public SyncEntity findSyncEntityById(Long id) {
        return syncRepository.findSyncEntityById(id);
    }
}
