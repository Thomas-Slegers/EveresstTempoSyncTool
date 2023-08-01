package be.everesst.everessttemposynctool.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogFileController {
    private Resource logFileResource;

    public LogFileController(ResourceLoader resourceLoader, @Value("${logging.file.name}") String logFile) {
        this.logFileResource = resourceLoader.getResource("file:" + logFile);
    }

    @GetMapping("/logs")
    public Resource getLogFile() {
        return this.logFileResource;
    }
}
