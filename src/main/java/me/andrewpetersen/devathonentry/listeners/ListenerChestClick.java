package me.andrewpetersen.devathonentry.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

/*
 * This project has been written by Andrew Petersen, and anyone who has contributed to the source code
 * (or where otherwise declared). 
 *
 * This code is licensed under the GPLv3 License, a copy of which can be found in the root directory. 
 */
public class ListenerChestClick implements Listener {

    /**
     * The chest click listener.
     *
     * @param evt The event.
     */
    @EventHandler
    public void onChestClick(PlayerInteractEvent evt) {
        if (evt.getClickedBlock() == null) return;
        if (evt.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (evt.getHand() != EquipmentSlot.HAND) return;


    }

}
