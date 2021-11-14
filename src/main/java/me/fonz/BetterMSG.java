package me.fonz;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class BetterMSG extends JavaPlugin {

    //---------TARGET-SENDER-------------
    private Map<UUID, UUID> lastMessaged;

    @Override
    public void onEnable() {
        lastMessaged = new HashMap<>();

        getCommand("msg").setExecutor(new MessageCommand(this));
        getCommand("reply").setExecutor(new ReplyCommand(this));
    }

    public void sendMessage(Player target, CommandSender sender, String[] messageArgs) {
        String message = "";
        for (String word : messageArgs) {
            message += word + " ";
        }

        /* OPTION 1
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6To &7" + target.getName() + "&f: " + message));
        target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6From &7" + sender.getName() + "&f: " + message));
        */

        // OPTION 2
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6To " + target.getName() + "&f: " + message));
        target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6From " + sender.getName() + "&f: " + message));

        target.playSound(target.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        setLastMessaged(target.getUniqueId(), sender instanceof Player ? ((Player) sender).getUniqueId() : null);
    }

    public Player getLastMessaged(Player target) {
        return Bukkit.getPlayer(lastMessaged.get(target.getUniqueId()));
    }

    private void setLastMessaged(UUID sender, UUID target) {
        lastMessaged.put(sender, target);
    }

}
