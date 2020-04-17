package me.CaptainWhymer.WhymerTech_Factions;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {
    public DataManager essentialData;
    public DataManager baseData;

    @Override
    public void onEnable() {
        this.essentialData = new DataManager(this, "data.yml");
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        Objects.requireNonNull(this.getCommand("claim")).setExecutor(new Claim());
    }
}