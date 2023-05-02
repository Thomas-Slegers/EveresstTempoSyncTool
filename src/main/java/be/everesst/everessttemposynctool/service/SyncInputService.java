package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.sync.entities.SyncInputEntity;
import be.everesst.everessttemposynctool.model.sync.entities.SyncRecordEntity;
import com.cegeka.horizon.camis.sync_logger.model.SyncRecord;
import com.cegeka.horizon.camis.sync_timesheet.service.SyncTimesheetService;
import com.cegeka.horizon.camis.sync_timesheet.csv.HoursLoggedCsvReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cegeka.horizon.camis.timesheet.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static com.cegeka.horizon.camis.web_client_factory.WebClientFactory.getWebClient;

@Service
public class SyncInputService {

    SyncRecordService syncRecordService;

    SyncResultService syncResultService;

    SyncTimesheetService syncTimesheetService;

    public SyncInputService(SyncTimesheetService syncTimesheetService, SyncResultService syncResultService, SyncRecordService syncRecordService) {
        this.syncTimesheetService = syncTimesheetService;
        this.syncResultService = syncResultService;
        this.syncRecordService = syncRecordService;
    }

    public void startCamisApi(SyncInputEntity syncInputEntity) throws IOException {
        WebClient webClient = getWebClient(syncInputEntity.getBaseUrl(), syncInputEntity.getClientId(), syncInputEntity.getClientSecret());
        List<Employee> employees = new HoursLoggedCsvReader(new FileInputStream(syncInputEntity.getFile())).readCsv();
        List<SyncRecord> syncRecords = syncTimesheetService.sync(webClient, employees);
        syncRecordEntitiesSaveToSyncResultEntity(syncRecordsToSyncRecordEntities(syncRecords), syncInputEntity.getSyncResultUUID());
    }

    private List<SyncRecordEntity> syncRecordsToSyncRecordEntities(List<SyncRecord> syncRecords) {
        List<SyncRecordEntity> syncRecordEntities = new ArrayList<>();
        for (SyncRecord syncRecord : syncRecords) {
            syncRecordEntities.add(new SyncRecordEntity(syncRecord.getMessage(), syncRecord.getEmployeeName(), syncRecord.getErrorCode(), syncRecord.getStartDate(), syncRecord.getHoursLogged(), syncRecord.getWorkOrder()));
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

