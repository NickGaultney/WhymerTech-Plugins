package me.CaptainWhymer.WhymerTech_Factions;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Set;

public abstract class Members {

    public static void members(Player p, String[] args, DataManager data) {
        ConfigurationSection members = data.getConfig().getConfigurationSection("players." + p.getUniqueId().toString() + ".members");
        if (members != null) {
            Set<String> keys = members.getKeys(true);
            p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "MEMBERS");
            p.sendMessage(ChatColor.DARK_PURPLE + "~~~~~~~~~~~~~~~");

            for (String s : keys) {
                String status = members.get(s).toString();
                if (status == null) {
                    continue;
                }
                p.sendMessage(ChatColor.AQUA + s);
            }

            p.sendMessage(ChatColor.DARK_PURPLE + "~~~~~~~~~~~~~~~");
        } else {
            p.sendMessage( ChatColor.RED + "You currently have no members!\nAdd players using /base add <player_name>");
        }
    }
}
