package be.everesst.everessttemposynctool.model.sync.entities;

import java.io.InputStream;
import java.util.UUID;

public record SyncInputEntity(UUID syncResultUUID, InputStream inputStream, String clientId,
                              String clientSecret) {

    @Override
    public String toString() {
        return "SyncInputJpaEntity{syncResultUUID=" + this.syncResultUUID + '\'' + ", file=" + this.inputStream  + '\'' + ", clientId='" + clientId + '\'' + ", clientSecret='" + clientSecret + '\'' + '}';
    }
}
