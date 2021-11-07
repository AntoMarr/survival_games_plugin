package me.antoniomarroquin.hungergames;

import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;

public class BorderControl 
{
	public static final int finalSize = 100;
	public static final int INITIAL_SIZE = 500;
	
	public static void resetBorder()
	{
		WorldBorder border = Bukkit.getServer().getWorld("world").getWorldBorder();
		border.setSize(INITIAL_SIZE);
	}
	
	public static void shrinkBorder()
	{
		WorldBorder border = Bukkit.getServer().getWorld("world").getWorldBorder();
		border.setSize(finalSize, (long) (border.getSize() - finalSize) * 2);
	}
	
	public static void freezeBorder()
	{
		WorldBorder border = Bukkit.getServer().getWorld("world").getWorldBorder();
		border.setSize(border.getSize());
	}
}
