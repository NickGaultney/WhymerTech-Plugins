package me.CaptainWhymer.WhymerTech_Factions;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;

public abstract class Help {
    public static void help(Player p, String[] args, DataManager data) {
        if (!p.hasPermission("wt.base.basic.help")) {
            p.sendMessage(ChatColor.RED + "You do not have permission to do that");
            return;
        }

        Method[] methods;
        if (args.length == 0) {
            p.sendMessage(ChatColor.GOLD + "____________________ HELP 1/1 ____________________");
            methods = Help.class.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                try {
                    p.sendMessage(ChatColor.GOLD + "/base " + ChatColor.BLUE + methods[i].getName() +
                            ChatColor.LIGHT_PURPLE +" --- " + ChatColor.AQUA + methods[i].invoke(null));
                } catch (Exception e) {
                    // Do nothing
                }
            }
        }
    }

    private static String members() {
        return "Lists the members of your base";
    }

    private static String claim() {
        return "Claims the chunk you are standing on";
    }

    private static String unclaim() {
        return "Unclaims the chunk you are standing on";
    }

    private static String add() {
        return "Add a user to your base";
    }

    private static String remove() {
        return "Remove a player from your base";
    }

    private static String permissions() {
        return "Does stuff";
    }

    private static String map() {
        return "Shows you a map of claimed territories";
    }

    private static String name() {
        return "Shows your base's name";
    }

    private static String rename() {
        return "Rename your base";
    }
}
