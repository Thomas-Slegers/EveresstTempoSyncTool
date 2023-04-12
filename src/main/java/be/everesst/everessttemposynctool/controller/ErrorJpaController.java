package be.everesst.everessttemposynctool.controller;

import be.everesst.everessttemposynctool.model.error.ErrorJpaEntity;
import be.everesst.everessttemposynctool.service.ErrorJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/errors")
public class ErrorJpaController {
    @Autowired
    private ErrorJpaService errorService;

    @GetMapping
    public List<ErrorJpaEntity> findAllErrorJpaEntities() {
        return errorService.findAllErrors();
    }

    @GetMapping(value = "/{errorId}")
    public ErrorJpaEntity findAllErrorJpaEntities(@PathVariable Long errorId) {
        return errorService.findErrorById(errorId);
    }
}

