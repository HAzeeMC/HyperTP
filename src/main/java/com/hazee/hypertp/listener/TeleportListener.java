package com.hazee.hypertp.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerTeleportEvent;
import com.hazee.hypertp.HyperTP;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import org.bukkit.Location;

public class TeleportListener implements Listener {
    private final HyperTP plugin;
    private final Map<UUID, Location> last = new ConcurrentHashMap<>();
    public TeleportListener(HyperTP plugin) { this.plugin = plugin; }
    @EventHandler
    public void onTP(PlayerTeleportEvent e) {
        last.put(e.getPlayer().getUniqueId(), e.getFrom());
    }
    public Location getLast(UUID uuid) { return last.get(uuid); }
}
