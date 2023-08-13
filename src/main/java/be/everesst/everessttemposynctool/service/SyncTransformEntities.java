package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.notification.csv.SlackEmployee;
import be.everesst.everessttemposynctool.model.sync.entities.SyncDayEntity;
import be.everesst.everessttemposynctool.model.sync.entities.SyncRecordEntity;
import com.cegeka.horizon.camis.domain.ResourceId;
import com.cegeka.horizon.camis.sync_logger.model.SyncDay;
import com.cegeka.horizon.camis.sync_logger.model.syncrecord.SyncRecord;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SyncTransformEntities {
    public static Set<SyncRecordEntity> syncRecordsToSyncRecordEntities(List<SyncRecord> syncRecords, List<SlackEmployee> slackMappingOfEmployees) {
        Set<SyncRecordEntity> syncRecordEntities = new HashSet<>();
        for (SyncRecord syncRecord : syncRecords) {
            syncRecordEntities.add(new SyncRecordEntity(syncRecord.employeeIdentification().resourceId().value(), syncRecord.recordData().getMessage(), syncRecord.employeeIdentification().name(), mapSlackEmployee(slackMappingOfEmployees, syncRecord.employeeIdentification().resourceId()), syncRecord.errorCode(), syncRecord.recordData().getDate(), syncRecord.hoursLogged(), syncRecord.recordData().getWorkOrder()));
        }
        return syncRecordEntities;
    }

    private static String mapSlackEmployee(List<SlackEmployee> slackMappingOfEmployees, ResourceId resourceId) {
        return slackMappingOfEmployees.stream()
                .filter(slackEmployee -> slackEmployee.camisId().equals(resourceId.value()))
                .map(SlackEmployee::slackUserName)
                .findFirst().orElse("noSlack");
    }

    public static Set<SyncDayEntity> syncDaysToSyncDaysEntities(List<SyncDay> syncDays) {
        Set<SyncDayEntity> syncDayEntities = new HashSet<>();
        for (SyncDay syncDay : syncDays) {
            syncDayEntities.add(new SyncDayEntity(syncDay.getResourceId().value(), syncDay.getDate(), syncDay.getWorkOrder().value(), syncDay.getHoursLoggedCamis(), syncDay.getHoursLoggedTempo()));
        }
        return syncDayEntities;
    }
}
