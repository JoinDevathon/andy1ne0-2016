package me.theyellowcreepz.devathonentry.api;

/*
 * This project has been written by Yellow, and anyone who has contributed to the source code
 * (or where otherwise declared). 
 *
 * This code is licensed under the GPLv3 License, a copy of which can be found in the root directory. 
 */

import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * The wrapper class for any blocks that need to be generated when creating a machine.
 */
public class MachineBlock {

    private MachineLevel masterLevel;

    private Block block;

    private boolean explodeEffect = false;

    private boolean smokeEffect = false;

    private boolean sparkleEffect = false;

    private Material material = Material.AIR;

    /**
     * The default constructor for this class, used to set various variables.
     *
     * @param master The master MachineLevel instance.
     * @param block The block that this class manipulates.
     * @param m The material this block will be set to.
     */
    public MachineBlock(MachineLevel master, Block block, Material m) {
        this.setMasterLevel(master);
        this.setBlock(block);
        this.setMaterial(m);
    }

    /**
     * The constructor used, should any generic effects be needed. Note that sound effects should be handled in the master machine level class.
     *
     * @param master        The master MachineLevel instance.
     * @param block         The block that this class manipulates.
     * @param m             The material this block will be set to.
     * @param explodeEffect Whether an explosion effect should be used post-creation of this block.
     * @param smokeEffect   Whether a smoke effect should be used post-creation of this block.
     * @param sparkleEffect Whether a sparkle effect should be used post-creation of this block.
     */
    public MachineBlock(MachineLevel master, Block block, Material m, boolean explodeEffect, boolean smokeEffect, boolean sparkleEffect) {
        this.setMasterLevel(master);
        this.setBlock(block);
        this.setMaterial(m);
        this.setExplodeEffect(explodeEffect);
        this.setSmokeEffect(smokeEffect);
        this.setSparkleEffect(sparkleEffect);
    }

    public MachineLevel getMasterLevel() {
        return this.masterLevel;
    }

    public void setMasterLevel(MachineLevel masterLevel) {
        this.masterLevel = masterLevel;
    }

    public Block getBlock() {
        return this.block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public boolean isExplodeEffect() {
        return this.explodeEffect;
    }

    public void setExplodeEffect(boolean explodeEffect) {
        this.explodeEffect = explodeEffect;
    }

    public boolean isSmokeEffect() {
        return this.smokeEffect;
    }

    public void setSmokeEffect(boolean smokeEffect) {
        this.smokeEffect = smokeEffect;
    }

    public boolean isSparkleEffect() {
        return this.sparkleEffect;
    }

    public void setSparkleEffect(boolean sparkleEffect) {
        this.sparkleEffect = sparkleEffect;
    }

    public Material getMaterial() {
        return this.material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
