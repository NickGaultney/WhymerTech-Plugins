package me.CaptainWhymer.WhymerTech_Factions;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract class Rename {
    public static void rename(Player p, String[] args, DataManager data) {
        if (args.length == 0) {
            p.sendMessage(ChatColor.RED + "Usage: /base rename <custom_name>");
            return;
        }

        data.getConfig().set("players." + p.getUniqueId().toString() + ".domain", args[0]);
        p.sendMessage(ChatColor.YELLOW + "Your domain shall now be called: " + ChatColor.GOLD + args[0]);
    }
}
