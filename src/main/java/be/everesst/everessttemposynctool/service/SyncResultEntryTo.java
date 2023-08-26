package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.sync.entities.SyncResultEntry;
import com.cegeka.horizon.camis.domain.WorkOrder;
import com.cegeka.horizon.camis.sync.logger.model.result.SyncResult;
import com.cegeka.horizon.camis.sync.logger.model.result.SyncResultType;
import com.cegeka.horizon.camis.timesheet.EuropeanWeek;

import java.time.LocalDate;

public record SyncResultEntryTo(LocalDate startOfWeek,
                                LocalDate syncDate,
                                com.cegeka.horizon.camis.domain.ResourceId resourceId, String employeeName,
                                SyncResultType syncResult,
                                WorkOrder workOrder,
                                String message,
                                Double camisHours,
                                Double inputHours) {

    public static SyncResultEntryTo map(SyncResultEntry entry){
        SyncResult contents = entry.contents();
        return new SyncResultEntryTo(EuropeanWeek.startOfWeek(contents.workorderInfo().date()), contents.workorderInfo().date(),
                                    contents.employee().resourceId(),
                                    contents.employee().name(),
                                    contents.type(), contents.workorderInfo().workOrder(), contents.workorderInfo().message(),
                                    contents.hoursInfo().camisHours(), contents.hoursInfo().inputHours());
    }


}
