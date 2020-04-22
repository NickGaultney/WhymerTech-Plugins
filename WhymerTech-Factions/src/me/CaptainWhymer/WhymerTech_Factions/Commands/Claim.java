package me.CaptainWhymer.WhymerTech_Factions.Commands;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract class Claim {
    public static void claim(Player p, String[] args, DataManager data) {
        if (!p.hasPermission("wt.base.basic.claim")) {
            p.sendMessage(ChatColor.RED + "You do not have permission to do that");
            return;
        }

        // If chunk is not already claimed
        if (!data.getConfig().contains("worlds." + p.getWorld().getName() + ".chunks." + p.getLocation().getChunk())) {
            // Set player as owner of chunk
            data.getConfig().set("worlds." + p.getWorld().getName() + ".chunks." + p.getLocation().getChunk() + ".owner", p.getUniqueId().toString());

            // Sets domain-name to player's name
            data.getConfig().set("players." + p.getUniqueId().toString() + ".domain", p.getName());

            // Sets default permissions
            data.getConfig().set("players." + p.getUniqueId().toString() + ".permissions.block-break", false);
            data.getConfig().set("players." + p.getUniqueId().toString() + ".permissions.block-place", false);
            data.getConfig().set("players." + p.getUniqueId().toString() + ".permissions.chest-open", false);
            data.getConfig().set("players." + p.getUniqueId().toString() + ".permissions.fire-spread", true);
            data.getConfig().set("players." + p.getUniqueId().toString() + ".permissions.lava-flow", true);
            data.getConfig().set("players." + p.getUniqueId().toString() + ".permissions.explosions", true);

            data.saveConfig();
            p.sendMessage(ChatColor.YELLOW + "Chunk claimed successfully");
        } else {
            String owner = data.getConfig().getString("worlds." + p.getWorld().getName() + ".chunks." + p.getLocation().getChunk() + ".owner");
            p.sendMessage(ChatColor.RED + "This land belongs to " + ChatColor.GOLD + data.getConfig().get("players." + owner + ".domain"));
        }
    }
}