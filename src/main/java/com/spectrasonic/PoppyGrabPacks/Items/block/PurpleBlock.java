package com.spectrasonic.PoppyGrabPacks.Items.block;

import dev.lone.itemsadder.api.CustomBlock;
import org.bukkit.block.Block;

public class PurpleBlock {

    public static final String ID = "poppy:purple_block";

    public static CustomBlock getCustomBlock() {
        return CustomBlock.getInstance(ID);
    }

    public static boolean isRegistered() {
        return CustomBlock.isInRegistry(ID);
    }

    public static boolean isBlock(Block block) {
        if (block == null) return false;
        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
        if (customBlock == null) return false;
        return customBlock.getNamespacedID().equalsIgnoreCase(ID);
    }
}
