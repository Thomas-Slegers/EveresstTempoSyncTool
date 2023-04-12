package be.everesst.everessttemposynctool.controller;

import be.everesst.everessttemposynctool.model.error.ErrorJpaEntity;
import be.everesst.everessttemposynctool.service.ErrorJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/")
public class ErrorJpaController {
    @Autowired
    private ErrorJpaService errorService;

    @GetMapping("/errors")
    public List<ErrorJpaEntity> findAllErrorJpaEntities() {
        return errorService.findAllErrors();
    }

    @GetMapping(value = "/errors/{errorId}")
    public ErrorJpaEntity findAllErrorJpaEntities(@PathVariable Long errorId) {
        return errorService.findErrorById(errorId);
    }
}

