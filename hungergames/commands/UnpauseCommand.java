package me.antoniomarroquin.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.antoniomarroquin.hungergames.GameControl;

public class UnpauseCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (!sender.isOp())
			return true;
		GameControl.unpause();
		return true;
	}

}