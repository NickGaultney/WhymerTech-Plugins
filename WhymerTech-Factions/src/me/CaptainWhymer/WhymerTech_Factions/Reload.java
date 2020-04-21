package me.CaptainWhymer.WhymerTech_Factions;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract class Reload {
    public static void reload(Player p, String[] args, DataManager data) {
        if (!p.hasPermission("wt.base.admin.reload")) {
            p.sendMessage(ChatColor.RED + "You do not have permission to do that");
            return;
        }

        data.reloadConfig();
        p.sendMessage(ChatColor.GREEN + "Config Reloaded!");
    }
}
