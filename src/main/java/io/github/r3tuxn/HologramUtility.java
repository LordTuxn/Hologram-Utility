package github.r3tuxn;

import github.r3tuxn.commands.CMD_Reload;
import github.r3tuxn.commands.CMD_SetHologram;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class HologramUtility extends JavaPlugin {

    private static HologramUtility plugin;

    private FileConfiguration config;
    private File configFile;

    private final static List<HologramDisplay> hologramDisplays = new ArrayList<>();

    @Override
    public void onEnable() {
        plugin = this;

        this.getCommand("sethologram").setExecutor(new CMD_SetHologram());
        this.getCommand("hreload").setExecutor(new CMD_Reload());
    }

    public static void registerHologramDisplay(HologramDisplay display) {
        hologramDisplays.add(display);

        FileConfiguration config = getPlugin().getConfig();
        String path ="holograms." + display.getHologramName();

        if(!config.contains(path)) {
            config.set(display.getDefaultPath() + "enabled", false);
            config.set(display.getDefaultPath() + "world", "world");
            config.set(display.getDefaultPath() + "x", 0);
            config.set(display.getDefaultPath() + "y", 0);
            config.set(display.getDefaultPath() + "z", 0);
        } else if(config.getBoolean(display.getDefaultPath() + "enabled")) {
            display.start();
        }
    }

    @Override
    public void reloadConfig() {
        configFile = new File(getDataFolder(), "config.yml");
        if (configFile.exists()) {
            config = YamlConfiguration.loadConfiguration(configFile);
        } else {
            // Look for default configuration file
            Reader defConfigStream = new InputStreamReader(this.getResource("defaultConfig.yml"), StandardCharsets.UTF_8);

            config = YamlConfiguration.loadConfiguration(defConfigStream);
        }
        saveConfig();
    }

    @Override
    public FileConfiguration getConfig() {
        if (config == null) {
            reloadConfig();
        }
        return config;
    }

    @Override
    public void saveConfig() {
        if (config == null || configFile == null) {
            return;
        }

        try {
            getConfig().save(configFile);
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
        }
    }

    public static HologramUtility getPlugin() {
        return plugin;
    }

    public static List<HologramDisplay> getHologramDisplays() {
        return hologramDisplays;
    }
}
