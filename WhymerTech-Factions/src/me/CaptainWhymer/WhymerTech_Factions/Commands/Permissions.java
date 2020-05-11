package me.CaptainWhymer.WhymerTech_Factions.Commands;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;


/*
    One day someone should make the a much prettier output by using an inventory with
    red wool representing FALSE and green wool representing TRUE
 */
public class Permissions {
    Player player;
    String[] args;
    DataManager data;

    private static final ArrayList<String> PERMISSIONS = new ArrayList<>(Arrays.asList(
            "block-break", "block-place", "chest-open",
            "explosions", "fire-spread", "lava-flow"
    ));

    public Permissions(Player player, String[] args, DataManager data) {
        this.player = player;
        this.args = args;
        this.data = data;
    }

    public void permissions() {
        if (!player.hasPermission("wt.base.basic.permissions")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to do that");
            return;
        }

        if (args.length == 0) {
            list(player, data);
        } else if (args[0].equalsIgnoreCase("set")) {
            set(player, Arrays.copyOfRange(args, 1, args.length), data);
        }
    }

    private static void list(Player p, DataManager data) {
        ConfigurationSection permissions = data.getConfig().getConfigurationSection("players." + p.getUniqueId().toString() + ".permissions");
        if (permissions != null) {
            Set<String> keys = permissions.getKeys(true);
            p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "PERMISSIONS");
            p.sendMessage(ChatColor.DARK_PURPLE + "~~~~~~~~~~~~~~~");

            for (String s : keys) {
                String status = permissions.get(s).toString();
                if (status == null) {
                    continue;
                }
                ChatColor booleanColor = (status.equals("true")) ? ChatColor.GREEN : ChatColor.RED;
                p.sendMessage(booleanColor + s);
            }

            p.sendMessage(ChatColor.DARK_PURPLE + "~~~~~~~~~~~~~~~");
            p.sendMessage(ChatColor.BLUE + "To change permissions use: \n/base permissions set <permission_name> <true/false>");
        } else {
            p.sendMessage( ChatColor.RED + "You must claim land before adjusting permissions");
        }
    }

    private static void set(Player p, String[] args, DataManager data) {
        if (args.length < 2) {
            p.sendMessage(ChatColor.RED + "Usage: /base permissions set <permission_name> <true/false>");
            return;
        }

        // If requested permission is valid
        if (PERMISSIONS.contains(args[0].toLowerCase())) {
            if (args[1].equalsIgnoreCase("true")) {
                data.getConfig().set("players." + p.getUniqueId().toString() + ".permissions." + args[0], true);
                p.sendMessage(ChatColor.BLUE + args[0] + ChatColor.YELLOW + " has been set to " + ChatColor.GREEN + "TRUE");
            } else {
                data.getConfig().set("players." + p.getUniqueId().toString() + ".permissions." + args[0], false);
                p.sendMessage(ChatColor.BLUE + args[0] + ChatColor.YELLOW + " has been set to " + ChatColor.RED + "FALSE");
            }

            data.saveConfig();
        } else {
            p.sendMessage(ChatColor.BLUE + args[0] + ChatColor.RED + " is not a valid permission!");
        }
    }
}
