package me.theyellowcreepz.devathonentry.listeners;

import me.theyellowcreepz.devathonentry.Strings;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/*
 * This project has been written by Yellow, and anyone who has contributed to the source code
 * (or where otherwise declared). 
 *
 * This code is licensed under the GPLv3 License, a copy of which can be found in the root directory. 
 */
public class ListenerBlockBreak implements Listener {

    /**
     * The block break event listener.
     *
     * @param evt The event.
     */
    @EventHandler
    public void onBreak(BlockBreakEvent evt) {
        if (evt.getBlock() != null) {
            if (evt.getBlock().hasMetadata(Strings.META_FLAG_UNBREAKABLE)) {
                if (!evt.getBlock().getMetadata(Strings.META_FLAG_UNBREAKABLE).get(0).asBoolean()) {
                    evt.setCancelled(true);
                }
            }
        }
    }

}
