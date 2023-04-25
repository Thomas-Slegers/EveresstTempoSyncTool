package be.everesst.everessttemposynctool.model.sync.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.File;

@Entity
public class SyncInputEntity {
    @Id
    private File file;

    private String operation;

    private String baseUrl;

    private String clientId;

    private String clientSecret;

    protected SyncInputEntity() {
    }

    public SyncInputEntity(File file, String operation, String baseUrl, String clientId, String clientSecret) {
        this.file = file;
        this.operation = operation;
        this.baseUrl = baseUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public String toString() {
        return "SyncInputJpaEntity{" + "file=" + this.file + ", operation='" + this.operation + '\'' + ", baseUrl='" + baseUrl + '\'' + ", clientId='" + clientId + '\'' + ", clientSecret='" + clientSecret + '\'' + '}';
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
