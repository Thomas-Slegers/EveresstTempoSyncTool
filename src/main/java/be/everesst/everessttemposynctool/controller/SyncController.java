package be.everesst.everessttemposynctool.controller;

import be.everesst.everessttemposynctool.model.error.SyncEntity;
import be.everesst.everessttemposynctool.model.error.SyncInputEntity;
import be.everesst.everessttemposynctool.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/sync/{syncId}")
    public SyncEntity findSyncEntityById(@PathVariable Long syncId) {
        return errorService.findSyncEntityById(syncId);
    }

    @PostMapping(value = "/input")
    public void startSync(@RequestBody SyncInputEntity syncInputEntity) {
        System.out.println(syncInputEntity.getFile().toString());
    }
}

