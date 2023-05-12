package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.sync.entities.SyncDayEntity;
import be.everesst.everessttemposynctool.model.sync.entities.SyncRecordEntity;
import com.cegeka.horizon.camis.sync_logger.model.SyncDay;
import com.cegeka.horizon.camis.sync_logger.model.syncrecord.SyncRecord;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SyncTransformEntities {
    public static Set<SyncRecordEntity> syncRecordsToSyncRecordEntities(List<SyncRecord> syncRecords) {
        Set<SyncRecordEntity> syncRecordEntities = new HashSet<>();
        for (SyncRecord syncRecord : syncRecords) {
            syncRecordEntities.add(new SyncRecordEntity(syncRecord.getEmployerData().getResourceId().value(), syncRecord.getRecordData().getMessage(), syncRecord.getEmployerData().getEmployeeName(), syncRecord.getErrorCode(), syncRecord.getRecordData().getDate(), syncRecord.getHoursLogged(), syncRecord.getRecordData().getWorkOrder()));
        }
        return syncRecordEntities;
    }

    public static Set<SyncDayEntity> syncDaysToSyncDaysEntities(List<SyncDay> syncDays) {
        Set<SyncDayEntity> syncDayEntities = new HashSet<>();
        for (SyncDay syncDay : syncDays) {
            syncDayEntities.add(new SyncDayEntity(syncDay.getResourceId().value(), syncDay.getDate(), syncDay.getWorkOrder().value(), syncDay.getHoursLoggedCamis(), syncDay.getHoursLoggedTempo()));
        }
        return syncDayEntities;
    }
}
