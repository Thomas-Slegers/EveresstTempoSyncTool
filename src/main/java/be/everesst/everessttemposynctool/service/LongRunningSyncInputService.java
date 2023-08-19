package be.everesst.everessttemposynctool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class LongRunningSyncInputService {

    @Autowired SyncInputService syncInputService;

    @Async
    public CompletableFuture<Void> startCamisApi(SyncInputEntity syncInputEntity) {
        syncInputService.startCamisApi(syncInputEntity);
        return CompletableFuture.completedFuture(null);
    }
}
