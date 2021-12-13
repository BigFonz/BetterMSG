package me.fonz;

import me.fonz.command.*;
import me.fonz.listener.ChatListener;
import me.fonz.listener.JoinQuitListener;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public final class BetterChat extends JavaPlugin {
    private static BetterChat instance;

    private MessageManager messageManager;
    private IgnoresData ignoresData;

    public static BetterChat getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        messageManager = new MessageManager();
        ignoresData = new IgnoresData();

        if (ignoresData.getIgnoresConfig().isSet("ignores")) {
            Map<UUID, List<UUID>> ignores = new HashMap<>();
            for (String ignored : ignoresData.getIgnoresConfig().getConfigurationSection("ignores").getKeys(false)) {
                ignores.put(UUID.fromString(ignored), ignoresData.getIgnoresConfig().getStringList("ignores." + ignored).stream().map(UUID::fromString).collect(Collectors.toList()));
            }

            messageManager.setIgnores(ignores);
        }

        getServer().getPluginManager().registerEvents(new JoinQuitListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getCommand("msg").setExecutor(new MessageCommand());
        getCommand("reply").setExecutor(new ReplyCommand());
        getCommand("ignore").setExecutor(new IgnoreCommand());
        getCommand("ignorelist").setExecutor(new IgnoreListCommand());
    }

    @Override
    public void onDisable() {
        ignoresData.getIgnoresConfig().set("ignores", null);
        for (UUID ignored : messageManager.getIgnores().keySet()) {
            ignoresData.getIgnoresConfig().set("ignores." + ignored, messageManager.getIgnores().get(ignored).stream().map(uuid -> uuid + "").collect(Collectors.toList()));
        }

        try {
            ignoresData.getIgnoresConfig().save(ignoresData.getIgnoresFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }

}
