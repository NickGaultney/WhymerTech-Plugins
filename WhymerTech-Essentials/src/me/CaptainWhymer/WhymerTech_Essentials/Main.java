package me.CaptainWhymer.WhymerTech_Essentials;

import me.CaptainWhymer.WhymerTech_Essentials.Files.DataManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {
    public DataManager essentialData;
    public DataManager baseData;

    @Override
    public void onEnable() {
        this.essentialData = new DataManager(this, "data.yml");
        this.essentialData = new DataManager(this, "base.yml");
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        Objects.requireNonNull(this.getCommand("Fly")).setExecutor(new Fly());
        Objects.requireNonNull(this.getCommand("sethome")).setExecutor(new SetHome(this.essentialData));
        Objects.requireNonNull(this.getCommand("home")).setExecutor(new Home(essentialData));
        Objects.requireNonNull(this.getCommand("homes")).setExecutor(new Homes(essentialData));
        Objects.requireNonNull(this.getCommand("delhome")).setExecutor(new DelHome(essentialData));
    }
}