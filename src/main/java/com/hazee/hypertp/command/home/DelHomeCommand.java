package com.hazee.hypertp.command.home;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.hazee.hypertp.HyperTP;
import com.hazee.hypertp.util.ChatUtil;

public class DelHomeCommand implements CommandExecutor {
    private final HyperTP plugin;
    public DelHomeCommand(HyperTP plugin) { this.plugin = plugin; }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) { ChatUtil.send(sender, "&cOnly players."); return true; }
        Player p = (Player) sender;
        if (args.length < 1) { ChatUtil.send(p, "&cUsage: /delhome <name>"); return true; }
        String name = args[0];
        if (plugin.getHomeManager().getHome(p.getUniqueId(), name) == null) {
            ChatUtil.send(p, "&cHome not found.");
            return true;
        }
        plugin.getHomeManager().deleteHome(p.getUniqueId(), name);
        ChatUtil.send(p, "&aHome &f" + name + " &adeleted.");
        return true;
    }
}
