package org.fishbits.commanditems.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.fishbits.commanditems.Main;

public class Give {

	public boolean give(String[] args, CommandSender s) {
		
		FileConfiguration config = Main.getInstance().getConfig();
		
		if (args.length >= 4) {
			
			Player p = Bukkit.getServer().getPlayer(args[1]);
			
			if (p == null) {
				
				s.sendMessage(Main.color(config.getString("messages.incorrectplayer")));
				return false;
				
			}
			
			int amount;
			
			try {
				
				amount = Integer.parseInt(args[3]);
				
			} catch (NumberFormatException e) {
				
				s.sendMessage(Main.color(config.getString("messages.incorrectnumber")));
				return false;
				
			}
			
			for (String key : config.getConfigurationSection("items").getKeys(false)) {
				
				if (key.contentEquals(args[2])) {
					
					ItemStack itemStack = new ItemStack(Material.getMaterial(config.getString("items." + key + ".item")));
					itemStack.setAmount(amount);
					ItemMeta meta = itemStack.getItemMeta();
					meta.setDisplayName(Main.color(config.getString("items." + key + ".name")));
					List<String> lore = new ArrayList<>();
					for (String line : config.getStringList("items." + key + ".lore")) {lore.add(Main.color(line));}
					meta.setLore(lore);
					itemStack.setItemMeta(meta);
					
					p.getInventory().addItem(itemStack);
					p.updateInventory();
					p.closeInventory();
					
					return true;
					
				}
				
			}
			
			s.sendMessage(Main.color(config.getString("messages.incorrectitem")));
			return false;
				
		} else {
			
			s.sendMessage(Main.color(config.getString("messages.usage").replaceAll("\\{usage}", "/commanditems give {player} {item} {amount}")));
			return false;
			
		}
		
	}
	
}
