package me.antoniomarroquin.hungergames.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.antoniomarroquin.hungergames.GameControl;

public class PlayerDeadEvent implements Listener
{
	@EventHandler
	public void onDead(PlayerDeathEvent event)
	{
		for (Player p : Bukkit.getOnlinePlayers())
		{
			p.playSound(p.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
		}
		GameControl.ALIVE_PLAYERS.remove(event.getEntity());
		if (GameControl.ALIVE_PLAYERS.size() == 1)
		{
			GameControl.winner(GameControl.ALIVE_PLAYERS.get(0));
		}
		else if (GameControl.ALIVE_PLAYERS.size() == 0)
		{
			GameControl.winner();
		}
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event)
	{
		Player player = event.getPlayer();
		player.setGameMode(GameMode.SPECTATOR);
		player.sendMessage("You are now spectating...");
	}
}
