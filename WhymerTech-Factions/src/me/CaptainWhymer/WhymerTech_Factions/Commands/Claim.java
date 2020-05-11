package me.CaptainWhymer.WhymerTech_Factions.Commands;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Claim {
    Player player;
    String[] args;
    DataManager data;

    public Claim(Player player, String[] args, DataManager data) {
        this.player = player;
        this.args = args;
        this.data = data;
    }

    public void claim() {
        if (!player.hasPermission("wt.base.basic.claim")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to do that");
            return;
            // TODO: Custom exception for not enough permissions
        }

        // If chunk is not already claimed
        if (!data.getConfig().contains("worlds." + player.getWorld().getName() + ".chunks." + player.getLocation().getChunk())) {
            // Set player as owner of chunk
            data.getConfig().set("worlds." + player.getWorld().getName() + ".chunks." + player.getLocation().getChunk() + ".owner", player.getUniqueId().toString());

            if (!data.getConfig().contains("players." + player.getUniqueId().toString())) {
                data.initializeBase(player);
            }

            data.saveConfig();
            player.sendMessage(ChatColor.YELLOW + "Chunk claimed successfully");
        } else {
            String owner = data.getConfig().getString("worlds." + player.getWorld().getName() + ".chunks." + player.getLocation().getChunk() + ".owner");
            player.sendMessage(ChatColor.RED + "This land belongs to " + ChatColor.GOLD + data.getConfig().get("players." + owner + ".domain"));
        }
    }
}