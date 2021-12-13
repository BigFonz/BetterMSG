package me.fonz;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class IgnoresData {
    private final File ignoresFile;
    private final YamlConfiguration ignoresConfig;

    public IgnoresData() {
        ignoresFile = new File(BetterChat.getInstance().getDataFolder(), "ignores.yml");
        if (!ignoresFile.exists()) {
            try {
                ignoresFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ignoresConfig = YamlConfiguration.loadConfiguration(ignoresFile);
    }

    public File getIgnoresFile() {
        return ignoresFile;
    }

    public YamlConfiguration getIgnoresConfig() {
        return ignoresConfig;
    }

}
