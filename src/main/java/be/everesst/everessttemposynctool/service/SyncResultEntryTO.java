package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.sync.entities.SyncResultEntry;
import com.cegeka.horizon.camis.domain.WorkOrder;
import com.cegeka.horizon.camis.sync.logger.model.result.SyncResult;
import com.cegeka.horizon.camis.sync.logger.model.result.SyncResultType;

import java.time.LocalDate;

import static java.time.DayOfWeek.MONDAY;
import static java.time.temporal.TemporalAdjusters.previous;

public record SyncResultEntryTO(LocalDate startOfWeek,
                                LocalDate syncDate,
                                String employeeName,
                                SyncResultType syncResult,
                                WorkOrder workOrder,
                                String message,
                                Double camisHours,
                                Double inputHours) {

    public static SyncResultEntryTO map(SyncResultEntry entry){
        SyncResult contents = entry.contents();
        return new SyncResultEntryTO(startOfWeek(contents.workorderInfo().date()), contents.workorderInfo().date(),
                                    contents.employee().name(),
                                    contents.type(), contents.workorderInfo().workOrder(), contents.workorderInfo().message(),
                                    contents.hoursInfo().camisHours(), contents.hoursInfo().inputHours());
    }

    private static LocalDate startOfWeek(LocalDate date) {
        if(date.getDayOfWeek() == MONDAY){
            return date;
        }else{
            return date.with(previous(MONDAY));
        }
    }
}
