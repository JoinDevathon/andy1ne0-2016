package me.andrewpetersen.devathonentry.listeners;

import me.andrewpetersen.devathonentry.DevathonPlugin;
import me.andrewpetersen.devathonentry.Strings;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_10_R1.RecipesFurnace;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_10_R1.inventory.CraftItemStack;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

/*
 * This project has been written by Andrew Petersen, and anyone who has contributed to the source code
 * (or where otherwise declared). 
 *
 * This code is licensed under the GPLv3 License, a copy of which can be found in the root directory. 
 */
public class ListenerInsertFurnaceItem implements Listener {

    private DevathonPlugin instance;

    public ListenerInsertFurnaceItem(DevathonPlugin inst) {
        this.setInstance(inst);
    }

    public DevathonPlugin getInstance() {
        return instance;
    }

    public void setInstance(DevathonPlugin instance) {
        this.instance = instance;
    }

    @EventHandler
    public void preventHarambeFoodMovement(InventoryClickEvent evt) {
        if (evt.getCurrentItem() == null) return;
        if (evt.getCurrentItem().getItemMeta() == null) return;
        if (evt.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        if (evt.getCurrentItem().getItemMeta().getDisplayName().equals(Strings.HARAMBE_FOOD_TITLE)) {
            evt.setCancelled(true);
            evt.getWhoClicked().sendMessage(Strings.HARAMBE_DISAPPROVAL_MESSAGE);
        }
    }

    @EventHandler
    public void onFurnaceInteract(InventoryClickEvent evt) {
        if (evt.getClickedInventory() == null) return;
        if (evt.getCurrentItem() == null) return;
        if (evt.getCurrentItem().getItemMeta() == null) return;
        if (evt.getClickedInventory().getName() == null) return;
        if (evt.getInventory() == null) return;
        if (evt.getInventory().getName() == null) return;

        if (evt.getInventory().getName().equals(Strings.ENDER_CHEST_TITLE)) {
            Player pl = (Player) evt.getWhoClicked();
            ItemStack i = evt.getCurrentItem();

            if (RecipesFurnace.getInstance().getRecipes().entrySet().stream().filter(e -> CraftItemStack.asBukkitCopy(e.getKey()).getType() == i.getType()).findFirst().isPresent()) {
                net.minecraft.server.v1_10_R1.ItemStack resultPre = RecipesFurnace.getInstance().getRecipes().entrySet().stream().filter(e -> CraftItemStack.asBukkitCopy(e.getKey()).getType() == i.getType()).findFirst().get().getValue();
                ItemStack result = CraftItemStack.asBukkitCopy(resultPre);
                pl.getInventory().remove(i);
                if (this.getInstance().registry.containsKey(pl)) {
                    Sign s = this.getInstance().registry.get(pl);
                    pl.closeInventory();
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (pl.getWorld().getTime() > 15000) {
                                this.cancel();
                            } else {
                                pl.getWorld().setTime(pl.getWorld().getTime() + 150);
                            }
                        }
                    }.runTaskTimer(this.getInstance(), 1l, 1l);

                    pl.sendMessage(Strings.HARAMBE_PREPARING_MEAL_MESSAGE);
                    org.bukkit.World w = pl.getWorld();
                    Location loc = s.getLocation();
                    loc.setY(loc.getY() + 3);
                    loc.setZ(loc.getZ() - 2);
                    pl.playSound(loc, Sound.ENTITY_TNT_PRIMED, 5, 5);
                    Item it = w.dropItem(loc, i);
                    it.setGlowing(true);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            it.setVelocity(new Vector(0, 1.2, 0));

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    it.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, it.getLocation(), 5);
                                    for (int i = 10; i <= 360; i = i + 10) {
                                        double rads = Math.toRadians(i);
                                        double x = Math.cos(rads) * 15;
                                        double z = Math.sin(rads) * 15;
                                        Location temp = it.getLocation().clone();
                                        temp.add(x, 0, z);
                                        Fireball f = (Fireball) it.getWorld().spawnEntity(temp, EntityType.FIREBALL);
                                        f.setDirection(new Vector(x * 5, -2, z * 2));
                                    }
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            it.setVelocity(new Vector(0, 0.9, -0.8));
                                            it.getWorld().spawnParticle(Particle.TOWN_AURA, it.getLocation(), 5);
                                            it.getWorld().spawnParticle(Particle.SPELL_WITCH, it.getLocation(), 5);
                                            it.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, it.getLocation(), 5);
                                            for (int t = 0; t < 3; t++)
                                                it.getWorld().playSound(it.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);

                                            new BukkitRunnable() {
                                                @Override
                                                public void run() {
                                                    Spider spoderman = (Spider) it.getWorld().spawnEntity(it.getLocation(), EntityType.SPIDER);
                                                    spoderman.setGlowing(true);
                                                    Bat bat = (Bat) it.getWorld().spawnEntity(it.getLocation(), EntityType.BAT);
                                                    bat.setGlowing(true);
                                                    Skeleton mrSkele = (Skeleton) it.getWorld().spawnEntity(it.getLocation(), EntityType.SKELETON);
                                                    mrSkele.setGlowing(true);
                                                    mrSkele.setSkeletonType(Skeleton.SkeletonType.WITHER);
                                                    mrSkele.setAI(false);
                                                    bat.setAI(false);
                                                    bat.setCustomName(ChatColor.GOLD + "" + ChatColor.BOLD + "Harambe Jr. ");
                                                    bat.setCustomNameVisible(true);

                                                    spoderman.setPassenger(mrSkele);
                                                    mrSkele.setPassenger(bat);
                                                    it.remove();
                                                    i.setType(result.getType());
                                                    pl.sendMessage(Strings.HARAMBE_BRINGING_ITEM_MESSAGE);
                                                    spoderman.setTarget(pl);
                                                    spoderman.setInvulnerable(true);
                                                    new BukkitRunnable() {
                                                        @Override
                                                        public void run() {
                                                            if (spoderman.getLocation().distance(pl.getLocation()) < 3) {
                                                                spoderman.setInvulnerable(false);
                                                                spoderman.damage(100);
                                                                bat.damage(100);
                                                                mrSkele.damage(100);
                                                                pl.getWorld().dropItem(spoderman.getLocation(), i);
                                                                pl.sendMessage(Strings.HARAMBE_JOB_FINISHED);
                                                                pl.playSound(pl.getLocation(), Sound.ENTITY_ENDERDRAGON_FIREBALL_EXPLODE, 5, 5);
                                                                pl.sendMessage(Strings.HARAMBE_DEAD_MESSAGE);
                                                                new BukkitRunnable() {
                                                                    int t = 0;

                                                                    @Override
                                                                    public void run() {
                                                                        if (this.t >= 5) {
                                                                            this.cancel();
                                                                        }
                                                                        for (int i = 5; i <= 360; i = i + 5) {
                                                                            double rads = Math.toRadians(i);
                                                                            double x = Math.cos(rads) * 15;
                                                                            double z = Math.sin(rads) * 15;
                                                                            Location locTemp = pl.getLocation().clone();
                                                                            locTemp.add(x, 0, z);
                                                                            pl.getWorld().strikeLightning(locTemp);
                                                                        }
                                                                        t++;
                                                                    }
                                                                }.runTaskTimer(getInstance(), 10l, 10l);
                                                                this.cancel();
                                                            }
                                                        }
                                                    }.runTaskTimer(getInstance(), 5l, 5l);

                                                }
                                            }.runTaskLater(getInstance(), 30l);
                                        }
                                    }.runTaskLater(getInstance(), 30l);
                                    it.getWorld().getPlayers().forEach(pt -> pt.playSound(it.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 5, 5));
                                }
                            }.runTaskLater(getInstance(), 40l);

                        }
                    }.runTaskLater(this.getInstance(), 40l);

                } else {
                    pl.closeInventory();
                    throw new NullPointerException("Player " + pl.getName() + " opened a Harambe chest and clicked an item, but no sign could be found in the registry. ");
                }

            } else {
                pl.sendMessage(Strings.INVALID_ITEMSTACK_ERROR);
            }
        }

    }

}
