package me.antoniomarroquin.hungergames;

import org.bukkit.plugin.java.JavaPlugin;

import me.antoniomarroquin.hungergames.commands.CommandsCommand;
import me.antoniomarroquin.hungergames.commands.PauseCommand;
import me.antoniomarroquin.hungergames.commands.RefillCommand;
import me.antoniomarroquin.hungergames.commands.ShrinkCommand;
import me.antoniomarroquin.hungergames.commands.StartCommand;
import me.antoniomarroquin.hungergames.commands.StopCommand;
import me.antoniomarroquin.hungergames.commands.TestCommand;
import me.antoniomarroquin.hungergames.commands.UnpauseCommand;
import me.antoniomarroquin.hungergames.events.PlayerDeadEvent;
import me.antoniomarroquin.hungergames.events.PlayerMoveOnPauseEvent;
import me.antoniomarroquin.hungergames.inventories.CommandSelectorInventory;

public class HungerGamesMain extends JavaPlugin
{
	public static boolean pause = false;
	public static boolean started = false;
	public static JavaPlugin plugin;
	
	@Override
	public void onEnable()
	{
		registerCommands();
		registerEvents();
		plugin = this;
	}
	
	@Override
	public void onDisable()
	{
		GameControl.stop();
	}
	
	public void registerCommands()
	{
		this.getCommand("refill").setExecutor(new RefillCommand());
		this.getCommand("shrink").setExecutor(new ShrinkCommand());
		this.getCommand("start").setExecutor(new StartCommand());
		this.getCommand("pause").setExecutor(new PauseCommand());
		this.getCommand("unpause").setExecutor(new UnpauseCommand());
		this.getCommand("stop").setExecutor(new StopCommand());
		
		this.getCommand("commands").setExecutor(new CommandsCommand());
		this.getCommand("test").setExecutor(new TestCommand());
	}
	
	public void registerEvents()
	{
		this.getServer().getPluginManager().registerEvents(new PlayerMoveOnPauseEvent(), this);
		this.getServer().getPluginManager().registerEvents(new CommandSelectorInventory(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerDeadEvent(), this);
	}
}
