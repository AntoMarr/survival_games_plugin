package me.antoniomarroquin.hungergames.runnables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.antoniomarroquin.hungergames.HungerGamesMain;

public class Precountdown implements Runnable
{
	
	@Override
	public void run()
	{
		for (Player p : Bukkit.getOnlinePlayers())
		{
			p.sendTitle(ChatColor.GREEN + "The game is starting!", "",0,20,0);
			HungerGamesMain.pause = true;
		}
		
	}

}
