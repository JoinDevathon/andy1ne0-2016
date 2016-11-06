package me.andrewpetersen.devathonentry.util;

/*
 * This project has been written by Andrew Petersen, and anyone who has contributed to the source code
 * (or where otherwise declared). 
 *
 * This code is licensed under the GPLv3 License, a copy of which can be found in the root directory. 
 */

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * A static utility class, intended to be used in the creation of machines.
 */
public final class MachineBuilder {

    /**
     * Private constructor - this is a static utility class.
     */
    private MachineBuilder() {
    }

    /**
     * Create a machine at the said location.
     *
     * @param axisLocation The axis location.
     * @param pl
     */
    public static void buildMachine(Location axisLocation, Player pl) {

    }

    /**
     * Validates if the necessary structure has been built properly along the X axis.
     *
     * @param axisLocation The axis location for the build.
     * @return Whether the ground layout is a valid machine base or not.
     */
    public static boolean validateBuildX(Location axisLocation) {
        Location a = axisLocation.clone();
        return false;
    }

    /**
     * A method that simplifies restoring a manipulated location to its' original value.
     *
     * @param axis    The origin axis.
     * @param toReset The axis to be reset.
     */
    public static void RestoreOrigin(Location axis, Location toReset) {
        toReset.setX(axis.getX());
        toReset.setY(axis.getY());
        toReset.setZ(axis.getZ());
    }

    /**
     * Validates if the necessary structure has been built properly along the Z axis.
     *
     * @param axisLocation The axis location for the build.
     * @return Whether the ground layout is a valid machine base or not.
     */
    public static boolean validateBuildZ(Location axisLocation) {
        return false;
    }

}
