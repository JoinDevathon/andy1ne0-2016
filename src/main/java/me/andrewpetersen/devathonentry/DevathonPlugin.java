package me.andrewpetersen.devathonentry;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class DevathonPlugin extends JavaPlugin {

    private static DevathonPlugin instance = null;

    /**
     * Get the Devathon Instance.
     *
     * @return The plugin instance.
     */
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
    }

    @Override
    public void onEnable() {
        DevathonPlugin.setInstance(this);
        this.getServer().getLogger().log(Level.INFO, "The Devathon project plugin has been initialized. ");
        this.getServer().getLogger().log(Level.INFO, "For usage instructions (how to create the machine), please see the README.md file in the Git repo. ");
    }

    @Override
    public void onDisable() {
        this.getServer().getLogger().log(Level.INFO, "The Devathon machine project has been disabled. ");
    }
}

