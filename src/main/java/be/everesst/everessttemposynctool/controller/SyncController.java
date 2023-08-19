package be.everesst.everessttemposynctool.controller;

import be.everesst.everessttemposynctool.model.sync.entities.SyncResultEntry;
import be.everesst.everessttemposynctool.service.LongRunningSyncInputService;
import be.everesst.everessttemposynctool.service.SyncInputEntity;
import be.everesst.everessttemposynctool.service.SyncResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping(value = "/")
public class SyncController {

    private final LongRunningSyncInputService syncInputService;
    private final SyncResultService syncResultService;

    public SyncController(LongRunningSyncInputService syncInputService, SyncResultService syncResultService) {
        this.syncInputService = syncInputService;
        this.syncResultService = syncResultService;
    }

    @GetMapping(value = "/sync/{syncTableUUID}")
    public List<SyncResultEntry> findSyncEntitiesBySyncTableUUID(@PathVariable UUID syncTableUUID) {
        return syncResultService.findSyncResultEntityByUUID(syncTableUUID);
    }

    @GetMapping(value = "/sync/{syncTableUUID}/slack")
    public ResponseEntity<Map<String, String>> findSlackInputBySyncTableUUID(@PathVariable UUID syncTableUUID) {
        String message = syncResultService.findSyncResultEntityByUUIDAsSlackInput(syncTableUUID);
        return ResponseEntity.ok(Collections.singletonMap("message", message));
    }

    @GetMapping(value = "/sync/{syncTableUUID}/{resourceId}/{date}")
    public List<SyncResultEntry> findSyncDayEntitiesBySyncTableUUIDResourceIdDate(@PathVariable UUID syncTableUUID, @PathVariable String resourceId, @PathVariable String date) {
        return syncResultService.findSyncResultEntityByUUID(syncTableUUID, resourceId, LocalDate.parse(date));
    }

    @GetMapping(value = "/sync/{syncTableUUID}/{resourceId}")
    public List<SyncResultEntry> findSyncDayEntitiesBySyncTableUUIDResourceId(@PathVariable UUID syncTableUUID, @PathVariable String resourceId) {
        return syncResultService.findSyncResultEntityByUUID(syncTableUUID, resourceId);
    }

    @PostMapping(value = "/input")
    public void startSync(@RequestParam("uuid") String uuid, @RequestParam("syncFile") MultipartFile syncFile, @RequestParam("slackEmployeesFile") MultipartFile slackEmployeesFile, @RequestParam("clientId") String clientId, @RequestParam("clientSecret") String clientSecret) throws IOException {
        syncInputService.startCamisApi(new SyncInputEntity(UUID.fromString(uuid), syncFile.getInputStream(), slackEmployeesFile.getInputStream(), clientId, clientSecret));
    }
}

