package be.everesst.everessttemposynctool.controller;

import be.everesst.everessttemposynctool.model.sync.entities.SyncDayEntity;
import be.everesst.everessttemposynctool.model.sync.entities.SyncRecordEntity;
import be.everesst.everessttemposynctool.model.sync.entities.SyncInputEntity;
import be.everesst.everessttemposynctool.service.SyncInputService;

import be.everesst.everessttemposynctool.service.SyncResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = "/")
public class SyncController {

    private final SyncInputService syncInputService;
    private final SyncResultService syncResultService;

    public SyncController(SyncInputService syncInputService, SyncResultService syncResultService) {
        this.syncInputService = syncInputService;
        this.syncResultService = syncResultService;
    }

    @GetMapping(value = "/sync/{syncTableUUID}")
    public Set<SyncRecordEntity> findSyncEntitiesBySyncTableUUID(@PathVariable UUID syncTableUUID) {
        return syncResultService.getSyncResultEntityByUUID(syncTableUUID).getSyncRecords();
    }

    @GetMapping(value = "/sync/{syncTableUUID}/slack")
    public ResponseEntity<Map<String, String>> findSlackInputBySyncTableUUID(@PathVariable UUID syncTableUUID) {
        String message = syncResultService.getSyncResultEntityByUUID(syncTableUUID).getSlackInput();
        return ResponseEntity.ok(Collections.singletonMap("message", message));
    }

    @GetMapping(value = "/sync/{syncTableUUID}/{resourceId}/{date}")
    public Set<SyncDayEntity> findSyncDayEntitiesBySyncTableUUID(@PathVariable UUID syncTableUUID, @PathVariable String resourceId, @PathVariable String date) {
        return syncResultService.getSyncResultEntityByUUID(syncTableUUID).findAllSyncDayEntitiesByUUIDAndResourceIdAndDate(resourceId, LocalDate.parse(date));
    }

    @PostMapping(value = "/input")
    public void startSync(@RequestParam("syncResultUUID") String uuid, @RequestParam("file") MultipartFile file, @RequestParam("clientId") String clientId, @RequestParam("clientSecret") String clientSecret) throws IOException {
        syncInputService.startCamisApi(new SyncInputEntity(UUID.fromString(uuid), file.getInputStream(), clientId, clientSecret));
    }
}

