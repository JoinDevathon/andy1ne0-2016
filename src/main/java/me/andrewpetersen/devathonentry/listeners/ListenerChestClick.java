package me.andrewpetersen.devathonentry.listeners;

import me.andrewpetersen.devathonentry.Strings;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;

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
        if (evt.getClickedBlock().getType() == Material.ENDER_CHEST) {
            Location loc = evt.getClickedBlock().getLocation();
            loc.setY(loc.getY() - 2);
            if (loc.getBlock() != null && loc.getBlock().getState() instanceof Sign) {
                Sign s = (Sign) loc.getBlock().getState();
                if (s.getLine(0).equals(Strings.SIGN_MACHINE_PREFIX_INTERNAL)) {
                    evt.setCancelled(true);
                    Inventory inv = Bukkit.createInventory(null, InventoryType.FURNACE, Strings.ENDER_CHEST_TITLE);

                }
            }
        }

    }

}
