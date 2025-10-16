package com.hazee.hypertp.manager;

import com.hazee.hypertp.HyperTP;
import com.hazee.hypertp.model.Home;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import java.io.File;
import java.util.*;

public class HomeManager {
    private final HyperTP plugin;
    private final File homesFile;
    private FileConfiguration homesCfg;
    private final Map<UUID, List<Home>> cache = new HashMap<>();

    public HomeManager(HyperTP plugin) {
        this.plugin = plugin;
        this.homesFile = new File(plugin.getDataFolder(), "homes.yml");
        if (!homesFile.exists()) plugin.saveResource("homes.yml", false);
        homesCfg = YamlConfiguration.loadConfiguration(homesFile);
        loadAll();
    }

    public void loadAll() {
        cache.clear();
        for (String uuidStr : homesCfg.getKeys(false)) {
            try {
                UUID owner = UUID.fromString(uuidStr);
                for (String key : homesCfg.getConfigurationSection(uuidStr).getKeys(false)) {
                    String path = uuidStr + "." + key;
                    String world = homesCfg.getString(path + ".world");
                    double x = homesCfg.getDouble(path + ".x");
                    double y = homesCfg.getDouble(path + ".y");
                    double z = homesCfg.getDouble(path + ".z");
                    float yaw = (float) homesCfg.getDouble(path + ".yaw");
                    float pitch = (float) homesCfg.getDouble(path + ".pitch");
                    Location loc = new Location(Bukkit.getWorld(world), x,y,z,yaw,pitch);
                    Home home = new Home(owner, key, loc);
                    cache.computeIfAbsent(owner, k->new ArrayList<>()).add(home);
                }
            } catch (Exception ignored) {}
        }
    }

    public List<Home> getHomes(UUID owner) {
        return cache.getOrDefault(owner, Collections.emptyList());
    }

    public Home getHome(UUID owner, String name) {
        return getHomes(owner).stream().filter(h->h.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public void saveHome(Home home) {
        String base = home.getOwner().toString() + "." + home.getName();
        homesCfg.set(base + ".world", home.getLocation().getWorld().getName());
        homesCfg.set(base + ".x", home.getLocation().getX());
        homesCfg.set(base + ".y", home.getLocation().getY());
        homesCfg.set(base + ".z", home.getLocation().getZ());
        homesCfg.set(base + ".yaw", home.getLocation().getYaw());
        homesCfg.set(base + ".pitch", home.getLocation().getPitch());
        try { homesCfg.save(homesFile); } catch (Exception e) { e.printStackTrace(); }
        cache.computeIfAbsent(home.getOwner(), k->new ArrayList<>()).removeIf(h->h.getName().equalsIgnoreCase(home.getName()));
        cache.computeIfAbsent(home.getOwner(), k->new ArrayList<>()).add(home);
    }

    public void deleteHome(UUID owner, String name) {
        homesCfg.set(owner.toString() + "." + name, null);
        try { homesCfg.save(homesFile); } catch (Exception e) { e.printStackTrace(); }
        cache.computeIfAbsent(owner, k->new ArrayList<>()).removeIf(h->h.getName().equalsIgnoreCase(name));
    }
}
