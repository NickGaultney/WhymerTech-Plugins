package me.CaptainWhymer.WhymerTech_Factions;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract class Claim {
    public static void claim(Player p, String[] args, DataManager data) {
        // If chunk is not already claimed
        if (!data.getConfig().contains("chunks." + p.getLocation().getChunk())) {
            // Set player as owner of chunk
            data.getConfig().set("chunks." + p.getLocation().getChunk() + ".owner", p.getUniqueId().toString());

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
            p.sendMessage(ChatColor.RED + "This land belongs to " + data.getConfig().get("players." + p.getUniqueId().toString() + ".domain"));
        }
    }
}