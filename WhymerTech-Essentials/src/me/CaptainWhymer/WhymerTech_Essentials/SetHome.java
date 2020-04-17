package me.CaptainWhymer.WhymerTech_Essentials;

import me.CaptainWhymer.WhymerTech_Essentials.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SetHome implements CommandExecutor {

    public DataManager data;

    public SetHome(DataManager data) {
        this.data = data;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Check permissions
        if (!sender.hasPermission("wt.sethome")) {
            sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to do that.");
            return true;
        }

        if (label.equalsIgnoreCase("sethome")) {
            // If the console calls this command
            if (!(sender instanceof Player)) {
                sender.sendMessage("Consoles can't have homes...");
                return true;
            }

            // If player calls command
            Player p = (Player) sender;
            if (args.length == 0) {
                // store player location in data.yml
                data.getConfig().set("players." + p.getUniqueId().toString() + ".homes" + ".home", p.getLocation());
            } else {
                data.getConfig().set("players." + p.getUniqueId().toString() + ".homes." + args[0], p.getLocation());
            }

            data.saveConfig();
            p.sendMessage(ChatColor.YELLOW + "Home set.");

            return true;
        }

        return false;
    }
}
