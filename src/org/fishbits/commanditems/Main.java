package org.fishbits.commanditems;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.fishbits.commanditems.command.CommandHandler;
import org.fishbits.commanditems.createitems.CommandItem;
import org.fishbits.commanditems.createitems.LoadItems;

import me.clip.placeholderapi.PlaceholderAPI;

public class Main extends JavaPlugin {

	public static List<CommandItem> commandItems;
	private static Main instance;
	
	public void onEnable() {
		
		getServer().getPluginManager().registerEvents(new OnClick(), this);
		
		getCommand("commanditems").setExecutor(new CommandHandler());
		
		instance = this;
		
		saveDefaultConfig();
		
		LoadItems loadItems = new LoadItems();
		commandItems = loadItems.loadItems();
		
	}
	
	public static Main getInstance() {
		
		return instance;
		
	}
	
	public static String format(String s, Player p) {
		
		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
			
			s = PlaceholderAPI.setPlaceholders(p, s);
			
		}
		
		s = s.replaceAll("\\{player}", p.getName());
		
		return color(s);
		
	}
	
	public static String color(String s) {
		
		return ChatColor.translateAlternateColorCodes('&', s);
		
	}
	
	public static ItemStack getItemInHandUpdated(Player p) {
		
		if (isLegacy(Bukkit.getVersion())) {
			
			return p.getItemInHand();
			
		} else {
			
			return p.getInventory().getItemInMainHand();
			
		}
		
	}
	
	public static boolean isLegacy(String version) {
		
		if (Bukkit.getVersion().contains("1.7") ||
				Bukkit.getVersion().contains("1.8") ||
				Bukkit.getVersion().contains("1.9") ||
				Bukkit.getVersion().contains("1.10") ||
				Bukkit.getVersion().contains("1.11") ||
				Bukkit.getVersion().contains("1.12")) {
		
			return true;
			
		} else {
			
			return false;
			
		}
			
	}
	
}