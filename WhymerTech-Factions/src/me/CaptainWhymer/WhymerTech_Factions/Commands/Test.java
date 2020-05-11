package me.CaptainWhymer.WhymerTech_Factions.Commands;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.entity.Player;

public class Test {
    Player player;
    String[] args;
    DataManager data;

    public Test(Player player, String[] args, DataManager data) {
        this.player = player;
        this.args = args;
        this.data = data;
    }

    public void test() {
        player.sendMessage("It Works!");
    }
}
