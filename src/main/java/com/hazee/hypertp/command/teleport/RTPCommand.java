package com.hazee.hypertp.command.teleport;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import com.hazee.hypertp.HyperTP;
import com.hazee.hypertp.util.ChatUtil;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.Location;

public class RTPCommand implements CommandExecutor {
    private final HyperTP plugin;
    private final Random rnd = new Random();
    public RTPCommand(HyperTP plugin) { this.plugin = plugin; }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) { ChatUtil.send(sender, "&cOnly players."); return true; }
        Player p = (Player) sender;
        World w = p.getWorld();
        int range = plugin.getConfig().getInt("rtp.range", 1000);
        int rx = rnd.nextInt(range*2) - range;
        int rz = rnd.nextInt(range*2) - range;
        int highest = w.getHighestBlockYAt(rx, rz);
        Location loc = new Location(w, rx, highest+1, rz);
        p.teleport(loc);
        ChatUtil.send(p, "&aRandomly teleported.");
        return true;
    }
}
