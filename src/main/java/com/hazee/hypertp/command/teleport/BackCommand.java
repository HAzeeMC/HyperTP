package com.hazee.hypertp.command.teleport;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import com.hazee.hypertp.HyperTP;
import com.hazee.hypertp.util.ChatUtil;
import com.hazee.hypertp.listener.TeleportListener;

public class BackCommand implements CommandExecutor {
    private final HyperTP plugin;
    public BackCommand(HyperTP plugin) { this.plugin = plugin; }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) { ChatUtil.send(sender, "&cOnly players."); return true; }
        Player p = (Player) sender;
        TeleportListener tl = new TeleportListener(plugin); // note: in production, obtain the same listener instance
        if (tl.getLast(p.getUniqueId()) == null) { ChatUtil.send(p, "&cNo previous location."); return true; }
        p.teleport(tl.getLast(p.getUniqueId()));
        ChatUtil.send(p, "&aReturned to previous location.");
        return true;
    }
}
