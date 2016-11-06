package me.andrewpetersen.devathonentry.levels;

import me.andrewpetersen.devathonentry.DevathonPlugin;
import me.andrewpetersen.devathonentry.api.MachineBlock;
import me.andrewpetersen.devathonentry.api.MachineLevel;
import org.bukkit.Location;

import java.util.ArrayList;

/*
 * This project has been written by Andrew Petersen, and anyone who has contributed to the source code
 * (or where otherwise declared). 
 *
 * This code is licensed under the GPLv3 License, a copy of which can be found in the root directory. 
 */
public class ThirdMachineLevel extends MachineLevel {
    /**
     * The constructor for this class, used to prepare the instance variable only.
     *
     * @param inst The main plugin instance. This variable may be accessed by MachineBlock instances.
     */
    public ThirdMachineLevel(DevathonPlugin inst) {
        super(inst);
    }

    @Override
    public ArrayList<MachineBlock> getBlocks(Location axisLocation) {
        return null;
    }
}
