package me.antoniomarroquin.hungergames.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import me.antoniomarroquin.hungergames.HungerGamesMain;

public class PlayerMoveOnPauseEvent implements Listener
{
	@EventHandler
	public void onPlayerMoveForPause(PlayerMoveEvent event)
	{
		if (HungerGamesMain.pause)
			if (!event.getPlayer().isOp())
				event.setCancelled(true);
	}
}
