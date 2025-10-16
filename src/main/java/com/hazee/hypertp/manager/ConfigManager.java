package com.hazee.hypertp.manager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import com.hazee.hypertp.util.ChatUtil;

public class ConfigManager {
    private final JavaPlugin plugin;
    private File langFolder;
    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.setup();
    }

    private void setup() {
        plugin.saveDefaultConfig();
        this.langFolder = new File(plugin.getDataFolder(), "langs");
        if (!langFolder.exists()) langFolder.mkdirs();
        // ensure langs exist
        plugin.saveResource("langs/en.yml", false);
        plugin.saveResource("langs/vn.yml", false);
        // save GUI defaults
        plugin.saveResource("gui/homegui.yml", false);
        plugin.saveResource("gui/tpagui.yml", false);
        plugin.saveResource("gui/rtpgui.yml", false);
        plugin.saveResource("homes.yml", false);
    }

    public FileConfiguration getConfigFile() {
        return plugin.getConfig();
    }
}
