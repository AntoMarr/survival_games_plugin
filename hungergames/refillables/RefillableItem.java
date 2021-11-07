package me.antoniomarroquin.hungergames.refillables;

import java.util.Random;

import org.bukkit.Material;

public class RefillableItem
{
	/* multiply chance by amount to get the total number of items;
	 * This means that a golden apple with a chance of 0.1 and amount 1 will only drop 0.1 on average
	 */
	private int chance;
	private Material material;
	private int amount;
	
	public RefillableItem(Material material, int chance, int amount)
	{
		this.material = material;
		this.chance = chance;
		this.amount = amount;
	}
	
	private boolean roll()
	{
		if (new Random().nextInt(100) <= chance)
			return true;
		return false;
	}
	
	public int getAmount()
	{
		if (roll())
			return new Random().nextInt(amount) + 1;
		return 0;
	}
	
	public Material getMaterial()
	{
		return material;
	}
}
