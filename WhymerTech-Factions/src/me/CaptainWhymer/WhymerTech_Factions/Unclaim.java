package me.CaptainWhymer.WhymerTech_Factions;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;

public abstract class Unclaim {
    public static void unclaim(Player p, String[] args, DataManager data) {
        if (!p.hasPermission("wt.base.basic.unclaim")) {
            p.sendMessage(ChatColor.RED + "You do not have permission to do that");
            return;
        }

        // If chunk belongs to the player
        if (Objects.equals(data.getConfig().get("worlds." + p.getWorld().getName() + ".chunks." + p.getLocation().getChunk() + ".owner"), p.getUniqueId().toString())) {
            // Remove chunk from data.yml
            data.getConfig().set("worlds." + p.getWorld().getName() + ".chunks." + p.getLocation().getChunk(), null);

            data.saveConfig();
            p.sendMessage(ChatColor.YELLOW + "Chunk unclaimed successfully");
        } else {
            p.sendMessage(ChatColor.RED + "This land belongs to " + data.getConfig().get("chunks." + p.getLocation().getChunk() + ".domain"));
        }
    }
}
