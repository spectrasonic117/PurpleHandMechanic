package com.spectrasonic.PurpleHandMechanic.Listeners;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Block block = event.getClickedBlock();
        if (block == null || block.getType() != Material.AMETHYST_BLOCK) return;
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || item.getType() != Material.PAPER) return;
        if (!item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if (!meta.hasCustomModelData() || meta.getCustomModelData() != 128) return;

        // Apply a launch: upward to approximately 5 blocks high and a slight forward push.
        Vector forward = player.getLocation().getDirection().setY(0).normalize();
        Vector velocity = forward.multiply(0.3);
        velocity.setY(0.9); // Adjusted to roughly achieve a 5-block jump height
        player.setVelocity(velocity);

        // Play the sound
        player.playSound(player.getLocation(), "minecraft:retrieve1", 1.0f, 1.0f);

        // Spawn END_ROD particles around the player
        player.getWorld().spawnParticle(Particle.END_ROD, player.getLocation(), 20, 0.5, 0.5, 0.5, 0.0);
    }
}
