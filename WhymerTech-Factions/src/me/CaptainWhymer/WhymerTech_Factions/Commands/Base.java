package me.CaptainWhymer.WhymerTech_Factions.Commands;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import me.CaptainWhymer.WhymerTech_Factions.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Base implements CommandExecutor {
    public Main plugin;
    public DataManager data;

    public Base(Main plugin, DataManager data) {
        this.plugin = plugin;
        this.data = data;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Check permissions
        if (!sender.hasPermission("wt.base")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to do that.");
            return true;
        }

        if (label.equalsIgnoreCase("base")) {
            // If the console calls this command
            if (!(sender instanceof Player)) {
                sender.sendMessage("Consoles don't have homes...");
                return true;
            }

            // If player calls command
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage(ChatColor.RED + "Invalid Command: Try /base help");
                return true;
            }

            try {
                // Check to see if command exists
                Class<?> clazz = Class.forName(getClass().getPackage().getName() + "." + capitalize(args[0]));

                // convert args to lowercase
                for(int i = 0; i < args.length; i++) {
                    args[i] = args[i].toLowerCase();
                }

                Method method = clazz.getMethod(args[0], Player.class, String[].class, DataManager.class);
                method.invoke(null, p, Arrays.copyOfRange(args, 1, args.length), data);
            } catch (Exception e) {
                p.sendMessage(ChatColor.RED + "Invalid Command: Try /base help");
                e.printStackTrace();
            }

            return true;
        }

        return false;
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}