package me.CaptainWhymer.WhymerTech_Factions.Commands;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Rename {
    Player player;
    String[] args;
    DataManager data;

    public Rename(Player player, String[] args, DataManager data) {
        this.player = player;
        this.args = args;
        this.data = data;
    }

    public void rename() {
        if (!player.hasPermission("wt.base.basic.rename")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to do that");
            return;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /base rename <custom_name>");
            return;
        }

        data.getConfig().set("players." + player.getUniqueId().toString() + ".domain", args[0]);
        data.saveConfig();
        player.sendMessage(ChatColor.YELLOW + "Your domain shall now be called: " + ChatColor.GOLD + args[0]);
    }
}
