package me.fonz;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCommand implements CommandExecutor {
    private BetterMSG plugin;

    public ReplyCommand(BetterMSG plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "You need to specify message!");
            return true;
        }

        Player lastMessagedPlayer = plugin.getLastMessaged((Player) sender);

        if (lastMessagedPlayer == null) {
            sender.sendMessage(ChatColor.RED + "No player has recently messaged you!");
            return true;
        }

        plugin.sendMessage(lastMessagedPlayer, sender, args);
        return true;
    }

}
