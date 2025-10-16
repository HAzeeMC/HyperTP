package com.hazee.hypertp.command.home;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.hazee.hypertp.HyperTP;
import com.hazee.hypertp.model.Home;
import com.hazee.hypertp.util.ChatUtil;

public class HomeCommand implements CommandExecutor {
    private final HyperTP plugin;
    public HomeCommand(HyperTP plugin) { this.plugin = plugin; }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) { ChatUtil.send(sender, "&cOnly players."); return true; }
        Player p = (Player) sender;
        if (args.length < 1) { ChatUtil.send(p, "&cUsage: /home <name>"); return true; }
        String name = args[0];
        Home home = plugin.getHomeManager().getHome(p.getUniqueId(), name);
        if (home == null) { ChatUtil.send(p, "&cHome not found: " + name); return true; }
        // no cooldown checks for brevity
        p.teleport(home.getLocation());
        ChatUtil.send(p, "&aTeleported to home &f" + name);
        return true;
    }
}
