package me.fonz.listener;

import me.fonz.BetterChat;
import me.fonz.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ChatListener implements Listener {

    @EventHandler
    public void chat(AsyncPlayerChatEvent event) {
        event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
        event.setFormat(Config.CHAT_FORMAT.replace("%sender%", "%s").replace("%message%", "%s"));

        List<UUID> ignores = BetterChat.getInstance().getMessageManager().getIgnores().get(event.getPlayer().getUniqueId());
        if (ignores == null) {
            return;
        }

        for (UUID player : ignores) {
            event.getRecipients().remove(Bukkit.getPlayer(player));
        }
    }

}
