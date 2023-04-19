package be.everesst.everessttemposynctool.model.sync.entities;

import jakarta.persistence.*;

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
        return "SyncInputJpaEntity{" + "file=" + file + ", operation='" + operation + '\'' + ", baseUrl='" + baseUrl + '\'' + ", clientId='" + clientId + '\'' + ", clientSecret='" + clientSecret + '\'' + '}';
    }

    public File getFile() {
        return file;
    }
}
