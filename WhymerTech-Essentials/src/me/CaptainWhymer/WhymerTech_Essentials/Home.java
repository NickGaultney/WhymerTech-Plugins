package me.CaptainWhymer.WhymerTech_Essentials;

import me.CaptainWhymer.WhymerTech_Essentials.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.util.Objects;

public class Home implements CommandExecutor{

    public DataManager data;

    public Home(DataManager data) {
        this.data = data;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Check permissions
        if (!sender.hasPermission("wt.home")) {
            sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to do that.");
            return true;
        }

        if (label.equalsIgnoreCase("home")) {
            // If the console calls this command
            if (!(sender instanceof Player)) {
                sender.sendMessage("Consoles don't have homes...");
                return true;
            }

            // If player calls command
            Player p = (Player) sender;
            if (args.length == 0) {
                if (!data.getConfig().contains("players." + p.getUniqueId().toString() + ".homes.home")) {
                    p.sendMessage(ChatColor.GRAY + "You appear to be homeless...");
                    return true;
                }
                p.teleport(Objects.requireNonNull(data.getConfig().getLocation("players." + p.getUniqueId().toString() + ".homes.home")));
            } else {
                if (!data.getConfig().contains("players." + p.getUniqueId().toString() + ".homes." + args[0])) {
                    p.sendMessage(ChatColor.GRAY + "That doesn't seem like a good home...");
                    return true;
                }
                p.teleport(Objects.requireNonNull(data.getConfig().getLocation("players." + p.getUniqueId().toString() + ".homes." + args[0])));
            }

            return true;
        }

        return false;
    }
}
