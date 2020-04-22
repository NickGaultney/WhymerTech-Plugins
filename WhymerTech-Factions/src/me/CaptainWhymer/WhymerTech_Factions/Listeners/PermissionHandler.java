package me.CaptainWhymer.WhymerTech_Factions.Listeners;

import me.CaptainWhymer.WhymerTech_Factions.Files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class PermissionHandler implements Listener {
    DataManager data;

    public PermissionHandler(DataManager data) {
        this.data = data;
    }

    @EventHandler
    public void onChunkChange(PlayerMoveEvent event) {
        Player p = event.getPlayer();

        // If the player entered a new chunk
        if (event.getFrom().getChunk() != Objects.requireNonNull(event.getTo()).getChunk()) {
            // If the chunks have different owners
            String previousChunkOwner = data.getConfig().getString("worlds." + p.getWorld().getName() + ".chunks." + event.getFrom().getChunk() + ".owner");
            String currentChunkOwner = data.getConfig().getString("worlds." + p.getWorld().getName() + ".chunks." + event.getTo().getChunk() + ".owner");
            if (!Objects.equals(previousChunkOwner, currentChunkOwner)) {
                // If the chunk has been claimed
                if (currentChunkOwner != null) {
                    p.sendMessage(ChatColor.GOLD + "You have entered " +
                            data.getConfig().get("players." + currentChunkOwner + ".domain") + "'s domain");
                    // If the chunk has not been claimed
                } else {
                    p.sendMessage(ChatColor.GREEN + "You have entered the wilderness");
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(!playerHasPermission(event.getPlayer(), event.getBlock().getLocation().getChunk(), "block-break"));
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        event.setCancelled(!playerHasPermission(event.getPlayer(), event.getBlock().getLocation().getChunk(), "block-place"));
    }

    @EventHandler
    public void onBucketEmpty(PlayerBucketEmptyEvent event) {
        event.setCancelled(!playerHasPermission(event.getPlayer(), event.getBlock().getLocation().getChunk(), "block-place"));
    }

    @EventHandler
    public void onOpenChest(InventoryOpenEvent event) {
        if (event.getInventory().getHolder() instanceof Chest || event.getInventory().getHolder() instanceof DoubleChest) {
            Chunk chunk = Objects.requireNonNull(event.getInventory().getLocation()).getChunk();
            event.setCancelled(!playerHasPermission((Player) event.getPlayer(), chunk, "chest-open"));
        }
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        event.setCancelled(!blockHasPermission(event.getBlock().getLocation().getChunk(), "fire-spread"));
    }

    @EventHandler
    public void onFireSpread(BlockSpreadEvent event) {
        if (event.getSource().getType() == Material.FIRE) {
            event.setCancelled(!blockHasPermission(event.getSource().getLocation().getChunk(), "fire-spread"));
        }
    }

    @EventHandler
    public void onExplosion(ExplosionPrimeEvent event) {
        event.setCancelled(!blockHasPermission(event.getEntity().getLocation().getChunk(), "explosions"));
    }

    @EventHandler
    public void onLavaFlow(BlockFromToEvent event) {
        if (event.getBlock().getType() == Material.LAVA) {
            Chunk chunk = event.getBlock().getLocation().getChunk();
            event.setCancelled(!blockHasPermission(chunk, "lava-flow"));
        }
    }

    private boolean playerHasPermission(Player player, Chunk chunk, String permission) {
        // If the block is in a chunk claimed by a player
        if (data.getConfig().contains("worlds." + player.getWorld().getName() + ".chunks." + chunk)) {
            String owner = data.getConfig().getString("worlds." + player.getWorld().getName() + ".chunks." + chunk + ".owner");
            // If the player is not the owner
            if (!player.getUniqueId().toString().equals(owner)) {
                // If player is a member
                if (data.getConfig().contains("players." + owner + ".members." + player.getName())) {
                    // If permission is disabled
                    return data.getConfig().getBoolean("players." + owner + ".permissions." + permission);
                    // If player is not a member
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean blockHasPermission(Chunk chunk, String permission) {
        // If the block is in a chunk claimed by a player
        if (data.getConfig().contains("worlds." + chunk.getWorld() + ".chunks." + chunk)) {
            String owner = data.getConfig().getString("worlds." + chunk.getWorld() + ".chunks." + chunk + ".owner");
            // If fire-spread is disabled
            return data.getConfig().getBoolean("players." + owner + ".permissions." + permission);
        }

        return true;
    }
}
