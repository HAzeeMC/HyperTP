package com.hazee.hypertp.manager;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CooldownManager {
    private final Map<String, Long> cd = new ConcurrentHashMap<>();

    public void setCooldown(UUID player, String key, long seconds) {
        cd.put(player.toString() + ":" + key, System.currentTimeMillis() + seconds * 1000);
    }
    public boolean hasCooldown(UUID player, String key) {
        Long t = cd.get(player.toString() + ":" + key);
        return t != null && t > System.currentTimeMillis();
    }
    public long getRemaining(UUID player, String key) {
        Long t = cd.get(player.toString() + ":" + key);
        if (t==null) return 0;
        long rem = (t - System.currentTimeMillis())/1000;
        return Math.max(0, rem);
    }
}
