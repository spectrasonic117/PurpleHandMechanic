package com.spectrasonic.PoppyGrabPacks.Listeners;

import com.spectrasonic.PoppyGrabPacks.Items.ItemsAdderUtils;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class MagneticGrabPackListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        // Verificar si es el magnetic_grabpack usando ItemsAdderUtils
        if (!ItemsAdderUtils.isMagneticGrabPack(item)) {
            return;
        }

        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) {
            return;
        }

        // Verificar si el bloque clickeado es el yellow_block de ItemsAdder
        if (ItemsAdderUtils.isYellowBlock(clickedBlock)) {
            event.setCancelled(true);
            handleYellowBlockEffect(player, clickedBlock);
        }
    }

    private void handleYellowBlockEffect(Player player, Block block) {
        // Teleportar al jugador al bloque
        player.teleport(block.getLocation().add(0.5, 1.5, 0.5));

        // Reproducir sonido de teleport
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);

        // Spawn partículas
        player.getWorld().spawnParticle(Particle.REVERSE_PORTAL, player.getLocation(), 30, 0.5, 0.5, 0.5, 0.1);
    }
}
