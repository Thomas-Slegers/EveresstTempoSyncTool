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
    private final SyncRecordService syncRecordService;
    private final SyncInputService syncInputService;

    public SyncController(SyncRecordService syncRecordService, SyncInputService syncInputService) {
        this.syncRecordService = syncRecordService;
        this.syncInputService = syncInputService;
    }

    @GetMapping(value = "/sync/{syncTableUUID}/{id}")
    public SyncRecordEntity findSyncEntitiesBySyncTableUUIDAndId(@PathVariable UUID syncTableUUID, @PathVariable Long id) {
        return syncRecordService.findSyncRecordByUUIDAndSyncRecordId(syncTableUUID, id);
    }

    @GetMapping(value = "/sync/{syncTableUUID}")
    public Set<SyncRecordEntity> findSyncEntitiesBySyncTableUUID(@PathVariable UUID syncTableUUID) {
        return syncRecordService.findAllSyncRecordsByUUID(syncTableUUID);
    }

    @PostMapping(value = "/input")
    public void startSync(@RequestParam("syncResultUUID") String uuid, @RequestParam("file") MultipartFile file, @RequestParam("operation") String operation, @RequestParam("baseUrl") String baseUrl, @RequestParam("clientId") String clientId, @RequestParam("clientSecret") String clientSecret) throws IOException {
        File tempFile = new File(TEMP_FILE);
        file.transferTo(tempFile);
        syncInputService.startCamisApi(new SyncInputEntity(UUID.fromString(uuid), tempFile, operation, baseUrl, clientId, clientSecret));
        tempFile.delete();
    }
}

