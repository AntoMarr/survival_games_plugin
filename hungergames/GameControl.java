package me.antoniomarroquin.hungergames;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import me.antoniomarroquin.hungergames.refillables.RefillableChest;
import me.antoniomarroquin.hungergames.runnables.CountdownTimer;
import me.antoniomarroquin.hungergames.runnables.Postcountdown;
import me.antoniomarroquin.hungergames.runnables.Precountdown;

public class GameControl 
{
	public static final ArrayList<Player> ALIVE_PLAYERS = new ArrayList<>();
	
	public static void start(CommandSender sender)
	{
		stop();
		for (Entity e : Bukkit.getServer().getWorld("world").getEntities())
		{
			if (!(e instanceof Player))
			{
				e.remove();
			}
		}
		Random random = new Random();
		for (int i = 0; i < RefillableChest.NUM_OF_CHESTS; i++)
		{
			int x = random.nextInt(BorderControl.INITIAL_SIZE) - (BorderControl.INITIAL_SIZE / 2);
			int y = random.nextInt(BorderControl.INITIAL_SIZE) - (BorderControl.INITIAL_SIZE / 2);
			new RefillableChest(x, y);
		}
		SpawnControl.createSpawn();
		gameRules();
		
		HungerGamesMain.started = true;
		sender.sendMessage(ChatColor.GREEN + "Game has started...");
		BorderControl.resetBorder();
		BorderControl.shrinkBorder();
		sender.sendMessage(ChatColor.GREEN + "Border is now shrinking...");
		SpawnControl.spawnPlayers();
		sender.sendMessage(ChatColor.GREEN + "Players are in ready position...");
		RefillableChest.refillAll();
		sender.sendMessage(ChatColor.GREEN + "All chests have been refilled...");
		
		new CountdownTimer(HungerGamesMain.plugin, 20, new Precountdown(), new Postcountdown(), 
		(t) -> 
		{
			for (Player p : Bukkit.getOnlinePlayers())
	        	p.sendTitle(ChatColor.GREEN + "The Game Is Starting In...", t.getSecondsLeft() + "",0,100,0);
		}).scheduleTimer();
		
		Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "" + ChatColor.GREEN + "The game has started!");
	}
	
	public static void stop()
	{
		RefillableChest.deleteAll();
		HungerGamesMain.started = false;
		BorderControl.resetBorder();
		Bukkit.getServer().broadcastMessage(ChatColor.RED + "The game has been stopped...");
		for (Player p : Bukkit.getServer().getOnlinePlayers())
			p.setGameMode(GameMode.SPECTATOR);
	}
	
	public static void pause()
	{
		HungerGamesMain.pause = true;
		BorderControl.freezeBorder();
		Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "The game has been paused...");
	}
	
	public static void unpause()
	{
		HungerGamesMain.pause = false;
		BorderControl.shrinkBorder();
		Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "The game has been unpaused...");
	}
	
	public static void gameRules()
	{
		World world = Bukkit.getServer().getWorld("world");
		world.setDifficulty(Difficulty.NORMAL);
		world.setTime(1000);
		for (Player p : Bukkit.getOnlinePlayers())
		{
			p.setGameMode(GameMode.ADVENTURE);
			p.getInventory().clear();
			p.setHealth(20);
			p.setFoodLevel(20);
			p.setSaturation(5);
			p.setTotalExperience(0);
		}
	}
	
	public static void winner(Player player)
	{
		stop();
		Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + player.getName() + " has won the game!");
	}
	
	public static void winner()
	{
		stop();
		Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Nobody won!");
	}
}
