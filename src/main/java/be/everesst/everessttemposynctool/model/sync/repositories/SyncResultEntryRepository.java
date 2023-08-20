package be.everesst.everessttemposynctool.model.sync.repositories;

import be.everesst.everessttemposynctool.model.sync.entities.SyncResultEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SyncResultEntryRepository extends JpaRepository<SyncResultEntry, Long> {

    List<SyncResultEntry> findSyncResultEntryBySyncUUID(UUID syncUUID);

    List<SyncResultEntry> findSyncResultEntryBySyncUUIDAndResourceId(UUID syncUUID, String resourceId);
}
