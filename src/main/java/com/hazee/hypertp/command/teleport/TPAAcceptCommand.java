package com.hazee.hypertp.command.teleport;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import com.hazee.hypertp.HyperTP;
import com.hazee.hypertp.model.TPARequest;
import com.hazee.hypertp.util.ChatUtil;

public class TPAAcceptCommand implements CommandExecutor {
    private final HyperTP plugin;
    public TPAAcceptCommand(HyperTP plugin) { this.plugin = plugin; }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) { ChatUtil.send(sender, "&cOnly players."); return true; }
        Player p = (Player) sender;
        TPARequest req = plugin.getTpaManager().getRequest(p.getUniqueId());
        if (req == null) { ChatUtil.send(p, "&cNo pending request."); return true; }
        Player requester = plugin.getServer().getPlayer(req.getRequester());
        if (requester == null) { ChatUtil.send(p, "&cRequester offline."); plugin.getTpaManager().removeRequest(p.getUniqueId()); return true; }
        if (req.isToTarget()) {
            // requester -> target (requester teleports to target)
            requester.teleport(p.getLocation());
            ChatUtil.send(requester, "&aTeleported to &f" + p.getName());
            ChatUtil.send(p, "&aAccepted.");
        } else {
            // target -> requester (target teleports to requester)
            p.teleport(requester.getLocation());
            ChatUtil.send(p, "&aTeleported to &f" + requester.getName());
            ChatUtil.send(requester, "&aAccepted.");
        }
        plugin.getTpaManager().removeRequest(p.getUniqueId());
        return true;
    }
}
