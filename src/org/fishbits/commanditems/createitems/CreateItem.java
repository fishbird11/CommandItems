package org.fishbits.commanditems.createitems;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CreateItem {
	
	public CommandItem createItem(Material material, String name, List<String> lore, List<String> commands, Boolean redeemable) {
		
		CommandItem item = new CommandItem();
		ItemStack itemStack = new ItemStack(material);
		ItemMeta meta = itemStack.getItemMeta();
		
		meta.setDisplayName(name);
		meta.setLore(lore);
		itemStack.setItemMeta(meta);
		
		item.commands = commands;
		item.redeemable = redeemable;
		item.itemStack = itemStack;
		
		return item;
		
	}
	
}
