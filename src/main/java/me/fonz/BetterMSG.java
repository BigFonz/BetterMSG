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

    private String targetFormat;
    private String senderFormat;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        lastMessaged = new HashMap<>();

        getCommand("msg").setExecutor(new MessageCommand(this));
        getCommand("reply").setExecutor(new ReplyCommand(this));
    }

    public void sendMessage(Player target, CommandSender sender, String[] messageArgs) {
        String message = "";

        for (String word : messageArgs) {
            message += word + " ";
        }

        targetFormat = getConfig().getString("target").replaceAll("%target%", target.getName()).
                replaceAll("%sender%", sender.getName()).
                replaceAll("%message%", message);

        senderFormat = getConfig().getString("sender").replaceAll("%target%", target.getName()).
                replaceAll("%sender%", sender.getName()).
                replaceAll("%message%", message);

        target.sendMessage(ChatColor.translateAlternateColorCodes('&', targetFormat));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', senderFormat));

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
