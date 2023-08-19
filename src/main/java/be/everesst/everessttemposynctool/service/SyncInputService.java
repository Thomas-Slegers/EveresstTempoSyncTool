package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.notification.csv.SlackEmployee;
import be.everesst.everessttemposynctool.model.notification.csv.SlackEmployeesCsvReader;
import be.everesst.everessttemposynctool.model.sync.entities.SyncResultEntry;
import com.cegeka.horizon.camis.domain.EmployeeIdentification;
import com.cegeka.horizon.camis.sync.logger.model.result.SyncResult;
import com.cegeka.horizon.camis.sync_timesheet.csv.HoursLoggedCsvReader;
import com.cegeka.horizon.camis.sync_timesheet.service.SyncTimesheetService;
import com.cegeka.horizon.camis.timesheet.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

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
        Flux<SyncResult> syncResult = syncTimesheetService.sync(webClient, employees, MINIMUM_HOURS_LOGGED_DAILY);
        saveSyncResultWithData(syncInputEntity, syncResult, slackMappingOfEmployees);
    }

    private void saveSyncResultWithData(SyncInputEntity syncInputEntity, Flux<SyncResult> syncResultsFlux, List<SlackEmployee> slackMappingOfEmployees){
        syncResultsFlux.subscribe(syncResult ->
            syncResultService.save(new SyncResultEntry(syncInputEntity.uuid(), syncResult.employee(),
                                    mapSlackEmployee(slackMappingOfEmployees, syncResult.employee()),
                                    syncResult.type(),
                                    syncResult
                                )
            ));
    }


    private String mapSlackEmployee(List<SlackEmployee> slackMappingOfEmployees, EmployeeIdentification employee) {
        return slackMappingOfEmployees.stream()
                .filter(slackEmployee -> slackEmployee.camisId().equals(employee.resourceId().value()))
                .map(SlackEmployee::slackUserName)
                .findFirst().orElse("noSlack");
    }
}

