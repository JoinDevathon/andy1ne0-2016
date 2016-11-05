package me.andrewpetersen.devathonentry.api;

/*
 * This project has been written by Andrew Petersen, and anyone who has contributed to the source code
 * (or where otherwise declared). 
 *
 * This code is licensed under the GPLv3 License, a copy of which can be found in the root directory. 
 */

/**
 * The wrapper class for any blocks that need to be generated when creating a machine.
 */
public class MachineBlock {

    private MachineLevel masterLevel;

    /**
     * The default constructor for this class, used to set various variables.
     *
     * @param master The master MachineLevel instance.
     */
    public MachineBlock(MachineLevel master) {
        this.setMasterLevel(master);
    }

    /**
     * Get the MachineLevel instance that correlates to this machine block.
     *
     * @return The plugin instance.
     */
    public MachineLevel getMasterLevel() {
        return this.masterLevel;
    }

    /**
     * Set the MachineLevel instance for this block.
     *
     * @param masterLevel The master level instance. .
     */
    private void setMasterLevel(MachineLevel masterLevel) {
        this.masterLevel = masterLevel;
    }

}
