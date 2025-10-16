package com.hazee.hypertp.model;

import java.util.UUID;
import org.bukkit.Location;

public class TPARequest {
    private final UUID requester;
    private final UUID target;
    private final boolean toTarget; // true = /tpa (requester -> target), false = /tpahere (target -> requester)
    public TPARequest(UUID requester, UUID target, boolean toTarget) {
        this.requester = requester; this.target = target; this.toTarget = toTarget;
    }
    public UUID getRequester() { return requester; }
    public UUID getTarget() { return target; }
    public boolean isToTarget() { return toTarget; }
}
