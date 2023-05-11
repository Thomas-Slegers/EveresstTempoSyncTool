package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.sync.entities.SyncDayEntity;
import be.everesst.everessttemposynctool.model.sync.entities.SyncRecordEntity;
import com.cegeka.horizon.camis.sync_logger.model.SyncDay;
import com.cegeka.horizon.camis.sync_logger.model.syncrecord.SyncRecord;

import java.util.ArrayList;
import java.util.List;

public class SyncTransformEntities {
    public static List<SyncRecordEntity> syncRecordsToSyncRecordEntities(List<SyncRecord> syncRecords) {
        List<SyncRecordEntity> syncRecordEntities = new ArrayList<>();
        for (SyncRecord syncRecord : syncRecords) {
            syncRecordEntities.add(new SyncRecordEntity(syncRecord.getEmployerData().getResourceId().value(), syncRecord.getRecordData().getMessage(), syncRecord.getEmployerData().getEmployeeName(), syncRecord.getErrorCode(), syncRecord.getRecordData().getDate(), syncRecord.getHoursLogged(), syncRecord.getRecordData().getWorkOrder()));
        }
        return syncRecordEntities;
    }

    public static List<SyncDayEntity> syncDaysToSyncDaysEntities(List<SyncDay> syncDays) {
        List<SyncDayEntity> syncDayEntities = new ArrayList<>();
        for (SyncDay syncDay : syncDays) {
            syncDayEntities.add(new SyncDayEntity(syncDay.getResourceId().value(), syncDay.getDate(), syncDay.getWorkOrder().value(), syncDay.getHoursLoggedCamis(), syncDay.getHoursLoggedTempo()));
        }
        return syncDayEntities;
    }
}
