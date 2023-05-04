package be.everesst.everessttemposynctool.controller;

import be.everesst.everessttemposynctool.model.sync.entities.SyncRecordEntity;
import be.everesst.everessttemposynctool.model.sync.entities.SyncInputEntity;
import be.everesst.everessttemposynctool.service.SyncInputService;
import be.everesst.everessttemposynctool.service.SyncRecordService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = "/")
public class SyncController {
    private static final String TEMP_FILE = "/home/thomas/git/Camis/EveresstTempoSyncTool/src/main/java/be/everesst/everessttemposynctool/controller/random.txt";
    private final SyncInputService syncInputService;
    private final SyncRecordService syncRecordService;

    public SyncController(SyncInputService syncInputService, SyncRecordService syncRecordService) {
        this.syncInputService = syncInputService;
        this.syncRecordService = syncRecordService;
    }

    @GetMapping(value = "/sync/{syncTableUUID}/{id}")
    public SyncRecordEntity findSyncEntitiesBySyncTableUUIDAndId(@PathVariable UUID syncTableUUID, @PathVariable Long id) {
        return syncRecordService.findSyncRecordByUUIDAndSyncRecordId(syncTableUUID, id);
    }

    @GetMapping(value = "/sync/{syncTableUUID}")
    public Set<SyncRecordEntity> findSyncEntitiesBySyncTableUUID(@PathVariable UUID syncTableUUID) {
        return syncRecordService.findAllSyncRecordsByUUID(syncTableUUID);
    }

    @GetMapping(value = "/sync/{syncTableUUID}/slack")
    public String findSlackInputBySyncTableUUID(@PathVariable UUID syncTableUUID) {
        return "{ \"message\": \"" + syncRecordService.findSlackInputBySyncResultUUID(syncTableUUID) + "\" }";
    }

    @PostMapping(value = "/input")
    public void startSync(@RequestParam("syncResultUUID") String uuid, @RequestParam("file") MultipartFile file, @RequestParam("operation") String operation, @RequestParam("clientId") String clientId, @RequestParam("clientSecret") String clientSecret) throws IOException {
        File tempFile = new File(TEMP_FILE);
        file.transferTo(tempFile);
        syncInputService.startCamisApi(new SyncInputEntity(UUID.fromString(uuid), tempFile, operation, clientId, clientSecret));
        tempFile.delete();
    }
}

