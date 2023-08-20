package be.everesst.everessttemposynctool.model.sync.repositories;

import be.everesst.everessttemposynctool.model.sync.entities.SyncResultEntry;
import com.cegeka.horizon.camis.domain.EmployeeIdentification;
import com.cegeka.horizon.camis.domain.ResourceId;
import com.cegeka.horizon.camis.domain.WorkOrder;
import com.cegeka.horizon.camis.sync.logger.model.result.CamisWorkorderInfo;
import com.cegeka.horizon.camis.sync.logger.model.result.HoursInfo;
import com.cegeka.horizon.camis.sync.logger.model.result.SyncResult;
import com.cegeka.horizon.camis.sync.logger.model.result.SyncResultType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SyncResultEntryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SyncResultEntryRepository repository;

    @Test
    public void testJsonSerialization() {
        EmployeeIdentification employee = new EmployeeIdentification(new ResourceId("I099999"), "Ward");
        UUID uuid = UUID.randomUUID();
        SyncResultEntry entry = new SyncResultEntry(uuid,
                "slackHandle", new SyncResult(employee, SyncResultType.SUCCESS, new CamisWorkorderInfo(LocalDate.now(), "message", new WorkOrder("LMAC002.003")), HoursInfo.compare(8, 8)));
        // Persist
        entityManager.persistAndFlush(entry);

        // Retrieve and test
        SyncResultEntry retrievedEntry = repository.findById(entry.id()).orElse(null);
        assertThat(retrievedEntry).isNotNull();
        assertThat(retrievedEntry.resourceId()).isEqualTo(new ResourceId("I099999"));
        assertThat(retrievedEntry.syncResult()).isNotNull();
        assertThat(retrievedEntry.syncResult().type()).isEqualTo(SyncResultType.SUCCESS);
    }
}
