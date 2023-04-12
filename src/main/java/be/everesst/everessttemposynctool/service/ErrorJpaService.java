package be.everesst.everessttemposynctool.service;

import be.everesst.everessttemposynctool.model.error.ErrorJpaEntity;
import be.everesst.everessttemposynctool.model.error.ErrorJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorJpaService {
    @Autowired
    ErrorJpaRepository errorJpaRepository;

    public List<ErrorJpaEntity> findAllErrors() {
        return errorJpaRepository.findAll();
    }

    public ErrorJpaEntity findErrorById(Long id) {
        return errorJpaRepository.findErrorJpaEntityById(id);
    }
}
