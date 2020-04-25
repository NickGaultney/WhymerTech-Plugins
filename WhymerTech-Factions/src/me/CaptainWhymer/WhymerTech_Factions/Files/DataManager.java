package me.CaptainWhymer.WhymerTech_Factions.Files;

import me.CaptainWhymer.WhymerTech_Factions.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class DataManager {

    private Main plugin;
    private FileConfiguration dataConfig = null;
    private File configFile = null;
    private String file;

    public DataManager(Main plugin, String file) {
        this.plugin = plugin;
        this.file = file;
        saveDefaultConfig();
    }

    public void reloadConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.plugin.getDataFolder(), this.file);
        }

        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.plugin.getResource(this.file);
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader((defaultStream)));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (this.dataConfig == null) { reloadConfig(); }

        return this.dataConfig;
    }

    public void saveConfig() {
        if (this.dataConfig == null || this.configFile == null) { return; }

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config file to " + this.configFile, e);
        }
    }

    public void saveDefaultConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.plugin.getDataFolder(), this.file);
        }

        if (!this.configFile.exists()) {
            this.plugin.saveResource(this.file, false);
        }
    }

    public void initializeBase(Player p) {
        // Sets domain-name to player's name
        getConfig().set("players." + p.getUniqueId().toString() + ".domain", p.getName());

        // Sets default permissions
        getConfig().set("players." + p.getUniqueId().toString() + ".permissions.block-break", false);
        getConfig().set("players." + p.getUniqueId().toString() + ".permissions.block-place", false);
        getConfig().set("players." + p.getUniqueId().toString() + ".permissions.chest-open", false);
        getConfig().set("players." + p.getUniqueId().toString() + ".permissions.fire-spread", false);
        getConfig().set("players." + p.getUniqueId().toString() + ".permissions.lava-flow", false);
        getConfig().set("players." + p.getUniqueId().toString() + ".permissions.explosions", false);

        saveConfig();
    }
}
