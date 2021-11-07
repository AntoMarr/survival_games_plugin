package me.antoniomarroquin.hungergames.runnables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.antoniomarroquin.hungergames.HungerGamesMain;

public class Postcountdown implements Runnable
{
	
	@Override
	public void run()
	{
		for (Player p : Bukkit.getOnlinePlayers())
		{
			p.sendTitle(ChatColor.GREEN + "Go Go Go!", "",10,100,10);
			HungerGamesMain.pause = false;
		}
		
	}

}