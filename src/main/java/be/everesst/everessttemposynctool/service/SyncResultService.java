package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.sync.entities.SyncResultEntry;
import be.everesst.everessttemposynctool.model.sync.repositories.SyncResultEntryRepository;
import com.cegeka.horizon.camis.domain.ResourceId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SyncResultService {


    private SlackInputMapper slackInputMapper;
    private SyncResultEntryRepository syncResultEntryRepository;

    public SyncResultService(SyncResultEntryRepository syncResultRepository, SlackInputMapper slackInputMapper) {
        this.syncResultEntryRepository = syncResultRepository;
        this.slackInputMapper = slackInputMapper;
    }

    public void save(SyncResultEntry syncResultEntity) {
        syncResultEntryRepository.save(syncResultEntity);
    }

    public List<SyncResultEntry> findSyncResultEntry(UUID syncUUID){
        return syncResultEntryRepository.findSyncResultEntryBySyncUUID(syncUUID);
    }

    public String findSyncResultEntityByUUIDAsSlackInput(UUID syncUUID) {
        return slackInputMapper.mapProblems(syncResultEntryRepository.findSyncResultEntryBySyncUUID(syncUUID));
    }

    public List<SyncResultEntry> findSyncResultEntry(UUID syncUUID, String resourceId) {
        return syncResultEntryRepository.findSyncResultEntryBySyncUUIDAndResourceId(syncUUID, new ResourceId(resourceId));
    }
}
