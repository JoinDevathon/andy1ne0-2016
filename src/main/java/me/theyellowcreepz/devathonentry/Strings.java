package me.theyellowcreepz.devathonentry;

/*
 * This project has been written by Yellow, and anyone who has contributed to the source code
 * (or where otherwise declared). 
 *
 * This code is licensed under the GPLv3 License, a copy of which can be found in the root directory. 
 */

import net.md_5.bungee.api.ChatColor;

/**
 * This is the class where any strings that should be used will be stored.
 */
public class Strings {

    public static final String FAILED_MACHINE_CREATION = ChatColor.GRAY + "" + ChatColor.ITALIC + "Ye didn't make the machine properly. Harambe is disappointed...";
    public static final String META_FLAG_UNBREAKABLE = "devathon_breakable";
    public static final String CREATING_MACHINE_MESSAGE = ChatColor.GOLD + "" + ChatColor.ITALIC + "Ye hath summoned the legacy of thy lord Harambe... ";
    public static final String FINISHED_MACHINE_BUILD_MESSAGE = ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Thou hath awoken Harambe. ";
    public static final String SIGN_MACHINE_PREFIX_INTERNAL = ChatColor.GREEN + "Regist";
    public static final String FINISHED_MACHINE_INSTRUCTIONS_MESSAGE = ChatColor.GREEN + "" + ChatColor.ITALIC + "Right-click on the ender chest to add items to the furnace! ";
    public static final String ENDER_CHEST_TITLE = ChatColor.DARK_GREEN + "Add items to cook!";
    public static final String HARAMBE_FOOD_TITLE = ChatColor.GREEN + "Harambe's Gorilla Food (plants)";
    public static final String FURNACE_MENU_INSTRUCTION = ChatColor.AQUA + "" + ChatColor.ITALIC + "Insert what you would like to cook... ";
    public static final String INVALID_ITEMSTACK_ERROR = ChatColor.RED + "" + ChatColor.ITALIC + "That's not a cookable item... Harambe wouldn't like to eat that... ";
    public static final String HARAMBE_DISAPPROVAL_MESSAGE = ChatColor.RED + "" + ChatColor.ITALIC + "Harambe is disappointed you tried to take his food... ";
    public static final String HARAMBE_PREPARING_MEAL_MESSAGE = ChatColor.GOLD + "" + ChatColor.ITALIC + "Thy one and powerful Harambe is now using his powers to give you a tender meal... Sit tight. ";
    public static final String HARAMBE_BRINGING_ITEM_MESSAGE = ChatColor.GOLD + "" + ChatColor.ITALIC + "*Harambe calls for his son to bring you your tender meal...*";
    public static final String HARAMBE_JOB_FINISHED = ChatColor.AQUA + "Thy overlord Harambe-dawg hath given thy tender meal. ";
    public static final String HARAMBE_DEAD_MESSAGE = ChatColor.GRAY + "" + ChatColor.ITALIC + "Oh no! You shot the gorilla! Rip. ";
    static final String VERBOSE_PREFIX = "[VERBOSE] ";

    /**
     * Private constructor - static utility class.
     */
    private Strings() {
    }

}
