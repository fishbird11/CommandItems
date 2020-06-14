package org.fishbits.commanditems.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.fishbits.commanditems.Main;

public class ItemInfo {

	public void itemInfo(Player p) {
		
		ItemStack itemStack;
		
		if (Bukkit.getVersion().contains("1.7") ||
				Bukkit.getVersion().contains("1.8") ||
				Bukkit.getVersion().contains("1.9") ||
				Bukkit.getVersion().contains("1.10") ||
				Bukkit.getVersion().contains("1.11") ||
				Bukkit.getVersion().contains("1.12")) {
			
			itemStack = p.getItemInHand();
			
		} else {
			
			itemStack = p.getInventory().getItemInMainHand();
			
		}
		
		String item = itemStack.getType().name();
		String name = new String();
		try {name = itemStack.getItemMeta().getDisplayName();} catch (NullPointerException e) {name = " ";}
		List<String> lore = new ArrayList<>();
		try {lore = itemStack.getItemMeta().getLore();} catch (NullPointerException e) {name = " ";}
	
		p.sendMessage(" ");
		p.sendMessage(Main.color("&6Item: " + item));
		p.sendMessage(Main.color("&6Name: &f" + name));
		p.sendMessage(Main.color("&6Lore: "));
		
		if (lore != null) {
			
			for (String s : lore) {
				
				p.sendMessage(Main.color("&6  - &5" + s));
				
			}
			
		}	
		
		p.sendMessage(" ");
		
	}
	
}
