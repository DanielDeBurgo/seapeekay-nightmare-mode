package com.goburde.cpknm.events;

import java.util.*;

import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.material.Colorable;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.bukkit.event.weather.*;

import static org.bukkit.Bukkit.getServer;

public class PlayerEvents implements Listener {

    public Material forgotten = Material.AIR;
    public static ArrayList<Location> explosions = new ArrayList<Location>();

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.getDisplayName() == "Seapeekay") {
            player.sendMessage(ChatColor.DARK_RED + "Welcome to your personal nightmare Callum");
        }
        else {
            player.sendMessage(ChatColor.DARK_RED + "Welcome to Seapeekay's nightmare " + player.getDisplayName());
        }
    }

    @EventHandler
    public static void onPlayerWalk(PlayerMoveEvent event) {
        Random numberGen = new Random();
        Player player = event.getPlayer();
        if (!(event.getFrom().getBlock().equals(event.getTo().getBlock()))) {
            Integer randomNumber = numberGen.nextInt(35);
            int x = player.getLocation().getBlockX();
            int y = player.getLocation().getBlockY();
            int z = player.getLocation().getBlockZ();

            Block block = player.getWorld().getBlockAt(x, y - 1, z);
            if (randomNumber.equals(new Integer(24))) { // Faye chose this one
                if (!(block.isPassable()) && !(block.isEmpty()) && !(block.isLiquid())) {
                    block.setType(Material.GRAVEL, true);
                }
            }
            if (block.getType().equals(Material.GRAVEL) && (player.getInventory().getBoots() == null)) {
                player.damage(1.5);
            }
        }
        List<Entity> near = event.getPlayer().getNearbyEntities(2,2,2);
        for (Entity e : near) {
            if (e.getType().equals((EntityType.FOX))) {
                player.sendTitle("FOX ATTACK", "", 0, 20, 0);
                event.getPlayer().damage(6);
            }
        }
        Material m = event.getPlayer().getLocation().getBlock().getType();
        if (m == Material.WATER) {
            Integer randomNumber = numberGen.nextInt(500); // 500
            if (randomNumber.equals(388)) { // Sofa chose this (388)
                PlayerInventory inventory = player.getInventory();
                ItemStack[] tempItems = inventory.getContents();
                ArrayList<ItemStack> invItems = new ArrayList<ItemStack>();
                for (ItemStack item : tempItems) {
                    if (item != null) {
                        invItems.add(item);
                    }
                }
                if (invItems.size() > 1) {
                    ItemStack toRemove = invItems.get(numberGen.nextInt(invItems.size()));
                    if (toRemove.getType().equals(Material.LEATHER_BOOTS) || toRemove.getType().equals(Material.GOLDEN_BOOTS) || toRemove.getType().equals(Material.IRON_BOOTS) || toRemove.getType().equals(Material.DIAMOND_BOOTS)) {
                        if (inventory.getBoots() == null){
                            inventory.remove(toRemove);
                        }
                        else {
                            inventory.setBoots(null);
                        }
                    }
                    else if (toRemove.getType().equals(Material.LEATHER_LEGGINGS) || toRemove.getType().equals(Material.GOLDEN_LEGGINGS) || toRemove.getType().equals(Material.IRON_LEGGINGS) || toRemove.getType().equals(Material.DIAMOND_LEGGINGS)) {
                        if (inventory.getLeggings() == null){
                            inventory.remove(toRemove);
                        }
                        else {
                            inventory.setLeggings(null);
                        }
                    }
                    else if (toRemove.getType().equals(Material.LEATHER_CHESTPLATE) || toRemove.getType().equals(Material.GOLDEN_CHESTPLATE) || toRemove.getType().equals(Material.IRON_CHESTPLATE) || toRemove.getType().equals(Material.DIAMOND_CHESTPLATE)) {
                        if (inventory.getChestplate() == null){
                            inventory.remove(toRemove);
                        }
                        else {
                            inventory.setChestplate(null);
                        }
                    }
                    else if (toRemove.getType().equals(Material.LEATHER_HELMET) || toRemove.getType().equals(Material.GOLDEN_HELMET) || toRemove.getType().equals(Material.IRON_HELMET) || toRemove.getType().equals(Material.DIAMOND_HELMET)) {
                        if (inventory.getHelmet() == null){
                            inventory.remove(toRemove);
                        }
                        else {
                            inventory.setHelmet(null);
                        }
                    }
                    else {
                        inventory.remove(toRemove);
                    }
                    player.getWorld().dropItem(player.getLocation(), toRemove);
                }
            }
        }
    }

    @EventHandler
    public static void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Random numberGen = new Random();
        Integer randomNumber = numberGen.nextInt(10);
        if (randomNumber.equals(new Integer((6)))) { // Georgie chose this one
            Entity damager = event.getDamager();
            if (damager.getType().equals(EntityType.PLAYER)) {
                Location loc = event.getEntity().getLocation();
                World world = event.getEntity().getWorld();
                if (!(event.getEntity().getType().equals(EntityType.ENDER_DRAGON))) {
                    event.getEntity().remove();
                    world.spawnEntity(loc, EntityType.FOX);
                }
            }
        }
    }

    @EventHandler
    public static void onPlayerPortal(PlayerPortalEvent event) {
        Player player = event.getPlayer();
        if (!(player.getInventory().contains(Material.ORANGE_TERRACOTTA))) {
            player.sendTitle("Nice try", "Get some orange terracotta", 10, 70, 20);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public static void onLeftClickBlock(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            if (block.getType().equals(Material.OBSIDIAN)) {
                Random numberGen = new Random();
                Integer randomNumber = numberGen.nextInt(4);
                if (randomNumber.equals(new Integer(2))){ // Sam chose this one
                    Location loc = block.getLocation();
                    block.setType(Material.AIR);
                    event.getPlayer().getWorld().dropItemNaturally(loc, new ItemStack(Material.OBSIDIAN));
                }
                else {
                    event.getPlayer().getWorld().createExplosion(block.getLocation(), 4F, false);
                }
            }
        }
        if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR) {
            Random numberGen = new Random();
            Integer randomNumber = numberGen.nextInt(50);
            if (randomNumber.equals(new Integer(12))) { // MJ chose this one
                event.getPlayer().setVelocity(new Vector(1, 0, 1).multiply(new Vector(numberGen.nextDouble() * 5, numberGen.nextDouble() * 2, numberGen.nextDouble() * 5)));
            }

        }
    }

    @EventHandler
    public static void OnPlayerEat(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack food = event.getItem();
        if (!(food.getType().equals(Material.BEEF)) && !(food.getType().equals(Material.COOKED_BEEF) && !(food.getType().equals(Material.MILK_BUCKET) && !(food.getType().equals(Material.POTION))))) {
            Long time = event.getPlayer().getWorld().getTime();
            Long midday = new Long(6000);
            int retVal = time.compareTo(midday);
            if (retVal > 0) {
                player.sendTitle("But it's steak time", "", 10, 70, 20);
                event.setCancelled(true);
            }
        }
        if (food.getType().equals(Material.COOKED_BEEF)) {
            event.getPlayer().setFoodLevel(event.getPlayer().getFoodLevel() - 4);
        }
    }

    @EventHandler
    public void onDamage (EntityDamageEvent event) {
        if(!(event.getEntity() instanceof Player)) {
            return;
        }


        Player player = (Player) event.getEntity();

        Damageable damag = player;
        double health = damag.getHealth();
        if (!(health > 5)) {
            player.getWorld().spawnEntity(player.getLocation().add(1,0,0), EntityType.WITHER);
        }
    }

    @EventHandler
    public static void onSprint(PlayerToggleSprintEvent event){
        Player player = event.getPlayer();
        if (player.isSprinting()){
            Random numberGen = new Random();
            Integer randomNumber = numberGen.nextInt(25);
            if (randomNumber.equals(1)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100, 1, true, true, false));
            }
        }
    }

    @EventHandler
    public void onCraft (CraftItemEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Random numberGen = new Random();
            Player player = Bukkit.getPlayer(event.getWhoClicked().getName());
            //Check if they've already forgotten something
            if (forgotten.equals(Material.AIR)){
                // They have not forgotten something, check if they need to forget this item with random roll
                Integer randomNumber = numberGen.nextInt(7);
                if (randomNumber.equals(new Integer(6))) { // My favourite person Sarah chose this
                    //They need to forget this item and I need to cancel this craft
                    player.sendTitle("You've forgotten how to craft this", "Craft something else and try again", 10, 70, 20);
                    forgotten = event.getInventory().getResult().getType();
                    event.setCancelled(true);
                }

            }
            else {
                //They have forgotten something, check if they're trying to craft that thing
                if (forgotten.equals(event.getInventory().getResult().getType())) {
                    // The sneaky devil is trying to craft something they've forgotten, let's let them know and give them a sneaky bit of damage
                    player.sendTitle("You're trying to craft something you've forgotten", "This hurts your head", 10, 70, 20);
                    player.damage(4);
                    event.setCancelled(true);
                }
                else {
                    // They're trying to craft something else, let's check if they need to forget this one. If not we can free them of their forgetfulness
                    Integer randomNumber = numberGen.nextInt(7);
                    if (randomNumber.equals(new Integer(4))){ // Amy chose this one
                        player.sendTitle("You've forgotten", "Craft something else and try again", 10, 70, 20);
                        forgotten = event.getInventory().getResult().getType();
                        event.setCancelled(true);
                    }
                    else {
                        forgotten = Material.AIR;
                    }
                }
            }
        }
    }

    //FIX THIS
//    @EventHandler
//    public static void onWeatherChange(WeatherChangeEvent event){
//        if (!(event.toWeatherState())){
//            //Rain is over time to check if we TP (IMPLEMENT THIS LATER IT'S CERTAIN FOR TESTING)
//
//            List<World> worldList = getServer().getWorlds();
//            Random numberGen = new Random();
//            for (World w : worldList) {
//                if (w.getEnvironment().equals(World.Environment.NETHER)) {
//                    // We need to pass this world in to our thing
//                    Location safePlace = w.getHighestBlockAt(0, 0).getLocation();
//                    boolean foundSafePlace = false;
//                    while (!(foundSafePlace)){
//                        Integer max = new Integer(1000);
//                        Integer min = new Integer(-1000);
//                        Integer randomX = numberGen.nextInt((max - min) + 1) + min;
//                        Integer randomZ =  numberGen.nextInt((max - min) + 1) + min;
//                        Integer Y = w.getHighestBlockYAt(randomX, randomZ);
//                        Location XYZ = new Location(w, randomX, Y, randomZ);
//                        Location Down1XYZ = new Location(w, randomX, Y.intValue() - 1, randomZ);
//                        Location Down2XYZ = new Location(w, randomX, Y.intValue() - 2, randomZ);
//                        getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + Y.toString());
//                        while (Y.intValue() != 0 && !(XYZ.getBlock().getType().equals(Material.AIR) && Down1XYZ.getBlock().getType().equals(Material.AIR) && !(Down2XYZ.getBlock().isLiquid()) && !(Down2XYZ.getBlock().isEmpty()) && !(Down2XYZ.getBlock().getType().equals(Material.BEDROCK)))) {
//                            getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Coords ("+XYZ.getX()+","+Y.toString()+","+XYZ.getZ()+") FAIL");
//                            safePlace = new Location(w, randomX, Y, randomZ);
//                            Y = Y - 1;
//                        }
//                        if (Y.intValue() != 0){
//                            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Coords ("+XYZ.getX()+","+Y.toString()+","+XYZ.getZ()+") PASS");
//                            foundSafePlace = true;
//                        }
//                    }
//                    //teleport the player to spacePlace
//                    Collection<? extends Player> players = getServer().getOnlinePlayers();
//                    for (Player player : players) {
//                        player.teleport(safePlace);
//                    }
//                }
//            }
//        }
//    }

    @EventHandler
    public static void OnPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        World world = player.getWorld();
        for (Entity entity : world.getEntities()){
            if (entity.getType().equals(EntityType.WITHER)){
                entity.remove();
            }
        }
    }

   @EventHandler
    public static void OnEntitySpawn(EntitySpawnEvent event) {
        Random random = new Random();
        List<String> mods = new ArrayList<String>();
        mods.add("Gnome");
        mods.add("Fae");
        mods.add("MJ");
        mods.add("VanMan");
        mods.add("Georgie");
        mods.add("Goat");
        mods.add("Sam");
        mods.add("Sam_the_muffin_man");
        mods.add("Ammmmmy");
        mods.add("Amy");
        mods.add("Novasobored");
        mods.add("Nova");
        mods.add("GoBurDe");
        mods.add("Dan");
        mods.add("TheSpacePenguin");
        mods.add("Space");
        if (event.getEntity() instanceof  Monster){
            event.getEntity().setCustomName(mods.get(random.nextInt(mods.size())));
            event.getEntity().setCustomNameVisible(true);
        }
   }

   @EventHandler
    public static void onBlockForm(BlockFormEvent event){
        explosions.add(event.getBlock().getLocation());
   }
}
