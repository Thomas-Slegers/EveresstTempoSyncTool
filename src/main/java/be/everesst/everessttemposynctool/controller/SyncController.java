package be.everesst.everessttemposynctool.controller;

import be.everesst.everessttemposynctool.model.sync.SyncEntity;
import be.everesst.everessttemposynctool.model.sync.SyncInputEntity;
import be.everesst.everessttemposynctool.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/")
public class SyncController {
    @Autowired
    private SyncService syncService;

    @GetMapping("/sync")
    public List<SyncEntity> findAllSyncEntities() {
        syncService.findAllSyncEntities().forEach(System.out::println);
        return syncService.findAllSyncEntities();
    }

    @GetMapping(value = "/sync/{syncTableUUID}/{id}")
    public SyncEntity findSyncEntitiesBySyncTableUUIDAndId(@PathVariable UUID syncTableUUID, @PathVariable Long id) {
        return syncService.findSyncEntitiesBySyncTableUUIDAndId(syncTableUUID, id);
    }

    @GetMapping(value = "/sync/{syncTableUUID}")
    public List<SyncEntity> findSyncEntitiesBySyncTableUUID(@PathVariable UUID syncTableUUID) {
        return syncService.findAllSyncEntitiesBySyncTableUUID(syncTableUUID);
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

