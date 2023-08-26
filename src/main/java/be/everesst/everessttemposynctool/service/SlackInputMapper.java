package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.sync.entities.SyncResultEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.cegeka.horizon.camis.timesheet.EuropeanWeek.startOfWeek;

@Service
public class SlackInputMapper {

    @Value("${slack.url.base}")
    private String slackUrlBase;

    public String mapProblems(List<SyncResultEntry> syncResultEntries) {

        String slackHandles = syncResultEntries.stream()
                .filter(syncResultEntry -> syncResultEntry.syncResult().type().isProblematic())
                .map(syncResultEntry -> syncResultEntry.slackHandle() +
                        createLinkMarkUp(
                                syncResultEntry.syncResult().type().name(),
                            composeUrl(syncResultEntry)))
                .distinct()
                .sorted()
                .collect(Collectors.joining("\n\n"));

        return slackHandles;
    }

    private String createLinkMarkUp(String resultType, String url) {
        return "[" +resultType+ "]"+"("+url+")";
    }

    private String  composeUrl(SyncResultEntry contents) {
        return slackUrlBase + contents.syncUUID() + "/" + contents.resourceId().value() + "/" + startOfWeek(contents.contents().workorderInfo().date());
    }

}
