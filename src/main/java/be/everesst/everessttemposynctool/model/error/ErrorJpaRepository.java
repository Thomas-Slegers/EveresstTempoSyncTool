package be.everesst.everessttemposynctool.model.error;

import org.springframework.data.repository.CrudRepository;

public interface ErrorJpaRepository extends CrudRepository<ErrorJpaEntity, Long> {
    ErrorJpaEntity findErrorJpaEntityByUser(String user);
}
