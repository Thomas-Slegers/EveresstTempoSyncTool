package be.everesst.everessttemposynctool.service;

import java.io.InputStream;
import java.util.UUID;

public record SyncInputEntity(UUID uuid, InputStream syncInputStream, InputStream slackEmployeesInputStream, String clientId,
                              String clientSecret) {
}
