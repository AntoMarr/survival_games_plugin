package me.antoniomarroquin.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.antoniomarroquin.hungergames.SpawnControl;

public class TestCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		SpawnControl.createSpawn();
		return true;
	}

}