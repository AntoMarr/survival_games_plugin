package me.antoniomarroquin.hungergames.refillables;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class RefillableChest 
{
	public static final HashSet<RefillableItem> REFILLABLE_ITEMS = new HashSet<>();
	public static final HashSet<Location> CHESTS = new HashSet<>();
	
	public static final int NUM_OF_CHESTS = 50;
	
	static 
	{
		REFILLABLE_ITEMS.add(new RefillableItem(Material.ARROW, 80, 10));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.BOW, 25, 1));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.GOLDEN_APPLE, 10, 1));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.DIAMOND, 5, 1));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.APPLE, 80, 5));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.BREAD, 60, 7));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.COOKED_BEEF, 25, 4));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.EXPERIENCE_BOTTLE, 10, 3));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.WOODEN_SWORD, 50, 1));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.STONE_SWORD, 25, 1));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.IRON_SWORD, 10, 1));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.LEATHER_HELMET, 25, 1));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.LEATHER_CHESTPLATE, 25, 1));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.LEATHER_LEGGINGS, 25, 1));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.LEATHER_BOOTS, 25, 1));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.CHAINMAIL_HELMET, 10, 1));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.CHAINMAIL_CHESTPLATE, 10, 1));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.CHAINMAIL_LEGGINGS, 10, 1));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.CHAINMAIL_BOOTS, 10, 1));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.IRON_HELMET, 5, 1));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.IRON_CHESTPLATE, 5, 1));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.IRON_LEGGINGS, 5, 1));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.IRON_BOOTS, 5, 1));
		REFILLABLE_ITEMS.add(new RefillableItem(Material.LAPIS_LAZULI, 2, 1));
		
	};
	
	public RefillableChest(int x, int z)
	{
		Location location = Bukkit.getWorld("world").getHighestBlockAt(x, z).getLocation();
		location.add(0, 1, 0);
		location.getBlock().setType(Material.CHEST);
		CHESTS.add(location);
	}
	
	public static void deleteAll()
	{
		for (Location location : CHESTS)
		{
			location.getBlock().setType(Material.AIR);
		}
	}
	
	public static void refillAll()
	{
		Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "All chests have been refilled.");
		for (Location loc : CHESTS)
		{
			if (loc.getBlock().getState() instanceof Chest)
			{
				Chest chest = (Chest) loc.getBlock().getState();
				refill(chest);
			}
			else
			{
				System.err.println(loc.toString() + " is not a chest location.");
			}
		}
	}
	
	public static void refill(Chest chest)
	{
		chest.getInventory().clear();
		REFILLABLE_ITEMS.forEach((i) -> 
		{
			chest.getInventory().addItem(new ItemStack(i.getMaterial(), i.getAmount()));
		});
	}
}
