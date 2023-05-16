package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.sync.entities.SyncInputEntity;
import be.everesst.everessttemposynctool.model.sync.entities.SyncResultEntity;
import com.cegeka.horizon.camis.sync_logger.model.SyncResult;
import com.cegeka.horizon.camis.sync_timesheet.csv.SlackEmployeesCsvReader;
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

    SyncResultService syncResultService;

    SyncTimesheetService syncTimesheetService;

    public SyncInputService(SyncTimesheetService syncTimesheetService, SyncResultService syncResultService) {
        this.syncTimesheetService = syncTimesheetService;
        this.syncResultService = syncResultService;
    }

    public void startCamisApi(SyncInputEntity syncInputEntity) {
        WebClient webClient = getWebClient(baseURL, syncInputEntity.clientId(), syncInputEntity.clientSecret());
        List<Employee> employees = new SlackEmployeesCsvReader(syncInputEntity.slackEmployeesInputStream()).readCsv(new HoursLoggedCsvReader(syncInputEntity.syncInputStream()).readCsv());
        SyncResult syncResult = syncTimesheetService.sync(webClient, employees);
        saveSyncResultWithData(syncInputEntity, syncResult);
    }

    private void saveSyncResultWithData(SyncInputEntity syncInputEntity, SyncResult syncResult){
        SyncResultEntity syncResultEntity = new SyncResultEntity(syncInputEntity.syncResultUUID());
        syncResultEntity.setSyncRecords(syncRecordsToSyncRecordEntities(syncResult.getSyncRecords()));
        syncResultEntity.setSyncDays(syncDaysToSyncDaysEntities(syncResult.getSyncDays()));
        syncResultService.save(syncResultEntity);
    }
}

