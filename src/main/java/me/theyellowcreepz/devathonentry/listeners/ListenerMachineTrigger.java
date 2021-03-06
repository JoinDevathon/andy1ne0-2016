package me.theyellowcreepz.devathonentry.listeners;

/*
 * This project has been written by Yellow, and anyone who has contributed to the source code
 * (or where otherwise declared). 
 *
 * This code is licensed under the GPLv3 License, a copy of which can be found in the root directory. 
 */

import me.theyellowcreepz.devathonentry.DevathonPlugin;
import me.theyellowcreepz.devathonentry.Strings;
import me.theyellowcreepz.devathonentry.util.MachineBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

/**
 * The listener class for when a machine creation is activated.
 */
public class ListenerMachineTrigger implements Listener {

    private final DevathonPlugin instance;

    @java.beans.ConstructorProperties({"instance"})
    public ListenerMachineTrigger(DevathonPlugin instance) {
        this.instance = instance;
    }

    /**
     * The block place event listener, used to start the generation of a Machine.
     *
     * @param evt The event.
     */
    @EventHandler
    public void onBlockPlace(PlayerInteractEvent evt) {
        if (evt.getClickedBlock() == null) return;
        if (evt.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (evt.getHand() != EquipmentSlot.HAND) return;
        if (evt.getPlayer().getInventory().getItemInMainHand() != null) {
            if (evt.getPlayer().getInventory().getItemInMainHand().getType() == Material.GOLD_HOE) {
                Player pl = evt.getPlayer();

                if (evt.getClickedBlock().getType() == Material.COAL_BLOCK) {
                    this.getInstance().verbose("Started checking if the machine components are all present. ");

                    Location axisLocation = evt.getClickedBlock().getLocation();
                    axisLocation.setY(axisLocation.getY() - 1);
                    if (isNull(axisLocation.getBlock())) {
                        this.failedAttempt(pl);
                    }

                    if (MachineBuilder.validateBuildX(axisLocation) /*|| MachineBuilder.validateBuildZ(axisLocation)*/) {
                        this.getInstance().verbose("Construction of a machine for player " + pl.getName() + " has begun. ");
                        MachineBuilder.buildMachine(axisLocation, pl);
                    }

                }
            }
        }
    }

    /**
     * A simple method to check if a block is null.
     *
     * @param b The block to check.
     * @return True if the block is null, false if it is not null.
     */
    public boolean isNull(Block b) {
        return b == null;
    }


    /**
     * The method used to simplify sending the player a message when he fails to create the machine properly.
     *
     * @param pl The player interacting.
     */
    public void failedAttempt(Player pl) {
        pl.sendMessage(Strings.FAILED_MACHINE_CREATION);
    }

    public DevathonPlugin getInstance() {
        return this.instance;
    }
}
