package be.everesst.everessttemposynctool.controller;

import be.everesst.everessttemposynctool.model.sync.entities.SyncRecordEntity;
import be.everesst.everessttemposynctool.model.sync.entities.SyncInputEntity;
import be.everesst.everessttemposynctool.service.SyncRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/")
public class SyncController {
    @Autowired
    private SyncRecordService syncRecordService;

    @GetMapping(value = "/sync/{syncTableUUID}/{id}")
    public SyncRecordEntity findSyncEntitiesBySyncTableUUIDAndId(@PathVariable UUID syncTableUUID, @PathVariable Long id) {
        return syncRecordService.findSyncRecordByUUIDAndSyncRecordId(syncTableUUID, id);
    }

    @GetMapping(value = "/sync/{syncTableUUID}")
    public Set<SyncRecordEntity> findSyncEntitiesBySyncTableUUID(@PathVariable UUID syncTableUUID) {
        return syncRecordService.findAllSyncRecordsByUUID(syncTableUUID);
    }

    @PostMapping(value = "/input")
    public void startSync(@RequestBody SyncInputEntity syncInputEntity) {
        System.out.println(syncInputEntity.getFile().toString());
    }

    @PostMapping("/camis-api/sync-response")
    public void findAllSyncResponses(@RequestBody Map<String, Object> payload) {
        System.out.println(payload);
    }
}

