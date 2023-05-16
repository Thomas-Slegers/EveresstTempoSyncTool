package be.everesst.everessttemposynctool.model.sync.entities;

import java.io.InputStream;
import java.util.UUID;

public record SyncInputEntity(UUID syncResultUUID, InputStream syncInputStream, InputStream slackEmployeesInputStream, String clientId,
                              String clientSecret) {
}
