package me.fonz.command;

import me.fonz.BetterChat;
import me.fonz.MessageManager;
import me.fonz.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReplyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command is only available for players!fdsfdsfdsfdsfds");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "You need to specify message!");
            return true;
        }

        Player senderPlayer = (Player) sender;
        MessageManager messageManager = BetterChat.getInstance().getMessageManager();
        UUID lastMessagedPlayer = messageManager.getWhoLastMessaged(senderPlayer);
        if (lastMessagedPlayer == null) {
            sender.sendMessage(ChatColor.RED + "No player has recently messaged you!");
            return true;
        }

        messageManager.sendPrivateMessage(Bukkit.getPlayer(lastMessagedPlayer), senderPlayer, args);
        return true;
    }

}
