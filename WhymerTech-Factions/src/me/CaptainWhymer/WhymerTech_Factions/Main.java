// TODO: Add comments here

package me.CaptainWhymer.WhymerTech_Factions;

import me.CaptainWhymer.WhymerTech_Factions.Commands.Base;
import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import me.CaptainWhymer.WhymerTech_Factions.Listeners.PermissionHandler;
import me.CaptainWhymer.WhymerTech_Factions.TabCompleters.BaseTab;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin implements Listener {
    public DataManager data;

    @Override
    public void onEnable() {
        this.data = new DataManager(this, "data.yml");
        registerCommands();
        registerEvents();
        this.getCommand("base").setTabCompleter(new BaseTab());
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        Objects.requireNonNull(this.getCommand("base")).setExecutor(new Base(this, this.data));
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new PermissionHandler(data), this);
    }
}