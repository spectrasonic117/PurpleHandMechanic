package com.spectrasonic.PurpleHandMechanic.Listeners;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.spectrasonic.PurpleHandMechanic.Items.OrangeGrabPack;
import com.spectrasonic.PurpleHandMechanic.Main;

public class OrangeGrapPackListener implements Listener {
    private final Main plugin;
    private final OrangeGrabPack orangeGrabPack;

    public OrangeGrapPackListener(Main plugin) {
        this.plugin = plugin;
        this.orangeGrabPack = new OrangeGrabPack();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (!orangeGrabPack.isSimilar(item))
            return;

        // Play the cast sound
        player.playSound(player.getLocation(), "minecraft:castfast", 1.0f, 1.0f);

        // Get the direction the player is looking
        Vector direction = player.getLocation().getDirection();
        Location startLocation = player.getEyeLocation();

        // Create a new runnable for the projectile
        new BukkitRunnable() {
            private final Vector velocity = direction.multiply(1);
            private Location currentLocation = startLocation.clone();
            private int distance = 0;

            @Override
            public void run() {
                // Maximum travel distance
                if (distance > 30) {
                    this.cancel();
                    return;
                }

                // Move the projectile
                currentLocation.add(velocity);

                // Spawn the LAVA particle
                currentLocation.getWorld().spawnParticle(Particle.LAVA, currentLocation, 1, 0, 0, 0, 0);

                // Check if hit a block
                if (!currentLocation.getBlock().isPassable()) {
                    this.cancel();
                    return;
                }

                distance++;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }
}