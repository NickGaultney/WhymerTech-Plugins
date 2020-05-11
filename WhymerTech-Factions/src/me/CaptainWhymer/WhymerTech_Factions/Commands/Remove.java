package me.CaptainWhymer.WhymerTech_Factions.Commands;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Remove {
    Player player;
    String[] args;
    DataManager data;

    public Remove(Player player, String[] args, DataManager data) {
        this.player = player;
        this.args = args;
        this.data = data;
    }

    public void remove() {
        if (!player.hasPermission("wt.base.basic.remove")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to do that");
            return;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /base remove <player_name>");
            return;
        }

        if (!data.getConfig().contains("players." + player.getUniqueId().toString() + ".members." + args[0])) {
            player.sendMessage(ChatColor.DARK_RED + "\"" + args[0] + "\" is not a member of your base");
            return;
        }

        // Remove player from base => Revoke permissions
        data.getConfig().set("players." + player.getUniqueId().toString() + ".members." + args[0], null);

        data.saveConfig();
        player.sendMessage(ChatColor.YELLOW + args[0] + " removed successfully");
    }
}
