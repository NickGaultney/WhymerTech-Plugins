package me.CaptainWhymer.WhymerTech_Essentials;

import me.CaptainWhymer.WhymerTech_Essentials.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Homes implements CommandExecutor {

    public DataManager data;

    public Homes(DataManager data) {
        this.data = data;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Check permissions
        if (!sender.hasPermission("wt.homes")) {
            sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to do that.");
            return true;
        }

        if (label.equalsIgnoreCase("homes")) {
            // If the console calls this command
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only real people have homes!");
                return true;
            }

            Player p = (Player) sender;
            if (!data.getConfig().contains("players." + p.getUniqueId().toString() + ".homes")) {
                p.sendMessage(ChatColor.GRAY + "You appear to be homeless...");
                return true;
            }

            StringBuilder msg = new StringBuilder(ChatColor.BOLD + "" + ChatColor.DARK_GRAY + "| ");
            data.getConfig().getConfigurationSection("players." + p.getUniqueId().toString() + ".homes").getKeys(false).forEach( key -> {
                msg.append(ChatColor.GOLD + key).append(ChatColor.BOLD + "" + ChatColor.DARK_GRAY + " | ");
            });

            p.sendMessage(msg.toString());
        }

        return false;
    }
}
