package me.CaptainWhymer.WhymerTech_Essentials;

import me.CaptainWhymer.WhymerTech_Essentials.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelHome implements CommandExecutor {

    public DataManager data;

    public DelHome(DataManager data) {
        this.data = data;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Check permissions
        if (!sender.hasPermission("wt.delhome")) {
            sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to do that.");
            return true;
        }

        if (label.equalsIgnoreCase("delhome")) {
            // If the console calls this command
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only real people have homes!");
                return true;
            }

            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage(ChatColor.DARK_RED + "Usage: /delhome <home-name>");
                return true;
            }

            if (data.getConfig().contains("players." + p.getUniqueId().toString() + ".homes." + args[0])) {
                data.getConfig().set("players." + p.getUniqueId().toString() + ".homes." + args[0], null);
                p.sendMessage(ChatColor.GREEN + args[0] + " deleted successfully!");
                data.saveConfig();
            } else {
                p.sendMessage(ChatColor.DARK_RED + "Couldn't find " + args[0]);
            }

            return true;
        }

        return false;
    }
}
