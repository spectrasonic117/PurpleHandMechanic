package com.spectrasonic.PoppyGrabPacks.Listeners;

import com.spectrasonic.PoppyGrabPacks.Items.block.PurpleBlock;
import com.spectrasonic.PoppyGrabPacks.Manager.ConfigManager;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import com.spectrasonic.PoppyGrabPacks.Items.item.PurpleGrabpackItem;
import org.bukkit.inventory.ItemStack;

public class PurpleGrapPackListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Player player = event.getPlayer();

        // Verificar si el jugador tiene el purple_grabpack en la mano
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (!PurpleGrabpackItem.isItem(itemInHand)) {
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
        // Obtener configuración de fuerzas del ConfigManager
        ConfigManager config = ConfigManager.getInstance(null);
        double forwardForce = config.getPurpleForwardForce();
        double upwardForce = config.getPurpleUpwardForce();

        // Aplicar impulso hacia arriba y adelante
        Vector forward = player.getLocation().getDirection().setY(0).normalize();
        Vector velocity = forward.multiply(forwardForce);
        velocity.setY(upwardForce);
        player.setVelocity(velocity);

        // Valores estáticos para sonido y partículas
        player.playSound(player.getLocation(), "poppy:purple_grabpack", 1.0f, 1.0f);
        player.getWorld().spawnParticle(Particle.END_ROD, player.getLocation(), 20, 0.5, 0.5, 0.5, 0.0);
    }
}
