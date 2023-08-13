package be.everesst.everessttemposynctool.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogFileController {
    private final Resource logFileResource;
    @Value("${logging.remote.available}")
    private String loggingRemoteAvailable;

    public LogFileController(ResourceLoader resourceLoader, @Value("${logging.file.name}") String logFile) {
        this.logFileResource = resourceLoader.getResource("file:" + logFile);
    }

    @GetMapping("/logs")
    public Resource getLogFile() {
        if(Boolean.parseBoolean(loggingRemoteAvailable)){
            return this.logFileResource;
        }
        throw new UnsupportedOperationException("Not permitted");
    }
}
