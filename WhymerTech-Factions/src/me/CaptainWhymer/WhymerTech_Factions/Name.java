package me.CaptainWhymer.WhymerTech_Factions;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract class Name {
    public static void name(Player p, String[] args, DataManager data) {
        if (data.getConfig().contains("players." + p.getUniqueId().toString() + ".domain")) {
            p.sendMessage(ChatColor.YELLOW + "You are known as: " + ChatColor.GOLD + data.getConfig().getString("players." + p.getUniqueId().toString() + ".domain"));
        } else {
            p.sendMessage(ChatColor.GRAY + "You appear to be nameless...");
        }
    }
}
