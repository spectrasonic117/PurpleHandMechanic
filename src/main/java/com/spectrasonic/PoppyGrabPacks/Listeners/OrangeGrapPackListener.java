package com.spectrasonic.PoppyGrabPacks.Listeners;

import com.spectrasonic.PoppyGrabPacks.Items.ItemsAdderUtils;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.spectrasonic.PoppyGrabPacks.Main;

public class OrangeGrapPackListener implements Listener {
    private final Main plugin;

    public OrangeGrapPackListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        // Verificar si es el orange_grabpack usando ItemsAdderUtils
        if (!ItemsAdderUtils.isOrangeGrabPack(item))
            return;

        // Play the cast sound
        player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1.0f, 1.0f);

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
