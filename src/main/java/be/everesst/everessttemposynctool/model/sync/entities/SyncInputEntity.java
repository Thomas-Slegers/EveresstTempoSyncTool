package be.everesst.everessttemposynctool.model.sync.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.File;
import java.util.UUID;

@Entity
public class SyncInputEntity {

    @Id
    private UUID syncResultUUID;

    private File file;

    private String operation;

    private String baseUrl;

    private String clientId;

    private String clientSecret;

    protected SyncInputEntity() {
    }

    public SyncInputEntity(UUID syncResultUUID, File file, String operation, String baseUrl, String clientId, String clientSecret) {
        this.syncResultUUID = syncResultUUID;
        this.file = file;
        this.operation = operation;
        this.baseUrl = baseUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public String toString() {
        return "SyncInputJpaEntity{syncResultUUID=" + this.syncResultUUID + '\'' + ", file=" + this.file + '\'' + ", operation='" + this.operation + '\'' + ", baseUrl='" + baseUrl + '\'' + ", clientId='" + clientId + '\'' + ", clientSecret='" + clientSecret + '\'' + '}';
    }

    public UUID getSyncResultUUID() {
        return syncResultUUID;
    }

    public File getFile() {
        return file;
    }

    public String getOperation() {
        return operation;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
