package com.hazee.hypertp.manager;

import com.hazee.hypertp.HyperTP;
import com.hazee.hypertp.model.TPARequest;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TPAManager {
    private final HyperTP plugin;
    private final Map<UUID, TPARequest> requests = new ConcurrentHashMap<>();
    public TPAManager(HyperTP plugin) { this.plugin = plugin; }
    public void createRequest(TPARequest r) { requests.put(r.getTarget(), r); }
    public TPARequest getRequest(UUID target) { return requests.get(target); }
    public void removeRequest(UUID target) { requests.remove(target); }
}
