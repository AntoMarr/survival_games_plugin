package me.antoniomarroquin.hungergames;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import me.antoniomarroquin.hungergames.refillables.RefillableChest;

public class SpawnControl 
{
	public static final ArrayList<Location> SPAWN_LOCATIONS = new ArrayList<>();
	public static final int SPAWN_SIZE = 25;
	
	public static void createSpawn()
	{
		World world = Bukkit.getWorld("world");
		
		Location middle = world.getHighestBlockAt(0, 0).getLocation();
		if (middle.getBlock().getType().equals(Material.ENCHANTING_TABLE))
			middle.subtract(0, 4, 0);
		else
			middle.add(0, 1, 0);
		double y = middle.getY();
		
		for (int i = 0; i < world.getMaxHeight(); i++)
		{
			for (int j = 0; j < SPAWN_SIZE; j++)
			{
				makeCircle(middle, SPAWN_SIZE - j, Material.AIR);
			}
			middle.add(0, 1, 0);
		}
		
		middle.setY(y);
		
		for (int i = 0; i < SPAWN_SIZE; i++)
		{
			makeCircle(middle, SPAWN_SIZE - i, Material.STONE);
		}
		
		middle.setY(++y);
		
		for (int i = 0; i < 3; i++)
		{
			middle.getBlock().setType(Material.GLOWSTONE);
			middle.add(0, 1, 0);
		}
		middle.getBlock().setType(Material.ENCHANTING_TABLE);
		middle.subtract(0, 1, 0);
		middle.getBlock().setType(Material.CRAFTING_TABLE);
		
		middle.setY(y);
		
		middle.add(1, 0, 1);
		for (int i = 0; i < 2; i++)
		{
			middle.getBlock().setType(Material.GLOWSTONE);
			middle.add(0, 1, 0);
		}
		middle.setY(y);
		
		middle.subtract(2, 0, 0);
		for (int i = 0; i < 2; i++)
		{
			middle.getBlock().setType(Material.GLOWSTONE);
			middle.add(0, 1, 0);
		}
		middle.setY(y);
		
		middle.subtract(0, 0, 2);
		for (int i = 0; i < 2; i++)
		{
			middle.getBlock().setType(Material.GLOWSTONE);
			middle.add(0, 1, 0);
		}
		middle.setY(y);
		
		middle.add(2, 0, 0);
		for (int i = 0; i < 2; i++)
		{
			middle.getBlock().setType(Material.GLOWSTONE);
			middle.add(0, 1, 0);
		}
		middle.setY(y);
		
		middle.setX(0);
		middle.setZ(0);
		
		middle.add(2, 0, 2);
		middle.getBlock().setType(Material.GLOWSTONE);
		middle.setY(y);
		
		middle.subtract(0, 0, 4);
		middle.getBlock().setType(Material.GLOWSTONE);
		middle.setY(y);
		
		middle.subtract(4, 0, 0);
		middle.getBlock().setType(Material.GLOWSTONE);
		middle.setY(y);
		
		middle.add(0, 0, 4);
		middle.getBlock().setType(Material.GLOWSTONE);
		middle.setY(y);
		
		new RefillableChest(1, 1);
		new RefillableChest(1, -1);
		new RefillableChest(-1, -1);
		new RefillableChest(-1, 1);
		
		new RefillableChest(2, 2);
		new RefillableChest(2, -2);
		new RefillableChest(-2, -2);
		new RefillableChest(-2, 2);
		
		addSpawnLocation(0, 12);
		addSpawnLocation(0, -12);
		addSpawnLocation(12, 0);
		addSpawnLocation(-12, 0);
		
		addSpawnLocation(8, 8);
		addSpawnLocation(8, -8);
		addSpawnLocation(-8, 8);
		addSpawnLocation(-8, -8);
	}
	
	public static void makeCircle(Location loc, Integer r, Material m)
	{
        int x;
        int y = loc.getBlockY();
        int z;
               
        for (double i = 0.0; i < 360.0; i += 0.1)
        {
        	double angle = i * Math.PI / 180;
            x = (int)(loc.getX() + r * Math.cos(angle));
            z = (int)(loc.getZ() + r * Math.sin(angle));
            
            Bukkit.getServer().getWorld("world").getBlockAt(x, y, z).setType(m);
        }
    }
	
	public static void addSpawnLocation(int x, int y)
	{
		Location location = Bukkit.getWorld("world").getHighestBlockAt(x, y).getLocation().add(0, 1, 0);
		location.getBlock().setType(Material.GOLD_BLOCK);
		SPAWN_LOCATIONS.add(location.add(0, 1, 0));
	}
	
	public static void spawnPlayers()
	{
		int i = 0;
		for (Player p : Bukkit.getServer().getOnlinePlayers())
		{
			if (!p.isOp())
			{
				Vector temp = p.getLocation().getDirection();
				p.teleport(SPAWN_LOCATIONS.get(i++));
				p.getLocation().setDirection(temp);
				GameControl.ALIVE_PLAYERS.add(p);
			}
		}
	}
}
