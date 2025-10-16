package com.hazee.hypertp;

import org.bukkit.plugin.java.JavaPlugin;
import com.hazee.hypertp.manager.ConfigManager;
import com.hazee.hypertp.manager.HomeManager;
import com.hazee.hypertp.manager.CooldownManager;
import com.hazee.hypertp.manager.TPAManager;
import com.hazee.hypertp.util.ChatUtil;
import com.hazee.hypertp.listener.TeleportListener;
import com.hazee.hypertp.listener.GUIListener;
import org.bukkit.Bukkit;

public final class HyperTP extends JavaPlugin {
    private static HyperTP instance;
    private ConfigManager configManager;
    private HomeManager homeManager;
    private CooldownManager cooldownManager;
    private TPAManager tpaManager;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        this.configManager = new ConfigManager(this);
        this.homeManager = new HomeManager(this);
        this.cooldownManager = new CooldownManager();
        this.tpaManager = new TPAManager(this);

        // Register listeners
        Bukkit.getPluginManager().registerEvents(new TeleportListener(this), this);
        Bukkit.getPluginManager().registerEvents(new GUIListener(this), this);

        // Register commands (simple lambda mapping for brevity)
        this.getCommand("sethome").setExecutor(new com.hazee.hypertp.command.home.SetHomeCommand(this));
        this.getCommand("home").setExecutor(new com.hazee.hypertp.command.home.HomeCommand(this));
        this.getCommand("delhome").setExecutor(new com.hazee.hypertp.command.home.DelHomeCommand(this));
        this.getCommand("homelist").setExecutor(new com.hazee.hypertp.command.home.HomeListCommand(this));
        this.getCommand("tpa").setExecutor(new com.hazee.hypertp.command.teleport.TPACommand(this));
        this.getCommand("tpahere").setExecutor(new com.hazee.hypertp.command.teleport.TPAHereCommand(this));
        this.getCommand("tpaccept").setExecutor(new com.hazee.hypertp.command.teleport.TPAAcceptCommand(this));
        this.getCommand("tpdeny").setExecutor(new com.hazee.hypertp.command.teleport.TPADenyCommand(this));
        this.getCommand("back").setExecutor(new com.hazee.hypertp.command.teleport.BackCommand(this));
        this.getCommand("rtp").setExecutor(new com.hazee.hypertp.command.teleport.RTPCommand(this));
        this.getCommand("htp").setExecutor(new com.hazee.hypertp.command.core.HTPCommand(this));

        ChatUtil.log("&aHyperTP enabled. Author: H_Azee");
    }

    @Override
    public void onDisable() {
        ChatUtil.log("&cHyperTP disabled.");
    }

    public static HyperTP getInstance() { return instance; }
    public ConfigManager getCfg() { return configManager; }
    public HomeManager getHomeManager() { return homeManager; }
    public CooldownManager getCooldownManager() { return cooldownManager; }
    public TPAManager getTpaManager() { return tpaManager; }
}
