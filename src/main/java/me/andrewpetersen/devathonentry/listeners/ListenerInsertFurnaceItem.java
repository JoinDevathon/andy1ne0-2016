package me.andrewpetersen.devathonentry.listeners;

import me.andrewpetersen.devathonentry.DevathonPlugin;
import me.andrewpetersen.devathonentry.Strings;
import net.minecraft.server.v1_10_R1.RecipesFurnace;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_10_R1.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
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
                i.setType(result.getType()); // TODO move this elsewhere.
                if (this.getInstance().registry.containsKey(pl)) {
                    Sign s = this.getInstance().registry.get(pl);
                    pl.closeInventory();
                    pl.sendMessage(Strings.HARAMBE_PREPARING_MEAL_MESSAGE);
                    org.bukkit.World w = pl.getWorld();
                    Location loc = s.getLocation();
                    loc.setY(loc.getY() + 3);
                    loc.setZ(loc.getZ() - 2);
                    pl.playSound(loc, Sound.ENTITY_TNT_PRIMED, 5, 5);
                    Item it = w.dropItem(loc, i);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            it.setVelocity(new Vector(0, 1.2, 0));

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    it.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, it.getLocation(), 5);
                                    for (int i = 30; i <= 360; i = i + 30) {
                                        double rads = Math.toRadians(i);
                                        double x = Math.cos(rads) * 3;
                                        double z = Math.sin(rads) * 3;
                                        Location temp = it.getLocation().clone();
                                        temp.add(x, 0, z);
                                        Fireball f = (Fireball) it.getWorld().spawnEntity(temp, EntityType.FIREBALL);
                                        f.setDirection(new Vector(x * 5, -2, z * 2));
                                    }
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            it.setVelocity(new Vector(0, 0.9, -1.0));
                                            it.getWorld().spawnParticle(Particle.TOWN_AURA, it.getLocation(), 5);
                                            it.getWorld().spawnParticle(Particle.SPELL_WITCH, it.getLocation(), 5);
                                            it.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, it.getLocation(), 5);
                                            for (int t = 0; t < 3; t++)
                                                it.getWorld().playSound(it.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);
                                            new BukkitRunnable() {
                                                @Override
                                                public void run() {

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
