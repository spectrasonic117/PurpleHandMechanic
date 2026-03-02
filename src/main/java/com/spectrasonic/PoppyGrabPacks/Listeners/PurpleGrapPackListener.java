package com.spectrasonic.PoppyGrabPacks.Listeners;

import com.spectrasonic.PoppyGrabPacks.Items.block.PurpleBlock;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class PurpleGrapPackListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) {
            return;
        }

        // Verificar si el bloque clickeado es el purple_block de ItemsAdder
        if (PurpleBlock.isBlock(clickedBlock)) {
            event.setCancelled(true);
            handlePurpleBlockEffect(event.getPlayer());
        }
    }

    private void handlePurpleBlockEffect(Player player) {
        // Aplicar impulso hacia arriba y adelante
        Vector forward = player.getLocation().getDirection().setY(0).normalize();
        Vector velocity = forward.multiply(0.3);
        velocity.setY(0.9);
        player.setVelocity(velocity);

        // Reproducir sonido
        player.playSound(player.getLocation(), "minecraft:retrieve1", 1.0f, 1.0f);

        // Spawn partículas
        player.getWorld().spawnParticle(Particle.END_ROD, player.getLocation(), 20, 0.5, 0.5, 0.5, 0.0);
    }
}
