package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.sync.entities.SyncInputEntity;
import com.cegeka.horizon.camis.sync_logger.model.SyncResult;
import com.cegeka.horizon.camis.sync_timesheet.service.SyncTimesheetService;
import com.cegeka.horizon.camis.sync_timesheet.csv.HoursLoggedCsvReader;

import java.util.List;

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

    public void startCamisApi(SyncInputEntity syncInputEntity) {
        WebClient webClient = getWebClient(baseURL, syncInputEntity.clientId(), syncInputEntity.clientSecret());
        List<Employee> employees = new HoursLoggedCsvReader(syncInputEntity.inputStream()).readCsv();
        SyncResult syncResult = syncTimesheetService.sync(webClient, employees);
        syncResultService.createSyncResult(syncInputEntity.syncResultUUID());
        syncRecordService.saveSyncRecords(syncInputEntity.syncResultUUID(), syncRecordsToSyncRecordEntities(syncResult.getSyncRecords()));
        syncDayService.saveSyncDays(syncInputEntity.syncResultUUID(), syncDaysToSyncDaysEntities(syncResult.getSyncDays()));
    }
}

