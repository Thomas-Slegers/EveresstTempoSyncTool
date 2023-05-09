package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.sync.entities.SyncInputEntity;
import be.everesst.everessttemposynctool.model.sync.entities.SyncRecordEntity;
import com.cegeka.horizon.camis.sync_logger.model.SyncReturn;
import com.cegeka.horizon.camis.sync_timesheet.service.SyncTimesheetService;
import com.cegeka.horizon.camis.sync_timesheet.csv.HoursLoggedCsvReader;

import java.io.*;
import java.util.List;
import java.util.UUID;

import com.cegeka.horizon.camis.timesheet.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static be.everesst.everessttemposynctool.service.SyncTransformEntities.syncDaysToSyncDaysEntities;
import static be.everesst.everessttemposynctool.service.SyncTransformEntities.syncRecordsToSyncRecordEntities;
import static com.cegeka.horizon.camis.web_client_factory.WebClientFactory.getWebClient;

@Service
public class SyncInputService {

    private static final String baseURL = "https://gw.api.cegeka.com/1/erp/camis/v1/prd/";

    SyncDayService syncDayService;

    SyncRecordService syncRecordService;

    SyncResultService syncResultService;

    SyncTimesheetService syncTimesheetService;

    public SyncInputService(SyncDayService syncDayService, SyncTimesheetService syncTimesheetService, SyncResultService syncResultService, SyncRecordService syncRecordService) {
        this.syncDayService = syncDayService;
        this.syncTimesheetService = syncTimesheetService;
        this.syncResultService = syncResultService;
        this.syncRecordService = syncRecordService;

    }

    public void startCamisApi(SyncInputEntity syncInputEntity) throws IOException {
        WebClient webClient = getWebClient(baseURL, syncInputEntity.getClientId(), syncInputEntity.getClientSecret());
        List<Employee> employees = new HoursLoggedCsvReader(new FileInputStream(syncInputEntity.getFile())).readCsv();
        SyncReturn syncReturn = syncTimesheetService.sync(webClient, employees);
        syncRecordEntitiesSaveToSyncResultEntity(syncInputEntity.getSyncResultUUID(), syncRecordsToSyncRecordEntities(syncReturn.getSyncRecords()));
        syncDayService.saveSyncDays(syncInputEntity.getSyncResultUUID(), syncDaysToSyncDaysEntities(syncReturn.getSyncDays()));
    }

    private void syncRecordEntitiesSaveToSyncResultEntity(UUID syncResultUUID, List<SyncRecordEntity> syncRecordEntities) {
        syncResultService.createSyncResult(syncResultUUID);
        for (SyncRecordEntity syncRecordEntity : syncRecordEntities) {
            syncRecordService.saveRecord(syncResultUUID, syncRecordEntity);
        }
    }
}

