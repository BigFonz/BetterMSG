package me.fonz.command;

import me.fonz.BetterChat;
import me.fonz.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class MessageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command is only available for players!33333");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "You need to specify a player and message! /msg <player> <message>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "That player is not online!");
            return true;
        }

        BetterChat.getInstance().getMessageManager().sendPrivateMessage(target, (Player) sender, Arrays.copyOfRange(args, 1, args.length));
        return true;
    }

}
