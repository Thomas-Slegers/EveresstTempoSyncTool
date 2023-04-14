package be.everesst.everessttemposynctool;

import be.everesst.everessttemposynctool.model.error.SyncEntity;
import be.everesst.everessttemposynctool.model.error.SyncRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;

@SpringBootApplication
public class EveresstTempoSyncToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(EveresstTempoSyncToolApplication.class, args);
    }

    @Bean
    CommandLineRunner init(SyncRepository syncRepository) {
        return args -> {
            ArrayList<SyncEntity> syncEntities = new ArrayList<>();
            syncEntities.add(new SyncEntity("Testing", LocalDate.now(), "9005026", "John", "AD", "LMAC000.003", 1, 1, "Less than 40h logged in week 1", "Fill till you have.."));
            syncEntities.add(new SyncEntity("Ravi", LocalDate.now(), "I099716", "Julie", "AD", "LMAC000.003", 0.5, 1, "Less than 40h logged in week 2", "Fill till you have.."));
            syncEntities.add(new SyncEntity("Ravi", LocalDate.now(), "9005026", "Jennifer", "AD", "LMAC000.003", 5, 2, "sum(camisHours) > sum(tempoHours) ...", "The tools differ from values..."));
            syncEntities.add(new SyncEntity("Sherpa", LocalDate.now(), "9005026", "Roel", "AD", " 2023-03-17 with workorder LMAC...", 6, 3, "", "Workorder ..."));
            Stream.of(syncEntities).forEach(syncRepository::saveAll);
            syncRepository.findAll().forEach(syncEntity -> System.out.println(syncEntity.toString()));
        };
    }
}
