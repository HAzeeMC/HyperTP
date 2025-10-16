package com.hazee.hypertp.command.home;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.hazee.hypertp.HyperTP;
import com.hazee.hypertp.model.Home;
import com.hazee.hypertp.util.ChatUtil;
import java.util.List;

public class HomeListCommand implements CommandExecutor {
    private final HyperTP plugin;
    public HomeListCommand(HyperTP plugin) { this.plugin = plugin; }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) { ChatUtil.send(sender, "&cOnly players."); return true; }
        Player p = (Player) sender;
        List<Home> homes = plugin.getHomeManager().getHomes(p.getUniqueId());
        if (homes.isEmpty()) { ChatUtil.send(p, "&eYou have no homes."); return true; }
        ChatUtil.send(p, "&6-- Your Homes --");
        homes.forEach(h -> ChatUtil.send(p, "&f- &a" + h.getName() + " &7(" + h.getLocation().getWorld().getName() + ")"));
        return true;
    }
}
