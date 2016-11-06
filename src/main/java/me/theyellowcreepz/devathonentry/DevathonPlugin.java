package me.theyellowcreepz.devathonentry;

import me.theyellowcreepz.devathonentry.listeners.ListenerBlockBreak;
import me.theyellowcreepz.devathonentry.listeners.ListenerChestClick;
import me.theyellowcreepz.devathonentry.listeners.ListenerInsertFurnaceItem;
import me.theyellowcreepz.devathonentry.listeners.ListenerMachineTrigger;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Level;

public class DevathonPlugin extends JavaPlugin implements Listener {

    private static DevathonPlugin instance = null;
    public HashMap<Player, Sign> registry = new HashMap<>();

    public static DevathonPlugin getInstance() {
        return DevathonPlugin.instance;
    }

    /**
     * Set the DevathonPlugin instance. Throws an IllegalStateException if the instance has already been set.
     *
     * @param instance The plugin instance.
     */
    public static void setInstance(DevathonPlugin instance) {

        if (DevathonPlugin.getInstance() != null) {
            throw new IllegalStateException("The instance has already been set! ");
        }
        DevathonPlugin.instance = instance;
    }

    /**
     * The verbose method - any strings sent to this method will be logged using the verbose layout.
     *
     * @param msg The verbose message to be logged.
     */
    public void verbose(String msg) {
        this.getServer().getLogger().log(Level.INFO, Strings.VERBOSE_PREFIX + msg);
    }

    /**
     * The method where all Bukkit listeners should be registered.
     */
    public void registerListeners() {
        // Fill in with any event listeners later on.
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new ListenerMachineTrigger(this), this);
        this.getServer().getPluginManager().registerEvents(new ListenerBlockBreak(), this);
        this.getServer().getPluginManager().registerEvents(new ListenerInsertFurnaceItem(this), this);
        this.getServer().getPluginManager().registerEvents(new ListenerChestClick(this), this);
    }

    @Override
    public void onEnable() {
        DevathonPlugin.setInstance(this);
        this.getServer().getLogger().log(Level.INFO, "The Devathon project plugin has been initialized. ");
        this.getServer().getLogger().log(Level.INFO, "For usage instructions (how to create the machine), please see the README.md file in the Git repo. ");
        this.registerListeners();
    }

    @Override
    public void onDisable() {
        this.getServer().getLogger().log(Level.INFO, "The Devathon machine project has been disabled. ");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent evt) {
        this.registry.remove(evt.getPlayer());
    }

    @EventHandler
    public void onCloseInv(InventoryCloseEvent evt) {
        this.registry.remove(evt.getPlayer());
    }
}

