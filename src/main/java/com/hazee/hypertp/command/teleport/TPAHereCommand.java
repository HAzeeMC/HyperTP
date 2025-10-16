package com.hazee.hypertp.command.teleport;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import com.hazee.hypertp.HyperTP;
import com.hazee.hypertp.model.TPARequest;
import com.hazee.hypertp.util.ChatUtil;

public class TPAHereCommand implements CommandExecutor {
    private final HyperTP plugin;
    public TPAHereCommand(HyperTP plugin) { this.plugin = plugin; }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) { ChatUtil.send(sender, "&cOnly players."); return true; }
        if (args.length < 1) { ChatUtil.send(sender, "&cUsage: /tpahere <player>"); return true; }
        Player p = (Player) sender;
        Player target = plugin.getServer().getPlayer(args[0]);
        if (target == null) { ChatUtil.send(p, "&cPlayer not found."); return true; }
        TPARequest r = new TPARequest(target.getUniqueId(), p.getUniqueId(), false);
        plugin.getTpaManager().createRequest(r);
        ChatUtil.send(p, "&aRequest sent.");
        ChatUtil.send(target, "&eYou have a tpahere request from &f" + p.getName() + " &eType &a/tpaccept &eor &c/tpdeny");
        return true;
    }
}
