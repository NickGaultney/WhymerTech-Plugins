package me.CaptainWhymer.WhymerTech_Factions;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract class Add {
    public static void add(Player p, String[] args, DataManager data) {
        if (args.length == 0) {
            p.sendMessage(ChatColor.RED + "Usage: /base add <player_name>");
            return;
        }

        Player p2 = Main.getPlugin(Main.class).getServer().getPlayer(args[0]);
        if (p2 == null) {
            p.sendMessage(ChatColor.DARK_RED + "There is no \"" + args[0] + "\" online at the moment");
            return;
        }

        if (data.getConfig().contains("players." + p.getUniqueId().toString() + ".members." + p2.getName())) {
            p.sendMessage(ChatColor.GRAY + args[0] + " is already a member of your base");
            return;
        }

        // Add player to base => Give permissions
        data.getConfig().set("players." + p.getUniqueId().toString() + ".members." + p2.getName(), p2.getUniqueId().toString());

        data.saveConfig();
        p.sendMessage(ChatColor.YELLOW + p2.getName() + " added successfully");
    }
}
