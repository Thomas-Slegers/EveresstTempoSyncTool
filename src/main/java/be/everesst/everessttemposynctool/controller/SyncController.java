package be.everesst.everessttemposynctool.controller;

import be.everesst.everessttemposynctool.model.sync.entities.SyncRecordEntity;
import be.everesst.everessttemposynctool.model.sync.entities.SyncInputEntity;
import be.everesst.everessttemposynctool.service.SyncRecordService;
import be.everesst.everessttemposynctool.service.SyncResultService;
import com.basic.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/")
public class SyncController {
    @Autowired
    private SyncRecordService syncRecordService;
    @Autowired
    private SyncResultService syncResultService;

    @GetMapping(value = "/sync/{syncTableUUID}/{id}")
    public SyncRecordEntity findSyncEntitiesBySyncTableUUIDAndId(@PathVariable UUID syncTableUUID, @PathVariable Long id) {
        Main.main(null);
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
    public void findAllSyncResponses(@RequestBody Map<String, Map<String, String>> payload) {
        UUID syncResultUUID1 = UUID.fromString("c29c801d-f9b3-4c90-b99c-9c5c2d4d4601");
        syncResultService.createSyncResult(syncResultUUID1);
        System.out.println(payload);
        payload.forEach((key, value) -> {
            syncRecordService.saveRecord(syncResultUUID1, new SyncRecordEntity(Long.parseLong(key),
                    value.get("message"),
                    value.get("employeeName"),
                    LocalDate.parse(value.get("startDate")),
                    Double.parseDouble(value.get("hoursLogged")),
                    value.get("workOrder")));
        });
    }
}

