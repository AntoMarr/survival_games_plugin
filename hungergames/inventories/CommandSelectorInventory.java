package me.antoniomarroquin.hungergames.inventories;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.antoniomarroquin.hungergames.GameControl;
import me.antoniomarroquin.hungergames.refillables.RefillableChest;

public class CommandSelectorInventory implements Listener
{
	
	public static Inventory INVENTORY = Bukkit.createInventory(null, 9, "Select Command:");
	
	static
	{
		Inventory inv = INVENTORY;
		createDisplay(Material.BARRIER, inv, 0, ChatColor.RED + "Exit", "Exit the menu.");
		createDisplay(Material.GREEN_CONCRETE, inv, 2, ChatColor.GREEN + "Start", "Start the game.");
		createDisplay(Material.RED_CONCRETE, inv, 3, ChatColor.DARK_RED + "Stop", "Stop the game.");
		createDisplay(Material.ICE, inv, 5, ChatColor.BLUE + "Pause", "Pause the game for all the players.");
		createDisplay(Material.MAGMA_BLOCK, inv, 6, ChatColor.GOLD + "Unpause", "Unpause the game for all players.");
		createDisplay(Material.CHEST, inv, 8, ChatColor.YELLOW + "Refill", "Refill the chests.");
	}
	
	public static void createDisplay(Material material, Inventory inv, int Slot, String name, String lore)
	{
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(lore);
		meta.setLore(Lore);
		item.setItemMeta(meta);
		 
		inv.setItem(Slot, item); 
		 
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) 
	{
		Player player = (Player) event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		if (clicked == null)
			return;
		Inventory inventory = event.getInventory();
		if (inventory.equals(INVENTORY))
		{
			if (clicked.getType() == Material.BARRIER)
			{
				event.setCancelled(true);
				player.closeInventory();
			}
			if (clicked.getType() == Material.GREEN_CONCRETE)
			{
				event.setCancelled(true);
				GameControl.start(player);
				player.closeInventory();
			}
			if (clicked.getType() == Material.RED_CONCRETE)
			{
				event.setCancelled(true);
				GameControl.stop();
				player.closeInventory();
			}
			if (clicked.getType() == Material.ICE)
			{
				event.setCancelled(true);
				GameControl.pause();
				player.closeInventory();
			}
			if (clicked.getType() == Material.CHEST)
			{
				event.setCancelled(true);
				RefillableChest.refillAll();
				player.closeInventory();
			}
			if (clicked.getType() == Material.MAGMA_BLOCK)
			{
				event.setCancelled(true);
				GameControl.unpause();
				player.closeInventory();
			}
//			if (clicked.getType() == Material.PLAYER_HEAD)
//			{
//				event.setCancelled(true);
//				player.closeInventory();
//			}
		}
	}
	
//	public static void renameMode(Player player)
//	{
//		player.sendMessage(ChatColor.DARK_GRAY + "===================================");
//		player.sendMessage(ChatColor.DARK_GRAY + "=" + ChatColor.DARK_RED + "         YOU ARE IN RENAME MODE          " + ChatColor.DARK_GRAY + "=");
//		player.sendMessage(ChatColor.DARK_GRAY + "=" + ChatColor.DARK_RED + "            YOUR NEXT MESSAGE             " + ChatColor.DARK_GRAY + "=");
//		player.sendMessage(ChatColor.DARK_GRAY + "=" + ChatColor.DARK_RED + "    WILL SET THE NAME FOR THE TEAM    " + ChatColor.DARK_GRAY + "=");
//		player.sendMessage(ChatColor.DARK_GRAY + "===================================");
//	}
	
}