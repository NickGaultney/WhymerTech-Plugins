package me.CaptainWhymer.WhymerTech_Factions.Commands;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract class Remove {
    public static void remove(Player p, String[] args, DataManager data) {
        if (!p.hasPermission("wt.base.basic.remove")) {
            p.sendMessage(ChatColor.RED + "You do not have permission to do that");
            return;
        }

        if (args.length == 0) {
            p.sendMessage(ChatColor.RED + "Usage: /base remove <player_name>");
            return;
        }

        if (!data.getConfig().contains("players." + p.getUniqueId().toString() + ".members." + args[0])) {
            p.sendMessage(ChatColor.DARK_RED + "\"" + args[0] + "\" is not a member of your base");
            return;
        }

        // Remove player from base => Revoke permissions
        data.getConfig().set("players." + p.getUniqueId().toString() + ".members." + args[0], null);

        data.saveConfig();
        p.sendMessage(ChatColor.YELLOW + args[0] + " removed successfully");
    }
}
