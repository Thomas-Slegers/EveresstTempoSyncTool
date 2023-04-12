package be.everesst.everessttemposynctool;

import be.everesst.everessttemposynctool.model.error.ErrorJpaEntity;
import be.everesst.everessttemposynctool.model.error.ErrorJpaRepository;
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
    CommandLineRunner init(ErrorJpaRepository errorJpaRepository) {
        return args -> {
            ArrayList<ErrorJpaEntity> errorJpaEntities = new ArrayList<>();
            errorJpaEntities.add(new ErrorJpaEntity(LocalDate.now(), "John", 1, "Less than 40h logged in week 1", "Fill till you have.."));
            errorJpaEntities.add(new ErrorJpaEntity(LocalDate.now(), "Julie", 1, "Less than 40h logged in week 2", "Fill till you have.."));
            errorJpaEntities.add(new ErrorJpaEntity(LocalDate.now(), "Jennifer", 2, "sum(camisHours) > sum(tempoHours) ...", "The tools differ from values..."));
            errorJpaEntities.add(new ErrorJpaEntity(LocalDate.now(), "Roel", 3, " 2023-03-17 with workorder LMAC...", "Workorder ..."));
            Stream.of(errorJpaEntities).forEach(errorJpaRepository::saveAll);
            errorJpaRepository.findAll().forEach(errorJpaEntity -> System.out.println(errorJpaEntity.toString()));
        };
    }
}
