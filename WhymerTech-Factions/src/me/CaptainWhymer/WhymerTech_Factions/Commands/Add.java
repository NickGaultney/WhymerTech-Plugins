package me.CaptainWhymer.WhymerTech_Factions.Commands;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import me.CaptainWhymer.WhymerTech_Factions.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Add {
    Player player;
    String[] args;
    DataManager data;

    public Add(Player player, String[] args, DataManager data) {
        this.player = player;
        this.args = args;
        this.data = data;
    }

    public void add() {
        if (!player.hasPermission("wt.base.basic.add")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to do that");
            return;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /base add <player_name>");
            return;
        }

        Player p2 = Main.getPlugin(Main.class).getServer().getPlayer(args[0]);
        if (p2 == null) {
            player.sendMessage(ChatColor.DARK_RED + "There is no \"" + args[0] + "\" online at the moment");
            return;
        }

        if (data.getConfig().contains("players." + player.getUniqueId().toString() + ".members." + p2.getName())) {
            player.sendMessage(ChatColor.GRAY + args[0] + " is already a member of your base");
            return;
        }

        // Add player to base => Give permissions
        data.getConfig().set("players." + player.getUniqueId().toString() + ".members." + p2.getName(), p2.getUniqueId().toString());

        data.saveConfig();
        player.sendMessage(ChatColor.YELLOW + p2.getName() + " added successfully");
    }
}
