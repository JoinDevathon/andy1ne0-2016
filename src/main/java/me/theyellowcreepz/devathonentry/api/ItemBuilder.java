package me.theyellowcreepz.devathonentry.api;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/*
 * This project has been written by Yellow, and anyone who has contributed to the source code
 * (or where otherwise declared). 
 *
 * This code is licensed under the GPLv3 License, a copy of which can be found in the root directory. 
 */

/**
 * An item builder class, intended to simplify the process of creating item stacks.
 */
public class ItemBuilder {
    private String title = "Item";
    private int quantity = 1;
    private boolean customBytes = false;
    private DyeColor dyeColor = DyeColor.BLACK;
    private Material material = Material.AIR;
    private LinkedHashMap<Enchantment, Integer> enchantments = new LinkedHashMap<>();
    private ArrayList<String> lore = new ArrayList<>();

    /**
     * Public constructor - no logic performed here.
     */
    public ItemBuilder() {

    }

    /**
     * Sets the quantity for this item stack.
     *
     * @param i The quantity to set it to.
     * @return This instance.
     */
    public ItemBuilder setQuantity(int i) {
        this.quantity = i;
        return this;
    }

    /**
     * Add a title to the item stack.
     *
     * @param title The item name.
     * @return This instance.
     */
    public ItemBuilder addTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Set the dye color for this item stack, if applicable.
     *
     * @param d The dye color.
     * @return This instance.
     */
    public ItemBuilder setDye(DyeColor d) {
        this.dyeColor = d;
        this.customBytes = true;
        return this;
    }

    /**
     * Set the material for this item.
     *
     * @param m The material to set this item to.
     * @return This instance.
     */
    public ItemBuilder setMaterial(Material m) {
        this.material = m;
        return this;
    }

    /**
     * Add a string lore to this itemstack.
     *
     * @param s The lore string.
     * @return This instance.
     */
    public ItemBuilder withLore(String s) {
        this.lore.add(s);
        return this;
    }

    /**
     * Add an enchantment to this item.
     *
     * @param e     The enchantment type.
     * @param level The enchantment level.
     * @return This instance.
     */
    public ItemBuilder withEnchantment(Enchantment e, int level) {
        this.enchantments.put(e, level);
        return this;
    }

    /**
     * Builds this itemstack instance.
     *
     * @return The built Item Stack.
     */
    public ItemStack build() {
        ItemStack toExport;
        if (customBytes) {
            toExport = new ItemStack(material, quantity, dyeColor.getData());
        } else {
            toExport = new ItemStack(material, quantity);
        }
        ItemMeta meta = toExport.getItemMeta();
        meta.setDisplayName(title);
        meta.setLore(lore);
        toExport.setItemMeta(meta);
        for (Enchantment e : enchantments.keySet()) {
            toExport.addUnsafeEnchantment(e, enchantments.get(e));
        }
        return toExport;
    }

}