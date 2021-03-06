package me.theyellowcreepz.devathonentry.levels;

import me.theyellowcreepz.devathonentry.DevathonPlugin;
import me.theyellowcreepz.devathonentry.Strings;
import me.theyellowcreepz.devathonentry.api.MachineBlock;
import me.theyellowcreepz.devathonentry.api.MachineLevel;
import me.theyellowcreepz.devathonentry.util.MachineBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;

/*
 * This project has been written by Yellow, and anyone who has contributed to the source code
 * (or where otherwise declared). 
 *
 * This code is licensed under the GPLv3 License, a copy of which can be found in the root directory. 
 */
public class FirstMachineLevel extends MachineLevel {

    /**
     * The constructor for this class, used to prepare the instance variable only.
     *
     * @param inst The main plugin instance. This variable may be accessed by MachineBlock instances.
     */
    public FirstMachineLevel(DevathonPlugin inst) {
        super(inst);
    }

    @Override
    public ArrayList<MachineBlock> getBlocks(Location axisLocation) {

        ArrayList<MachineBlock> b = new ArrayList<>();
        axisLocation.setY(axisLocation.getY() + 1);
        Location origin = axisLocation.clone();
        axisLocation.setX(axisLocation.getX() - 1);
        axisLocation.setZ(axisLocation.getZ() - 3);
        double cloneZ = axisLocation.getZ();
        for (double i = cloneZ; i <= cloneZ + 6; i++) {
            axisLocation.setZ(i);
            axisLocation.getBlock().setMetadata(Strings.META_FLAG_UNBREAKABLE, new FixedMetadataValue(this.getInstance(), false));
            b.add(new MachineBlock(this, axisLocation.getBlock(), Material.COAL_BLOCK, false, true, true));
        }
        MachineBuilder.restoreOrigin(origin, axisLocation);
        axisLocation.setX(axisLocation.getX() + 1);
        axisLocation.setZ(axisLocation.getZ() - 3);
        for (double i = cloneZ; i <= cloneZ + 6; i++) {
            axisLocation.setZ(i);
            axisLocation.getBlock().setMetadata(Strings.META_FLAG_UNBREAKABLE, new FixedMetadataValue(this.getInstance(), false));
            b.add(new MachineBlock(this, axisLocation.getBlock(), Material.COAL_BLOCK, false, true, true));
        }
        MachineBuilder.restoreOrigin(origin, axisLocation);
        axisLocation.setZ(axisLocation.getZ() + 3);
        axisLocation.getBlock().setMetadata(Strings.META_FLAG_UNBREAKABLE, new FixedMetadataValue(this.getInstance(), false));
        b.add(new MachineBlock(this, axisLocation.getBlock(), Material.GOLD_BLOCK, true, false, true));
        MachineBuilder.restoreOrigin(origin, axisLocation);
        axisLocation.setZ(axisLocation.getZ() - 3);
        axisLocation.getBlock().setMetadata(Strings.META_FLAG_UNBREAKABLE, new FixedMetadataValue(this.getInstance(), false));
        b.add(new MachineBlock(this, axisLocation.getBlock(), Material.GOLD_BLOCK, true, false, true));

        return b;
    }
}
