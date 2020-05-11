package me.CaptainWhymer.WhymerTech_Factions.Commands;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Reload {
    Player player;
    String[] args;
    DataManager data;

    public Reload(Player player, String[] args, DataManager data) {
        this.player = player;
        this.args = args;
        this.data = data;
    }

    public void reload() {
        if (!player.hasPermission("wt.base.admin.reload")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to do that");
            return;
        }

        data.reloadConfig();
        player.sendMessage(ChatColor.GREEN + "Config Reloaded!");
    }
}
