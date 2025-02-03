package com.spectrasonic.PurpleHandMechanic.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import java.util.HashMap;
import java.util.UUID;

import com.spectrasonic.PurpleHandMechanic.Main;

public class PlayerProjectileListener implements Listener {
    private final Main plugin;
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();
    private static final long COOLDOWN_TICKS = 10;

    public PlayerProjectileListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) return;
        Player player = event.getPlayer();

        // Check cooldown
        if (cooldowns.containsKey(player.getUniqueId())) {
            long timeLeft = cooldowns.get(player.getUniqueId()) - System.currentTimeMillis();
            if (timeLeft > 0) return;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || item.getType() != Material.PAPER) return;
        if (!item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if (!meta.hasCustomModelData() || meta.getCustomModelData() != 129) return;

        // Set cooldown
        cooldowns.put(player.getUniqueId(), System.currentTimeMillis() + (COOLDOWN_TICKS * 50));

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