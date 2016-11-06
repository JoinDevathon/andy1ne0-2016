package me.andrewpetersen.devathonentry.api;

/*
 * This project has been written by Andrew Petersen, and anyone who has contributed to the source code
 * (or where otherwise declared). 
 *
 * This code is licensed under the GPLv3 License, a copy of which can be found in the root directory. 
 */

import me.andrewpetersen.devathonentry.DevathonPlugin;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;

/**
 * The class that should be used when listing all levels that need to be generated on the machine.
 */
public abstract class MachineLevel {

    private DevathonPlugin instance;

    /**
     * The constructor for this class, used to prepare the instance variable only.
     *
     * @param inst The main plugin instance. This variable may be accessed by MachineBlock instances.
     */
    public MachineLevel(DevathonPlugin inst) {
        this.setInstance(inst);
    }

    /**
     * The method where any blocks to be generated should be returned.
     *
     * @return Any blocks that need to be generated in this level.
     */
    public abstract ArrayList<MachineBlock> getBlocks(Location axisLocation);

    /**
     * The method called once the construction of the level is complete.
     *
     * @param buildLocation The *central block* of the machine being built.
     */
    public void finishedLevelBuilding(Location buildLocation) {
        // Stub, override if needed.
    }

    /**
     * The method called whenever a block is finished being placed.
     *
     * @param buildLocation   The central location of the said build.
     * @param progressPercent The percentage of build progress on this particular level, with 100 being complete and 0 being not started yet.
     * @param block           The block that was placed.
     */
    public void buildMilestone(Location buildLocation, double progressPercent, Block block) {
        // Stub, override if needed.
    }

    /**
     * The method called once the construction of the level is complete.
     *
     * @param buildLocation The *central block* of the machine being built.
     */
    public void beginLevelBuilding(Location buildLocation) {
        // Stub, override if needed.
    }

    public DevathonPlugin getInstance() {
        return this.instance;
    }

    public void setInstance(DevathonPlugin instance) {
        this.instance = instance;
    }
}
