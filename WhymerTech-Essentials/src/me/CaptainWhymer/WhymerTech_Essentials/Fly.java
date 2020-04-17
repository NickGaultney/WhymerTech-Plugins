package me.CaptainWhymer.WhymerTech_Essentials;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Check permissions
        if (!sender.hasPermission("wt.fly")) {
            sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to do that.");
            return true;
        }

        if (label.equalsIgnoreCase("fly")) {
            // If the console calls this command
            if (!(sender instanceof Player)) {
                sender.sendMessage("*console goes flying");
                return true;
            }

            Player p = (Player) sender;
            if (!p.getAllowFlight()) {
                p.sendMessage(ChatColor.DARK_GREEN + "Vroom Vroom!");
                p.setAllowFlight(true);
            } else {
                p.sendMessage(ChatColor.YELLOW + "Skid. skirt.");
                p.setAllowFlight(false);
            }

            return true;
        }

        return false;
    }
}
