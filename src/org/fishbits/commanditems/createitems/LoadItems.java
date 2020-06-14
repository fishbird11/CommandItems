package org.fishbits.commanditems.createitems;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.fishbits.commanditems.Main;

public class LoadItems {

	public List<CommandItem> loadItems() {
		
		Main main = Main.getInstance();
		FileConfiguration config = main.getConfig();
		List<CommandItem> commandItems = new ArrayList<>();
		
		for (String key : config.getConfigurationSection("items").getKeys(false)) {
			
			Material material = Material.matchMaterial(config.getString("items." + key + ".item"));
			String name = Main.color(config.getString("items." + key + ".name"));
			List<String> lore = new ArrayList<>();
			for (String line : config.getStringList("items." + key + ".lore")) {lore.add(Main.color(line));}
			List<String> commands = config.getStringList("items." + key + ".commands");
			boolean redeemable = config.getBoolean("items." + key + ".redeemable");
			
			CreateItem createItem = new CreateItem();
			
			commandItems.add(createItem.createItem(material, name, lore, commands, redeemable));
			
		}
		
		return commandItems;
		
	}
	
}
