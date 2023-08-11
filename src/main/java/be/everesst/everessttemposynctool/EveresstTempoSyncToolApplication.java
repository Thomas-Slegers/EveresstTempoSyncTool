package be.everesst.everessttemposynctool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"com.cegeka.horizon.camis.*", "be.*"})
@EnableAsync
public class EveresstTempoSyncToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(EveresstTempoSyncToolApplication.class, args);
    }
}
