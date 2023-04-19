package be.everesst.everessttemposynctool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EveresstTempoSyncToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(EveresstTempoSyncToolApplication.class, args);
    }

//    @Bean
//    CommandLineRunner init(SyncResultService syncResultService, SyncRecordService syncRecordService) {
//        return args -> {
//            UUID syncResultUUID1 = UUID.fromString("c29c801d-f9b3-4c90-b99c-9c5c2d4d4601");
//            syncResultService.createSyncResult(syncResultUUID1);
//            syncRecordService.saveRecord(syncResultUUID1, new SyncRecordEntity(1, "Less than 40h logged in week 2", "John", LocalDate.now(), 40, "LMAC000.001"));
//            syncRecordService.saveRecord(syncResultUUID1, new SyncRecordEntity(2, "Less than 40h logged in week 2", "John", LocalDate.now(), 40, "LMAC000.002"));
//            syncRecordService.saveRecord(syncResultUUID1, new SyncRecordEntity(3, "Less than 40h logged in week 2", "John", LocalDate.now(), 40, "LMAC000.003"));
//            syncRecordService.saveRecord(syncResultUUID1, new SyncRecordEntity(4, "Less than 40h logged in week 2", "John", LocalDate.now(), 40, "LMAC000.004"));
//            syncRecordService.findAllSyncRecordsByUUID(syncResultUUID1).forEach(syncRecord -> System.out.println(syncRecord.toString()));
//        };
//    }
}
