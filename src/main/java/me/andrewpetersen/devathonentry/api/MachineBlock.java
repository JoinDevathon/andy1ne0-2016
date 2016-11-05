package me.andrewpetersen.devathonentry.api;

/*
 * This project has been written by Andrew Petersen, and anyone who has contributed to the source code
 * (or where otherwise declared). 
 *
 * This code is licensed under the GPLv3 License, a copy of which can be found in the root directory. 
 */

import jdk.nashorn.internal.ir.Block;
import lombok.Getter;
import lombok.Setter;

/**
 * The wrapper class for any blocks that need to be generated when creating a machine.
 */
@Getter
@Setter
public class MachineBlock {

    private MachineLevel masterLevel;

    private Block block;

    private boolean explodeEffect = false;

    private boolean smokeEffect = false;

    private boolean sparkleEffect = false;

    private boolean soundEffects = false;

    /**
     * The default constructor for this class, used to set various variables.
     *
     * @param master The master MachineLevel instance.
     */
    public MachineBlock(MachineLevel master) {
        this.setMasterLevel(master);
    }

}
