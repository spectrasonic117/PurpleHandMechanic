package com.spectrasonic.PoppyGrabPacks.Listeners;

import com.spectrasonic.PoppyGrabPacks.Items.block.YellowBlock;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.Location;
import org.bukkit.util.RayTraceResult;

import com.spectrasonic.PoppyGrabPacks.Items.item.BlueGrabpackItem;
import org.bukkit.inventory.ItemStack;

public class BlueGrabPackListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Player player = event.getPlayer();

        // Verificar si el jugador tiene el blue_grabpack en la mano
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (!BlueGrabpackItem.isItem(itemInHand)) {
            return;
        }

        // Realizar ray trace para detectar bloques a distancia
        RayTraceResult result = player.getWorld().rayTraceBlocks(
                player.getEyeLocation(), // Punto de inicio (ojo del jugador)
                player.getLocation().getDirection(), // Dirección en la que mira el jugador
                15, // Distancia máxima (15 bloques)
                FluidCollisionMode.NEVER // No colisionar con fluidos
        );

        // Verificar si el ray trace golpeó un bloque
        if (result != null && result.getHitBlock() != null) {
            Block hitBlock = result.getHitBlock();

            // Verificar si el bloque golpeado es el yellow_block de ItemsAdder
            if (YellowBlock.isBlock(hitBlock)) {
                event.setCancelled(true);
                handleYellowBlockEffect(player, hitBlock);
            }
        } else if (event.getClickedBlock() != null && YellowBlock.isBlock(event.getClickedBlock())) {
            // También verificar el bloque clickeado tradicionalmente por si está cerca
            event.setCancelled(true);
            handleYellowBlockEffect(player, event.getClickedBlock());
        }
    }

    private void handleYellowBlockEffect(Player player, Block clickedBlock) {
        // Spawn partículas de portal inverso
        player.getWorld().spawnParticle(Particle.REVERSE_PORTAL, player.getLocation(), 30, 0.5, 0.5, 0.5, 0.1);

        // Reproducir sonido de portal
        player.playSound(player.getLocation(), "poppy:purple_grabpack", 1.0f, 1.0f);

        // Calcular la ubicación encima del bloque para teletransportar al jugador
        Location teleportLocation = clickedBlock.getLocation().add(0.5, 1, 0.5);

        // Mantener el yaw y pitch actuales del jugador para preservar la dirección de
        // la vista
        teleportLocation.setYaw(player.getLocation().getYaw());
        teleportLocation.setPitch(player.getLocation().getPitch());

        player.teleport(teleportLocation);
    }

}
