package be.everesst.everessttemposynctool.model.error;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SyncRepository extends CrudRepository<SyncEntity, Long> {
    List<SyncEntity> findAll();

    SyncEntity findSyncEntityById(Long id);
}
