package com.hazee.hypertp.command.home;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.hazee.hypertp.HyperTP;
import com.hazee.hypertp.model.Home;
import com.hazee.hypertp.util.ChatUtil;
import java.util.UUID;

public class SetHomeCommand implements CommandExecutor {
    private final HyperTP plugin;
    public SetHomeCommand(HyperTP plugin) { this.plugin = plugin; }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) { ChatUtil.send(sender, "&cOnly players."); return true; }
        Player p = (Player) sender;
        if (args.length < 1) { ChatUtil.send(p, "&cUsage: /sethome <name>"); return true; }
        String name = args[0];
        // ensure uniqueness per owner
        if (plugin.getHomeManager().getHome(p.getUniqueId(), name) != null) {
            ChatUtil.send(p, "&cYou already have a home named &f" + name);
            return true;
        }
        Home home = new Home(p.getUniqueId(), name, p.getLocation());
        plugin.getHomeManager().saveHome(home);
        ChatUtil.send(p, "&aHome &f" + name + " &ahas been set.");
        return true;
    }
}
