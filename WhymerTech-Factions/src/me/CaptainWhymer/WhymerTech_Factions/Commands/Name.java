package me.CaptainWhymer.WhymerTech_Factions.Commands;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Name {
    Player player;
    String[] args;
    DataManager data;

    public Name(Player player, String[] args, DataManager data) {
        this.player = player;
        this.args = args;
        this.data = data;
    }

    public void name() {
        if (!player.hasPermission("wt.base.basic.name")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to do that");
            return;
        }

        if (data.getConfig().contains("players." + player.getUniqueId().toString() + ".domain")) {
            player.sendMessage(ChatColor.YELLOW + "You are known as: " + ChatColor.GOLD + data.getConfig().getString("players." + player.getUniqueId().toString() + ".domain"));
        } else {
            player.sendMessage(ChatColor.GRAY + "You appear to be nameless...");
        }
    }
}
