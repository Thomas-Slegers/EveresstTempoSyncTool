package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.sync.entities.SyncInputEntity;
import be.everesst.everessttemposynctool.model.sync.entities.SyncRecordEntity;
import com.cegeka.horizon.camis.sync_logger.model.HoursMinimumSyncError;
import com.cegeka.horizon.camis.sync_logger.model.SyncRecord;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// TODO CamisApi import package

@Service
public class SyncInputService {

    @Autowired
    SyncRecordService syncRecordService;

    @Autowired
    SyncResultService syncResultService;

    public void startCamisApi(SyncInputEntity syncInputEntity) {
        List<SyncRecord> syncRecords = new ArrayList<>();

//      Test Code
        SyncRecord syncRecord1 = new HoursMinimumSyncError("Not syncing inputEmployee Bryon Ward timesheet starting at 2023-05-01 due to less than 40.0 hours logged", "Ward Bryon", LocalDate.of(2023, 05, 01), 40.0);
        SyncRecord syncRecord2 = new HoursMinimumSyncError("Not syncing inputEmployee Thomas Slegers timesheet starting at 2023-05-15 due to less than 40.0 hours logged", "Thomas Slegers", LocalDate.of(2023, 05, 15), 40.0);
        syncRecords.add(syncRecord1);
        syncRecords.add(syncRecord2);

//      CAMIS IMPLEMENTATIE
//        List<SyncRecord> syncRecords = Sync_timesheet_application.main(syncInputEntity.getFile(),
//            syncInputEntity.getOperation(),
//            syncInputEntity.getBaseUrl(),
//            syncInputEntity.getClientId(),
//            syncInputEntity.getClientSecret())

        List<SyncRecordEntity> syncRecordEntities = syncRecordsToSyncRecordEntities(syncRecords);
        syncRecordEntitiesSaveToSyncResultEntity(syncRecordEntities, UUID.fromString(syncInputEntity.getSyncResultUUID()));
    }

    private List<SyncRecordEntity> syncRecordsToSyncRecordEntities(List<SyncRecord> syncRecords) {
        List<SyncRecordEntity> syncRecordEntities = new ArrayList<>();
        for (int index = 0; index < syncRecords.size(); index++) {
            SyncRecord currentSyncRecord = syncRecords.get(index);
            syncRecordEntities.add(new SyncRecordEntity(index,
                    currentSyncRecord.getMessage(),
                    currentSyncRecord.getEmployeeName(),
                    currentSyncRecord.getStartDate(),
                    currentSyncRecord.getHoursLogged(),
                    currentSyncRecord.getWorkOrder()));
        }
        return syncRecordEntities;
    }

    private void syncRecordEntitiesSaveToSyncResultEntity(List<SyncRecordEntity> syncRecordEntities, UUID syncResultUUID) {
        syncResultService.createSyncResult(syncResultUUID);
        for (SyncRecordEntity syncRecordEntity : syncRecordEntities) {
            syncRecordService.saveRecord(syncResultUUID, syncRecordEntity);
        }
    }
}

