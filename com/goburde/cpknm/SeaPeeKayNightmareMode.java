package com.goburde.cpknm;

import com.goburde.cpknm.events.PlayerEvents;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.*;

public class SeaPeeKayNightmareMode extends JavaPlugin {

    public Integer addConfusion = new Integer(-1);

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "[Nightmare]: Started up SeaPeeKay's personal nightmare!");
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                //check if we need to do any explosions
                // Check time, if night ensure player has nausea, if day ensure player does not have nausea
                Collection<? extends Player> players = getServer().getOnlinePlayers();
                if (!(players.isEmpty())) {
                    List<World> worldList = getServer().getWorlds();
                    for (World w : worldList) {
                        if (w.getEnvironment().equals(World.Environment.NORMAL)) {
                            Long time = w.getTime();
                            if (addConfusion.equals(new Integer(-1)) && time.compareTo(new Long(23000)) < 0){
                                Random confuseGen = new Random();
                                addConfusion = confuseGen.nextInt(3);
                            }
                            Long nighttime = new Long(17000);
                            int retVal = time.compareTo(nighttime);
                            if (time.compareTo(new Long(23000)) > 0) {
                                //take away nausea
                                addConfusion = new Integer(-1);
                                for (Player player : players) {
                                    player.removePotionEffect(PotionEffectType.CONFUSION);
                                }
                            } else if (retVal > 0) {
                                //give players nausea
                                if (addConfusion.equals(new Integer(1))) {
                                    for (Player player : players) {
                                        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 1, true, true, false));
                                    }
                                }
                            } else {
                                //take away nausea
                                for (Player player : players) {
                                    player.removePotionEffect(PotionEffectType.CONFUSION);
                                }
                            }
                        }
                    }
                    Random numberGen = new Random();
                    Integer randomNumber = numberGen.nextInt(300);
                    if (randomNumber.equals(new Integer(263))) { // Jord chose this
                        for (Player player : players) {
                            player.sendTitle("You blinked", "", 10, 70, 20);
                            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 10, true, true, false));
                        }
                    }
                    randomNumber = numberGen.nextInt(1800);
                    if (randomNumber.equals(new Integer(385))) { // Cal chose this
                        // Time to time travel
                        for (Player player : players) {
                            PlayerInventory inventory = player.getInventory();
                            ItemStack[] tempItems = inventory.getContents();
                            ArrayList<ItemStack> invItems = new ArrayList<ItemStack>();
                            for (ItemStack item : tempItems) {
                                if (item != null) {
                                    invItems.add(item);
                                }
                            }
                            if (invItems.size() > 2) {
                                player.sendTitle("TIME TRAVEL", "Check your inventory", 10, 70, 20);
                                HashMap<Integer, ? extends ItemStack> itemSearch;

                                itemSearch = inventory.all(Material.STONE_SWORD);
                                for (Integer i : itemSearch.keySet()) {
                                    inventory.setItem(i, new ItemStack(Material.WOODEN_SWORD));
                                }

                                itemSearch = inventory.all(Material.STONE_SHOVEL);
                                for (Integer i : itemSearch.keySet()) {
                                    inventory.setItem(i, new ItemStack(Material.WOODEN_SHOVEL));
                                }

                                itemSearch = inventory.all(Material.STONE_PICKAXE);
                                for (Integer i : itemSearch.keySet()) {
                                    inventory.setItem(i, new ItemStack(Material.WOODEN_PICKAXE));
                                }

                                itemSearch = inventory.all(Material.STONE_HOE);
                                for (Integer i : itemSearch.keySet()) {
                                    inventory.setItem(i, new ItemStack(Material.WOODEN_HOE));
                                }

                                itemSearch = inventory.all(Material.STONE_AXE);
                                for (Integer i : itemSearch.keySet()) {
                                    inventory.setItem(i, new ItemStack(Material.WOODEN_AXE));
                                }

                                itemSearch = inventory.all(Material.IRON_SWORD);
                                for (Integer i : itemSearch.keySet()) {
                                    inventory.setItem(i, new ItemStack(Material.STONE_SWORD));
                                }

                                itemSearch = inventory.all(Material.IRON_SHOVEL);
                                for (Integer i : itemSearch.keySet()) {
                                    inventory.setItem(i, new ItemStack(Material.STONE_SHOVEL));
                                }

                                itemSearch = inventory.all(Material.IRON_PICKAXE);
                                for (Integer i : itemSearch.keySet()) {
                                    inventory.setItem(i, new ItemStack(Material.STONE_PICKAXE));
                                }

                                itemSearch = inventory.all(Material.IRON_HOE);
                                for (Integer i : itemSearch.keySet()) {
                                    inventory.setItem(i, new ItemStack(Material.STONE_HOE));
                                }

                                itemSearch = inventory.all(Material.IRON_AXE);
                                for (Integer i : itemSearch.keySet()) {
                                    inventory.setItem(i, new ItemStack(Material.STONE_AXE));
                                }

                                itemSearch = inventory.all(Material.DIAMOND_SWORD);
                                for (Integer i : itemSearch.keySet()) {
                                    inventory.setItem(i, new ItemStack(Material.IRON_SWORD));
                                }

                                itemSearch = inventory.all(Material.DIAMOND_SHOVEL);
                                for (Integer i : itemSearch.keySet()) {
                                    inventory.setItem(i, new ItemStack(Material.IRON_SHOVEL));
                                }

                                itemSearch = inventory.all(Material.DIAMOND_PICKAXE);
                                for (Integer i : itemSearch.keySet()) {
                                    inventory.setItem(i, new ItemStack(Material.IRON_PICKAXE));
                                }

                                itemSearch = inventory.all(Material.DIAMOND_HOE);
                                for (Integer i : itemSearch.keySet()) {
                                    inventory.setItem(i, new ItemStack(Material.IRON_HOE));
                                }

                                itemSearch = inventory.all(Material.DIAMOND_AXE);
                                for (Integer i : itemSearch.keySet()) {
                                    inventory.setItem(i, new ItemStack(Material.IRON_AXE));
                                }
                                for (ItemStack armourPiece : inventory.getArmorContents()) {
                                    if (armourPiece != null) {
                                        if (armourPiece.getType().equals(Material.GOLDEN_BOOTS)) {
                                            inventory.setBoots(new ItemStack(Material.LEATHER_BOOTS));
                                        } else if (armourPiece.getType().equals(Material.GOLDEN_LEGGINGS)) {
                                            inventory.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
                                        } else if (armourPiece.getType().equals(Material.GOLDEN_CHESTPLATE)) {
                                            inventory.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
                                        } else if (armourPiece.getType().equals(Material.GOLDEN_HELMET)) {
                                            inventory.setHelmet(new ItemStack(Material.LEATHER_HELMET));
                                        } else if (armourPiece.getType().equals(Material.IRON_BOOTS)) {
                                            inventory.setBoots(new ItemStack(Material.GOLDEN_BOOTS));
                                        } else if (armourPiece.getType().equals(Material.IRON_LEGGINGS)) {
                                            inventory.setLeggings(new ItemStack(Material.GOLDEN_LEGGINGS));
                                        } else if (armourPiece.getType().equals(Material.IRON_CHESTPLATE)) {
                                            inventory.setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
                                        } else if (armourPiece.getType().equals(Material.IRON_HELMET)) {
                                            inventory.setHelmet(new ItemStack(Material.GOLDEN_HELMET));
                                        } else if (armourPiece.getType().equals(Material.DIAMOND_BOOTS)) {
                                            inventory.setBoots(new ItemStack(Material.IRON_BOOTS));
                                        } else if (armourPiece.getType().equals(Material.DIAMOND_LEGGINGS)) {
                                            inventory.setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                                        } else if (armourPiece.getType().equals(Material.DIAMOND_CHESTPLATE)) {
                                            inventory.setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                                        } else if (armourPiece.getType().equals(Material.DIAMOND_HELMET)) {
                                            inventory.setHelmet(new ItemStack(Material.IRON_HELMET));
                                        }
                                    }
                                }
                                // Choose 2 random items from player's inventory and delete them
                                tempItems = inventory.getContents();
                                invItems = new ArrayList<ItemStack>();
                                for (ItemStack item : tempItems) {
                                    if (item != null) {
                                        invItems.add(item);
                                    }
                                }
                                ItemStack toRemove = invItems.get(numberGen.nextInt(invItems.size()));
                                if (toRemove.getType().equals(Material.LEATHER_BOOTS) || toRemove.getType().equals(Material.GOLDEN_BOOTS) || toRemove.getType().equals(Material.IRON_BOOTS) || toRemove.getType().equals(Material.DIAMOND_BOOTS)) {
                                    if (inventory.getBoots() == null) {
                                        inventory.remove(toRemove);
                                    } else {
                                        inventory.setBoots(null);
                                    }
                                } else if (toRemove.getType().equals(Material.LEATHER_LEGGINGS) || toRemove.getType().equals(Material.GOLDEN_LEGGINGS) || toRemove.getType().equals(Material.IRON_LEGGINGS) || toRemove.getType().equals(Material.DIAMOND_LEGGINGS)) {
                                    if (inventory.getLeggings() == null) {
                                        inventory.remove(toRemove);
                                    } else {
                                        inventory.setLeggings(null);
                                    }
                                } else if (toRemove.getType().equals(Material.LEATHER_CHESTPLATE) || toRemove.getType().equals(Material.GOLDEN_CHESTPLATE) || toRemove.getType().equals(Material.IRON_CHESTPLATE) || toRemove.getType().equals(Material.DIAMOND_CHESTPLATE)) {
                                    if (inventory.getChestplate() == null) {
                                        inventory.remove(toRemove);
                                    } else {
                                        inventory.setChestplate(null);
                                    }
                                } else if (toRemove.getType().equals(Material.LEATHER_HELMET) || toRemove.getType().equals(Material.GOLDEN_HELMET) || toRemove.getType().equals(Material.IRON_HELMET) || toRemove.getType().equals(Material.DIAMOND_HELMET)) {
                                    if (inventory.getHelmet() == null) {
                                        inventory.remove(toRemove);
                                    } else {
                                        inventory.setHelmet(null);
                                    }
                                } else {
                                    inventory.remove(toRemove);
                                }
                                invItems.remove(toRemove);
                                toRemove = invItems.get(numberGen.nextInt(invItems.size()));
                                if (toRemove.getType().equals(Material.LEATHER_BOOTS) || toRemove.getType().equals(Material.GOLDEN_BOOTS) || toRemove.getType().equals(Material.IRON_BOOTS) || toRemove.getType().equals(Material.DIAMOND_BOOTS)) {
                                    if (inventory.getBoots() == null) {
                                        inventory.remove(toRemove);
                                    } else {
                                        inventory.setBoots(null);
                                    }
                                } else if (toRemove.getType().equals(Material.LEATHER_LEGGINGS) || toRemove.getType().equals(Material.GOLDEN_LEGGINGS) || toRemove.getType().equals(Material.IRON_LEGGINGS) || toRemove.getType().equals(Material.DIAMOND_LEGGINGS)) {
                                    if (inventory.getLeggings() == null) {
                                        inventory.remove(toRemove);
                                    } else {
                                        inventory.setLeggings(null);
                                    }
                                } else if (toRemove.getType().equals(Material.LEATHER_CHESTPLATE) || toRemove.getType().equals(Material.GOLDEN_CHESTPLATE) || toRemove.getType().equals(Material.IRON_CHESTPLATE) || toRemove.getType().equals(Material.DIAMOND_CHESTPLATE)) {
                                    if (inventory.getChestplate() == null) {
                                        inventory.remove(toRemove);
                                    } else {
                                        inventory.setChestplate(null);
                                    }
                                } else if (toRemove.getType().equals(Material.LEATHER_HELMET) || toRemove.getType().equals(Material.GOLDEN_HELMET) || toRemove.getType().equals(Material.IRON_HELMET) || toRemove.getType().equals(Material.DIAMOND_HELMET)) {
                                    if (inventory.getHelmet() == null) {
                                        inventory.remove(toRemove);
                                    } else {
                                        inventory.setHelmet(null);
                                    }
                                } else {
                                    inventory.remove(toRemove);
                                }
                            }
                        }
                    }
                }
            }
        }, 0L, 20L);



        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Collection<? extends Player> players = getServer().getOnlinePlayers();
                if (!(players.isEmpty())) {
                    while (PlayerEvents.explosions.size() > 0) {
                        for (Player player : players) {
                            if (PlayerEvents.explosions.get(0).getBlock().getType().equals(Material.OBSIDIAN)) {
                                player.getWorld().createExplosion(PlayerEvents.explosions.get(0), 4F, false);
                            }
                            PlayerEvents.explosions.remove(0);
                        }
                    }
                }
            }
        }, 0L, 2L);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "[Nightmare]: Giving up are we?");
    }

}
