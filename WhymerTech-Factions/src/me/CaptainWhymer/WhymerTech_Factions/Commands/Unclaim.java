package me.CaptainWhymer.WhymerTech_Factions.Commands;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Unclaim {
    Player player;
    String[] args;
    DataManager data;

    public Unclaim(Player player, String[] args, DataManager data) {
        this.player = player;
        this.args = args;
        this.data = data;
    }

    public void unclaim() {
        if (!player.hasPermission("wt.base.basic.unclaim")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to do that");
            return;
        }

        // If chunk belongs to the player
        if (Objects.equals(data.getConfig().get("worlds." + player.getWorld().getName() + ".chunks." + player.getLocation().getChunk() + ".owner"), player.getUniqueId().toString())) {
            // Remove chunk from data.yml
            data.getConfig().set("worlds." + player.getWorld().getName() + ".chunks." + player.getLocation().getChunk(), null);

            data.saveConfig();
            player.sendMessage(ChatColor.YELLOW + "Chunk unclaimed successfully");
        } else {
            player.sendMessage(ChatColor.RED + "This land belongs to " + data.getConfig().get("chunks." + player.getLocation().getChunk() + ".domain"));
        }
    }
}
