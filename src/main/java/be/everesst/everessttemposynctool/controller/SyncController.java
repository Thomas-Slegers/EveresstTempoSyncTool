package be.everesst.everessttemposynctool.controller;

import be.everesst.everessttemposynctool.model.sync.entities.SyncRecordEntity;
import be.everesst.everessttemposynctool.model.sync.entities.SyncInputEntity;
import be.everesst.everessttemposynctool.service.SyncInputService;
import be.everesst.everessttemposynctool.service.SyncRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = "/")
public class SyncController {
    @Autowired
    private SyncRecordService syncRecordService;
    @Autowired
    private SyncInputService syncInputService;

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
        File tempFile = new File("/home/thomas/git/Camis/EveresstTempoSyncTool/src/main/java/be/everesst/everessttemposynctool/controller/random.txt");
        file.transferTo(tempFile);
        syncInputService.startCamisApi(new SyncInputEntity(uuid, tempFile, operation, baseUrl, clientId, clientSecret));
        tempFile.delete();
    }
}

