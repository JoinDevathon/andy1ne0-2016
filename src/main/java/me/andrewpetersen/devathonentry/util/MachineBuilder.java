package me.andrewpetersen.devathonentry.util;

/*
 * This project has been written by Andrew Petersen, and anyone who has contributed to the source code
 * (or where otherwise declared). 
 *
 * This code is licensed under the GPLv3 License, a copy of which can be found in the root directory. 
 */

import me.andrewpetersen.devathonentry.DevathonPlugin;
import me.andrewpetersen.devathonentry.Strings;
import me.andrewpetersen.devathonentry.api.MachineBlock;
import me.andrewpetersen.devathonentry.api.MachineLevel;
import me.andrewpetersen.devathonentry.levels.FirstMachineLevel;
import me.andrewpetersen.devathonentry.levels.SecondMachineLevel;
import me.andrewpetersen.devathonentry.levels.ThirdMachineLevel;
import org.bukkit.*;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

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
     * @param pl The player who is having their machine built.
     */
    public static void buildMachine(Location axisLocation, final Player pl) {
        pl.sendMessage(Strings.CREATING_MACHINE_MESSAGE);
        pl.playSound(pl.getLocation(), Sound.ENTITY_BOBBER_THROW, 5, 5);

        ArrayList<MachineBlock> blocks = new ArrayList<>();

        ArrayList<MachineLevel> levels = new ArrayList<>();

        levels.add(new FirstMachineLevel(DevathonPlugin.getInstance()));
        levels.add(new SecondMachineLevel(DevathonPlugin.getInstance()));
        levels.add(new ThirdMachineLevel(DevathonPlugin.getInstance()));

        levels.stream().forEachOrdered((s) -> blocks.addAll(s.getBlocks(axisLocation.clone())));

        new BukkitRunnable() {
            @Override
            public void run() {
                if (blocks.size() == 0) {
                    this.cancel();

                    Location axis = axisLocation.clone();
                    axis.setZ(axis.getZ() + 4);
                    axis.getBlock().setType(Material.ENDER_CHEST);
                    axis.setY(axis.getY() - 2);
                    axis.getBlock().setType(Material.SIGN_POST);
                    Sign s = (Sign) axis.getBlock().getState();
                    s.setLine(0, Strings.SIGN_MACHINE_PREFIX_INTERNAL);
                    if (pl.isOnline()) {
                        pl.sendMessage(Strings.FINISHED_MACHINE_BUILD_MESSAGE);
                        pl.sendMessage(Strings.FINISHED_MACHINE_INSTRUCTIONS_MESSAGE);
                    }
                } else {
                    MachineBlock bl = blocks.get(0);
                    bl.getBlock().setType(bl.getMaterial());
                    if (bl.isExplodeEffect()) {
                        bl.getBlock().getLocation().getWorld().getPlayers().stream().filter(pl -> pl.getLocation().distance(bl.getBlock().getLocation()) <= 20).forEach(pl -> {
                            pl.playEffect(bl.getBlock().getLocation(), Effect.EXPLOSION_LARGE, null);
                            pl.playSound(bl.getBlock().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 5, 5);
                        });
                    }
                    if (bl.isSmokeEffect()) {
                        bl.getBlock().getLocation().getWorld().getPlayers().stream().filter(pl -> pl.getLocation().distance(bl.getBlock().getLocation()) <= 20).forEach(pl -> {
                            pl.playEffect(bl.getBlock().getLocation(), Effect.SMOKE, 5);
                            pl.playSound(bl.getBlock().getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 5, 5);
                        });
                    }
                    if (bl.isSparkleEffect()) {
                        bl.getBlock().getWorld().spawnParticle(Particle.CRIT_MAGIC, bl.getBlock().getLocation(), 5);
                        bl.getBlock().getWorld().spawnParticle(Particle.SPELL_INSTANT, bl.getBlock().getLocation(), 5);
                        pl.playSound(bl.getBlock().getLocation(), Sound.BLOCK_SAND_FALL, 5, 5);
                    }
                    blocks.remove(bl);
                }
            }
        }.runTaskTimer(DevathonPlugin.getInstance(), 10l, 10l);

    }

    /**
     * Validates if the necessary structure has been built properly along the X axis.
     *
     * @param axisLocation The axis location for the build.
     * @return Whether the ground layout is a valid machine base or not.
     */
    public static boolean validateBuildX(Location axisLocation) {
        Location a = axisLocation.clone();
        a.setX(a.getX() + 1);
        a.setZ(a.getZ() - 3);
        double cloneZ = a.getZ();
        for (double i = cloneZ; i <= cloneZ + 6; i++) {
            a.setZ(i);
            if (a.getBlock() == null) {
                return false;
            } else if (a.getBlock().getType() != Material.COBBLESTONE) {
                return false;
            }
        }
        restoreOrigin(axisLocation, a);
        a.setX(a.getX() - 1);
        a.setZ(a.getZ() - 3);
        cloneZ = a.getZ();
        for (double i = cloneZ; i <= cloneZ + 6; i++) {
            a.setZ(i);
            if (a.getBlock() == null) {
                return false;
            } else if (a.getBlock().getType() != Material.COBBLESTONE) {
                return false;
            }
        }
        restoreOrigin(axisLocation, a);
        a.setZ(a.getZ() + 3);
        if (a.getBlock() == null) {
            return false;
        } else if (a.getBlock().getType() != Material.COBBLESTONE) {
            return false;
        }
        restoreOrigin(axisLocation, a);
        a.setZ(a.getZ() - 3);
        if (a.getBlock() == null) {
            return false;
        } else if (a.getBlock().getType() != Material.COBBLESTONE) {
            return false;
        }
        return true;
    }

    /**
     * A method that simplifies restoring a manipulated location to its' original value.
     *
     * @param axis    The origin axis.
     * @param toReset The axis to be reset.
     */
    public static void restoreOrigin(Location axis, Location toReset) {
        toReset.setX(axis.getX());
        toReset.setY(axis.getY());
        toReset.setZ(axis.getZ());
    }

    /*
     * Validates if the necessary structure has been built properly along the Z axis.
     *
     * @param axisLocation The axis location for the build.
     * @return Whether the ground layout is a valid machine base or not.
     */
    /*public static boolean validateBuildZ(Location axisLocation) {
        Location a = axisLocation.clone();
        a.setZ(a.getZ() + 1);
        a.setX(a.getX() - 3);
        double cloneX = a.getX();
        for (double i = cloneX; i <= cloneX + 6; i++) {
            a.setX(i);
            if (a.getBlock() == null) {
                return false;
            } else if (a.getBlock().getType() != Material.COBBLESTONE) {
                return false;
            }
        }
        restoreOrigin(axisLocation, a);
        a.setZ(a.getZ() - 1);
        a.setX(a.getX() - 3);
        cloneX = a.getX();
        for (double i = cloneX; i <= cloneX + 6; i++) {
            a.setX(i);
            if (a.getBlock() == null) {
                return false;
            } else if (a.getBlock().getType() != Material.COBBLESTONE) {
                return false;
            }
        }
        restoreOrigin(axisLocation, a);
        a.setX(a.getX() + 3);
        if (a.getBlock() == null) {
            return false;
        } else if (a.getBlock().getType() != Material.COBBLESTONE) {
            return false;
        }
        restoreOrigin(axisLocation, a);
        a.setX(a.getX() - 3);
        if (a.getBlock() == null) {
            return false;
        } else if (a.getBlock().getType() != Material.COBBLESTONE) {
            return false;
        }
        return true;
    }*/

}
