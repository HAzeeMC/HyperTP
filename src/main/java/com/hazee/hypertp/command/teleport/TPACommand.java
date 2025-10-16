package com.hazee.hypertp.command.teleport;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.hazee.hypertp.HyperTP;
import com.hazee.hypertp.model.TPARequest;
import com.hazee.hypertp.util.ChatUtil;
import java.util.UUID;

public class TPACommand implements CommandExecutor {
    private final HyperTP plugin;
    public TPACommand(HyperTP plugin) { this.plugin = plugin; }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) { ChatUtil.send(sender, "&cOnly players."); return true; }
        if (args.length < 1) { ChatUtil.send(sender, "&cUsage: /tpa <player>"); return true; }
        Player p = (Player) sender;
        Player target = plugin.getServer().getPlayer(args[0]);
        if (target == null) { ChatUtil.send(p, "&cPlayer not found."); return true; }
        if (target.getUniqueId().equals(p.getUniqueId())) { ChatUtil.send(p, "&cYou cannot tpa to yourself."); return true; }
        TPARequest r = new TPARequest(p.getUniqueId(), target.getUniqueId(), true);
        plugin.getTpaManager().createRequest(r);
        ChatUtil.send(p, "&aTPA request sent to &f" + target.getName());
        ChatUtil.send(target, "&eYou have a TPA request from &f" + p.getName() + " &eType &a/tpaccept &eor &c/tpdeny");
        return true;
    }
}
