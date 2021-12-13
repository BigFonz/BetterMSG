package me.fonz;

import org.bukkit.ChatColor;

public class Config {
    public static final String CHAT_FORMAT = load("chat");
    public static final String TARGET_FORMAT = load("target");
    public static final String SENDER_FORMAT = load("sender");

    public static final String JOIN_FORMAT = load("join");
    public static final String QUIT_FORMAT = load("quit");

    public static final String IGNORE = load("ignore");
    public static final String UNIGNORE = load("unignore");
    public static final String IGNORE_LIST_TITLE = load("ignored-title");
    public static final String IGNORED_ENTRY = load("entry");

    private static String load(String messageName) {
        String message = BetterChat.getInstance().getConfig().getString(messageName);
        return ChatColor.translateAlternateColorCodes('&', message == null ? "null" : message);
    }

}
