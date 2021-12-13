package me.fonz.listener;

import me.fonz.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void join(PlayerJoinEvent event) {
        event.setJoinMessage(Config.JOIN_FORMAT.replace("%player%", event.getPlayer().getName()));
    }

    @EventHandler
    public void quit(PlayerQuitEvent event) {
        event.setQuitMessage(Config.QUIT_FORMAT.replace("%player%", event.getPlayer().getName()));
    }

}
