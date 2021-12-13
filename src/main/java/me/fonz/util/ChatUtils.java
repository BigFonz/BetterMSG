package me.fonz.util;

import org.bukkit.ChatColor;

public class ChatUtils {

    public static String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String applePlaceholders(String text) {
        /*
        return text.replaceAll("%target%", target.getName()).
                replaceAll("%sender%", sender.getName()).
                replaceAll("%message%", message)));

         */
        return null;
    }



}
