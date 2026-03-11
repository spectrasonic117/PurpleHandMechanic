package com.spectrasonic.PoppyGrabPacks.Listeners;

import com.spectrasonic.PoppyGrabPacks.Items.block.YellowBlock;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.Location;

public class MagneticGrabPackListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) {
            return;
        }

        // Verificar si el bloque clickeado es el yellow_block de ItemsAdder
        if (YellowBlock.isBlock(clickedBlock)) {
            event.setCancelled(true);
            handleYellowBlockEffect(event.getPlayer(), clickedBlock);
        }
    }

    private void handleYellowBlockEffect(Player player, Block clickedBlock) {
        // Spawn partículas de portal inverso
        player.getWorld().spawnParticle(Particle.REVERSE_PORTAL, player.getLocation(), 30, 0.5, 0.5, 0.5, 0.1);

        // Reproducir sonido de portal
        player.playSound(player.getLocation(), "minecraft:portal.trigger", 1.0f, 1.0f);

        // Calcular la ubicación encima del bloque para teletransportar al jugador
        Location teleportLocation = clickedBlock.getLocation().add(0.5, 1, 0.5);
        player.teleport(teleportLocation);
    }

}
