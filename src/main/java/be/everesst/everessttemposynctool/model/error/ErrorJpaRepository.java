package be.everesst.everessttemposynctool.model.error;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ErrorJpaRepository extends CrudRepository<ErrorJpaEntity, Long> {
    List<ErrorJpaEntity> findAll();

    ErrorJpaEntity findErrorJpaEntityById(Long id);
}
