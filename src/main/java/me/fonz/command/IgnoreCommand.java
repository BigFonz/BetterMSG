package me.fonz.command;

import me.fonz.BetterChat;
import me.fonz.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class IgnoreCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command is only available for players!1");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "You need to include a player to ignore!");
            return true;
        }

        for (OfflinePlayer offlineTarget : Bukkit.getOfflinePlayers()) {
            if (offlineTarget.getName().equalsIgnoreCase(args[0])) {
                if (toggleIgnore(offlineTarget.getUniqueId(), ((Player) sender).getUniqueId())) {
                    sender.sendMessage(Config.IGNORE.replace("%target%", offlineTarget.getName()));
                } else {
                    sender.sendMessage(Config.UNIGNORE.replace("%target%", offlineTarget.getName()));
                }

                return true;
            }
        }

        sender.sendMessage(ChatColor.RED + "That player has never joined the server before!");
        return true;
    }

    private boolean toggleIgnore(UUID ignored, UUID ignoring) {
        Map<UUID, List<UUID>> ignores = BetterChat.getInstance().getMessageManager().getIgnores();

        List<UUID> allIgnoring = ignores.get(ignored);
        if (allIgnoring == null) {
            ignores.put(ignored, new ArrayList<>(Arrays.asList(ignoring)));
            return true;
        }

        if (!allIgnoring.contains(ignoring)) {
            allIgnoring.add(ignoring);
            System.out.println("bruh");
            return true;
        }

        System.out.println("aaaaaa");
        allIgnoring.remove(ignoring);
        if (allIgnoring.isEmpty()) {
            ignores.remove(ignored);
        }

        return false;
    }

}
