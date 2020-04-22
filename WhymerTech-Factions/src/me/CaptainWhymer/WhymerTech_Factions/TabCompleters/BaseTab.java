package me.CaptainWhymer.WhymerTech_Factions.TabCompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseTab implements TabCompleter {

    public static Map<String, List<String>> arguments = new HashMap<>();

    public BaseTab() {
        if (arguments.isEmpty()) {
            // Tier 1 commands
            arguments.put("base", new ArrayList<>());
            arguments.get("base").add("add"); arguments.get("base").add("remove");
            arguments.get("base").add("claim"); arguments.get("base").add("unclaim");
            arguments.get("base").add("help"); arguments.get("base").add("map");
            arguments.get("base").add("members"); arguments.get("base").add("name");
            arguments.get("base").add("rename"); arguments.get("base").add("permissions");
            arguments.get("base").add("reload");

            // Tier 2 commands
            arguments.put("permissions", new ArrayList<>());
            arguments.get("permissions").add("set");

            // Tier 3 commands
            arguments.put("set", new ArrayList<>());
            arguments.get("set").add("block-break"); arguments.get("set").add("block-place");
            arguments.get("set").add("chest-open"); arguments.get("set").add("explosions");
            arguments.get("set").add("fire-spread"); arguments.get("set").add("lava-flow");

            // Tier 4 commands
            arguments.put("values", new ArrayList<>());
            arguments.get("values").add("true"); arguments.get("values").add("false");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> result = new ArrayList<>();

        if (args.length == 1) {
            for (String command : arguments.get("base")) {
                if (command.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(command);
                }
            }
            return result;
        }

        if (args.length == 2) {
            String arg = args[0].toLowerCase();
            if (arguments.containsKey(arg)) {
                for (String command : arguments.get(arg)) {
                    if (command.toLowerCase().startsWith(args[1].toLowerCase())) {
                        result.add(command);
                    }
                }
                return result;
            }
        }

        if (args.length == 3) {
            String arg = args[1].toLowerCase();
            if (arguments.containsKey(arg)) {
                for (String command : arguments.get(arg)) {
                    if (command.toLowerCase().startsWith(args[2].toLowerCase())) {
                        result.add(command);
                    }
                }
                return result;
            }
        }

        if (args.length == 4) {
            if (args[1].equalsIgnoreCase("set")) {
                for (String command : arguments.get("values")) {
                    if (command.toLowerCase().startsWith(args[3].toLowerCase())) {
                        result.add(command);
                    }
                }
                return result;
            }
        }

        return null;
    }
}
