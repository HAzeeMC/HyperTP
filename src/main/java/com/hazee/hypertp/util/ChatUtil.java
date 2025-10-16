package com.hazee.hypertp.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatUtil {
    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    public static void send(CommandSender p, String s) {
        p.sendMessage(color(s));
    }
    public static void log(String s) {
        Bukkit.getConsoleSender().sendMessage(color(s));
    }
}
