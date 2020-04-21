package me.CaptainWhymer.WhymerTech_Factions;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public abstract class Map {
    private static final int SIZE = 15;
    public static void map(Player p, String[] args, DataManager data) {
        //ArrayList<StringBuilder> chunks = new ArrayList<>();
        // [z][x]
        String[] chunks = new String[SIZE];
        Arrays.fill(chunks, "  ");

        int x = p.getLocation().getChunk().getX() - SIZE/2;
        int z = p.getLocation().getChunk().getZ() - SIZE/2;

        p.sendMessage("");
        p.sendMessage(ChatColor.GOLD + "                NORTH");

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Chunk chunk = Objects.requireNonNull(Main.getPlugin(Main.class).getServer().getWorld(p.getWorld().getName())).getChunkAt(x, z);
                if (chunk == p.getLocation().getChunk()) {
                    chunks[i] += (ChatColor.GOLD + "+ ");
                // If chunk is claimed by someone
                } else if (data.getConfig().contains("chunks." + chunk)) {
                    // If chunk is claimed by this player
                    if (Objects.equals(data.getConfig().getString("chunks." + chunk + ".owner"), p.getUniqueId().toString())) {
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
            p.sendMessage(ss);
        }
    }
}
