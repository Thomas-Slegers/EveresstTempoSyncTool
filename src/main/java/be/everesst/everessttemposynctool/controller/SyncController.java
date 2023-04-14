package be.everesst.everessttemposynctool.controller;

import be.everesst.everessttemposynctool.model.error.SyncEntity;
import be.everesst.everessttemposynctool.model.error.SyncInputEntity;
import be.everesst.everessttemposynctool.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/")
public class SyncController {
    @Autowired
    private SyncService errorService;

    @GetMapping("/sync")
    public List<SyncEntity> findAllSyncEntities() {
        return errorService.findAllSyncEntities();
    }

    @GetMapping(value = "/sync/{syncTableUUID}/{id}")
    public SyncEntity findSyncEntitiesBySyncTableUUIDAndId(@PathVariable UUID syncTableUUID, @PathVariable Long id) {
        return errorService.findSyncEntitiesBySyncTableUUIDAndId(syncTableUUID, id);
    }

    @GetMapping(value = "/sync/{syncTableUUID}")
    public List<SyncEntity> findSyncEntitiesBySyncTableUUID(@PathVariable UUID syncTableUUID) {
        return errorService.findAllSyncEntitiesBySyncTableUUID(syncTableUUID);
    }

    @PostMapping(value = "/input")
    public void startSync(@RequestBody SyncInputEntity syncInputEntity) {
        System.out.println(syncInputEntity.getFile().toString());
    }
}

