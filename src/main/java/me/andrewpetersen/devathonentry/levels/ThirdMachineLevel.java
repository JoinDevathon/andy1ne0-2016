package me.andrewpetersen.devathonentry.levels;

import me.andrewpetersen.devathonentry.DevathonPlugin;
import me.andrewpetersen.devathonentry.Strings;
import me.andrewpetersen.devathonentry.api.MachineBlock;
import me.andrewpetersen.devathonentry.api.MachineLevel;
import me.andrewpetersen.devathonentry.util.MachineBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.metadata.FixedMetadataValue;

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

        ArrayList<MachineBlock> b = new ArrayList<>();
        axisLocation.setY(axisLocation.getY() + 3);
        Location origin = axisLocation.clone();
        axisLocation.setX(axisLocation.getX() - 1);
        axisLocation.setZ(axisLocation.getZ() - 3);
        double cloneZ = axisLocation.getZ();
        for (double i = cloneZ; i <= cloneZ + 6; i++) {
            axisLocation.setZ(i);
            axisLocation.getBlock().setMetadata(Strings.META_FLAG_UNBREAKABLE, new FixedMetadataValue(this.getInstance(), false));
            b.add(new MachineBlock(this, axisLocation.getBlock(), Material.LAPIS_BLOCK, false, true, true));
        }
        MachineBuilder.restoreOrigin(origin, axisLocation);
        axisLocation.setX(axisLocation.getX() + 1);
        axisLocation.setZ(axisLocation.getZ() - 3);
        for (double i = cloneZ; i <= cloneZ + 6; i++) {
            axisLocation.setZ(i);
            axisLocation.getBlock().setMetadata(Strings.META_FLAG_UNBREAKABLE, new FixedMetadataValue(this.getInstance(), false));
            b.add(new MachineBlock(this, axisLocation.getBlock(), Material.LAPIS_BLOCK, false, true, true));
        }
        MachineBuilder.restoreOrigin(origin, axisLocation);
        axisLocation.setZ(axisLocation.getZ() + 3);
        axisLocation.getBlock().setMetadata(Strings.META_FLAG_UNBREAKABLE, new FixedMetadataValue(this.getInstance(), false));
        b.add(new MachineBlock(this, axisLocation.getBlock(), Material.MOSSY_COBBLESTONE, true, true, true));
        MachineBuilder.restoreOrigin(origin, axisLocation);
        axisLocation.setZ(axisLocation.getZ() - 3);
        axisLocation.getBlock().setMetadata(Strings.META_FLAG_UNBREAKABLE, new FixedMetadataValue(this.getInstance(), false));
        b.add(new MachineBlock(this, axisLocation.getBlock(), Material.MOSSY_COBBLESTONE, true, true, true));

        return b;
    }
}
