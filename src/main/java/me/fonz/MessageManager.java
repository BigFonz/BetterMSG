package me.fonz;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.*;

public class MessageManager {
    //--------------TARGET-SENDER-------------
    private Map<UUID, UUID> lastMessaged;

    //--------------IGNORED----IGNORING
    private Map<UUID, List<UUID>> ignores;

    public MessageManager() {
        lastMessaged = new HashMap<>();
        ignores = new HashMap<>();
    }

    public void sendPrivateMessage(Player target, Player sender, String[] messageArgs) {
        String message = "";
        for (String word : messageArgs) {
            message += word + " ";
        }

        target.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.TARGET_FORMAT.replaceAll("%target%", target.getName()).
                replaceAll("%sender%", sender.getName()).
                replaceAll("%message%", message)));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.SENDER_FORMAT.replaceAll("%target%", target.getName()).
                replaceAll("%sender%", sender.getName()).
                replaceAll("%message%", message)));

        target.playSound(target.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        setLastMessaged(target, sender);
    }

    public UUID getWhoLastMessaged(Player target) {
        return lastMessaged.get(target.getUniqueId());
    }

    public void setLastMessaged(Player sender, Player target) {
        lastMessaged.put(sender.getUniqueId(), target.getUniqueId());
    }

    public Map<UUID, List<UUID>> getIgnores() {
        return ignores;
    }

    public void setIgnores(Map<UUID, List<UUID>> ignores) {
        this.ignores = ignores;
    }

}
