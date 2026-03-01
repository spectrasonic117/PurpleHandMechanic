package com.spectrasonic.PoppyGrabPacks.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.spectrasonic.PoppyGrabPacks.Items.MagneticGrabPack;

public class MagneticGrabPackListener implements Listener {
    private final MagneticGrabPack magneticGrabPack;

    public MagneticGrabPackListener() {
        this.magneticGrabPack = new MagneticGrabPack();
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        if (event.getState() != PlayerFishEvent.State.IN_GROUND) {
            return;
        }

        Player player = event.getPlayer();
        if (!isHoldingMagneticGrabPack(player)) {
            return;
        }

        FishHook hook = event.getHook();
        Block targetBlock = hook.getLocation().getBlock();

        if (targetBlock.getType() != Material.YELLOW_GLAZED_TERRACOTTA) {
            return;
        }

        teleportPlayerToHook(player, hook);
        playTeleportEffects(player, hook);
    }

    private boolean isHoldingMagneticGrabPack(Player player) {
        PlayerInventory inventory = player.getInventory();
        ItemStack mainHand = inventory.getItemInMainHand();
        ItemStack offHand = inventory.getItemInOffHand();

        return magneticGrabPack.isSimilar(mainHand) || magneticGrabPack.isSimilar(offHand);
    }

    private void teleportPlayerToHook(Player player, FishHook hook) {
        Location hookLocation = hook.getLocation().clone();
        Location teleportLocation = hookLocation.add(0, 1, 0);
        teleportLocation.setPitch(player.getLocation().getPitch());
        teleportLocation.setYaw(player.getLocation().getYaw());
        player.teleport(teleportLocation);
    }

    private void playTeleportEffects(Player player, FishHook hook) {
        Location playerLocation = player.getLocation();
        Location hookLocation = hook.getLocation();

        player.getWorld().spawnParticle(Particle.REVERSE_PORTAL, playerLocation, 30, 0.5, 0.5, 0.5, 0.1);
        player.getWorld().spawnParticle(Particle.REVERSE_PORTAL, hookLocation, 30, 0.5, 0.5, 0.5, 0.1);
        player.playSound(playerLocation, "minecraft:entity_enderman_teleport", 1.0f, 1.0f);
    }
}
