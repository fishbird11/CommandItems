package org.fishbits.commanditems;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.fishbits.commanditems.createitems.CommandItem;

public class CheckItem {

	public CommandItem checkItem(ItemStack itemStack) {
		
		for (CommandItem commandItem : Main.commandItems) {
			
			if (!(itemStack.getType().equals(Material.AIR))) {
				
				if (isSameItem(itemStack, commandItem.itemStack)) {
					
					return commandItem;
					
				}
			
			}
			
		}
		
		return new CommandItem();
		
	}
	
	private boolean isSameItem(ItemStack a, ItemStack b) {
		
		ItemMeta metaA = a.getItemMeta();
		ItemMeta metaB = b.getItemMeta();
		
		try {
		
			if (!(metaA.getDisplayName().equals(metaB.getDisplayName()))) {
				
				return false;
				
			}

		} catch (NullPointerException e) {
			
			if (!(!(metaA.hasDisplayName()) && !(metaB.hasDisplayName()))) {
				
				return false;
				
			}
			
		}
		
		if (!(a.getType().equals(b.getType()))) {
			
			return false;
			
		}

		if ((!(metaA.hasLore()) && !(metaB.hasLore())
				|| metaA.getLore().equals(metaB.getLore()))) {
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
	
}
