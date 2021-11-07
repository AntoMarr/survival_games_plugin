package me.antoniomarroquin.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.antoniomarroquin.hungergames.inventories.CommandSelectorInventory;

public class CommandsCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (!sender.isOp())
			return true;
		if (!(sender instanceof Player))
			return true;
		Player player = (Player) sender;
		player.openInventory(CommandSelectorInventory.INVENTORY);
		return true;
	}

}