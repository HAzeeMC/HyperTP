package com.hazee.hypertp.command.core;
import org.bukkit.command.*;
import com.hazee.hypertp.HyperTP;
import com.hazee.hypertp.util.ChatUtil;

public class HTPCommand implements CommandExecutor {
    private final HyperTP plugin;
    public HTPCommand(HyperTP plugin) { this.plugin = plugin; }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            ChatUtil.send(sender, "&6HyperTP &fv1 &7- &aCommands: /sethome /home /delhome /homelist /tpa /tpahere /tpaccept /tpdeny /back /rtp /htp reload");
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            ChatUtil.send(sender, "&aReloaded.");
            return true;
        }
        return true;
    }
}
