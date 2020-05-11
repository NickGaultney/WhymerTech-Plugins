package me.CaptainWhymer.WhymerTech_Factions.Commands;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import me.CaptainWhymer.WhymerTech_Factions.Main;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Map {
    private static final int SIZE = 15;
    Player player;
    String[] args;
    DataManager data;

    public Map(Player player, String[] args, DataManager data) {
        this.player = player;
        this.args = args;
        this.data = data;
    }

    public void map() {
        if (!player.hasPermission("wt.base.basic.map")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to do that");
            return;
        }

        String[] chunks = new String[SIZE];
        Arrays.fill(chunks, "  ");

        int x = player.getLocation().getChunk().getX() - SIZE/2;
        int z = player.getLocation().getChunk().getZ() - SIZE/2;

        player.sendMessage("");
        player.sendMessage(ChatColor.GOLD + "                NORTH");

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Chunk chunk = Objects.requireNonNull(Main.getPlugin(Main.class).getServer().getWorld(player.getWorld().getName())).getChunkAt(x, z);
                if (chunk == player.getLocation().getChunk()) {
                    chunks[i] += (ChatColor.GOLD + "+ ");
                // If chunk is claimed by someone
                } else if (data.getConfig().contains("worlds." + player.getWorld().getName() + ".chunks." + chunk)) {
                    // If chunk is claimed by this player
                    if (Objects.equals(data.getConfig().getString("worlds." + player.getWorld().getName() + ".chunks." + chunk + ".owner"), player.getUniqueId().toString())) {
                        chunks[i] += (ChatColor.BLUE + "+ ");
                    // If chunk belongs to someone else
                    } else {
                        chunks[i] += (ChatColor.RED + "+ ");
                    }
                // If chunk is wilderness
                } else {
                    chunks[i] += (ChatColor.GREEN + "+ ");
                }
                x++;
            }
            z++;
            x -= SIZE;
        }

        for (String ss : chunks) {
            player.sendMessage(ss);
        }
    }
}
