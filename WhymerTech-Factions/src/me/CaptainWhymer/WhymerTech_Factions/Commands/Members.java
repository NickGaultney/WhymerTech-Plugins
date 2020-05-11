package me.CaptainWhymer.WhymerTech_Factions.Commands;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Set;

public class Members {
    Player player;
    String[] args;
    DataManager data;

    public Members(Player player, String[] args, DataManager data) {
        this.player = player;
        this.args = args;
        this.data = data;
    }

    public void members() {
        if (!player.hasPermission("wt.base.basic.members")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to do that");
            return;
        }

        ConfigurationSection members = data.getConfig().getConfigurationSection("players." + player.getUniqueId().toString() + ".members");
        if (members != null) {
            Set<String> keys = members.getKeys(true);
            player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "MEMBERS");
            player.sendMessage(ChatColor.DARK_PURPLE + "~~~~~~~~~~~~~~~");

            for (String s : keys) {
                String status = members.get(s).toString();
                if (status == null) {
                    continue;
                }
                player.sendMessage(ChatColor.AQUA + s);
            }

            player.sendMessage(ChatColor.DARK_PURPLE + "~~~~~~~~~~~~~~~");
        } else {
            player.sendMessage( ChatColor.RED + "You currently have no members!\nAdd players using /base add <player_name>");
        }
    }
}
