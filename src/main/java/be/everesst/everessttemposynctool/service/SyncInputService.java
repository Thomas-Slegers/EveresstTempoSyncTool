package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.notification.csv.SlackEmployee;
import be.everesst.everessttemposynctool.model.notification.csv.SlackEmployeesCsvReader;
import be.everesst.everessttemposynctool.model.sync.entities.SyncInputEntity;
import be.everesst.everessttemposynctool.model.sync.entities.SyncResultEntity;
import com.cegeka.horizon.camis.sync_logger.model.SyncResult;
import com.cegeka.horizon.camis.sync_timesheet.service.SyncTimesheetService;
import com.cegeka.horizon.camis.sync_timesheet.csv.HoursLoggedCsvReader;

import java.util.List;

import com.cegeka.horizon.camis.timesheet.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static be.everesst.everessttemposynctool.service.SyncTransformEntities.syncDaysToSyncDaysEntities;
import static be.everesst.everessttemposynctool.service.SyncTransformEntities.syncRecordsToSyncRecordEntities;
import static com.cegeka.horizon.camis.web_client_factory.WebClientFactory.getWebClient;

@Service
public class SyncInputService {
    @Value("${camis.api}")
    private String camisBaseApiUrl;
    private static final double MINIMUM_HOURS_LOGGED_DAILY = 8.0;

    SyncResultService syncResultService;

    SyncTimesheetService syncTimesheetService;

    public SyncInputService(SyncTimesheetService syncTimesheetService, SyncResultService syncResultService) {
        this.syncTimesheetService = syncTimesheetService;
        this.syncResultService = syncResultService;
    }

    public void startCamisApi(SyncInputEntity syncInputEntity) {
        WebClient webClient = getWebClient(camisBaseApiUrl, syncInputEntity.clientId(), syncInputEntity.clientSecret());
        List<Employee> employees = new HoursLoggedCsvReader(syncInputEntity.syncInputStream()).readCsv();
        List<SlackEmployee> slackMappingOfEmployees = new SlackEmployeesCsvReader(syncInputEntity.slackEmployeesInputStream()).readCsv();
        SyncResult syncResult = syncTimesheetService.sync(webClient, employees, MINIMUM_HOURS_LOGGED_DAILY);
        saveSyncResultWithData(syncInputEntity, syncResult, slackMappingOfEmployees);
    }

    private void saveSyncResultWithData(SyncInputEntity syncInputEntity, SyncResult syncResult, List<SlackEmployee> slackMappingOfEmployees){
        SyncResultEntity syncResultEntity = new SyncResultEntity(syncInputEntity.syncResultUUID());
        syncResultEntity.setSyncRecords(syncRecordsToSyncRecordEntities(syncResult.getSyncRecords(), slackMappingOfEmployees));
        syncResultEntity.setSyncDays(syncDaysToSyncDaysEntities(syncResult.getSyncDays()));
        syncResultService.save(syncResultEntity);
    }
}

