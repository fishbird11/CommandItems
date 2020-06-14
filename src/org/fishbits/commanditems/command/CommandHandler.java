package org.fishbits.commanditems.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.fishbits.commanditems.Main;
import org.fishbits.commanditems.createitems.LoadItems;

public class CommandHandler implements CommandExecutor {

	public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
		
		if (s.hasPermission("commanditems.command")) {
		
			if (args.length >= 1) {
				
				if (args[0].contentEquals("iteminfo")) {
					
					if (s instanceof Player) {
						
						Player p = (Player) s;
						
						if (p.hasPermission("commanditems.iteminfo")) {
						
							ItemInfo itemInfo = new ItemInfo();
							itemInfo.itemInfo(p);
						
						} else {
							
							s.sendMessage(Main.color(Main.getInstance().getConfig().getString("messages.permissionerror")));
							
						}
					}
					
				} else if (args[0].contentEquals("give")) {
					
					if (s.hasPermission("commanditems.give")) {
					
						Give give = new Give();
						
						if(give.give(args, s)) {
							
							s.sendMessage(Main.color(Main.getInstance().getConfig().getString("messages.give"))
									.replaceAll("\\{player}", Bukkit.getServer().getPlayer(args[1]).getName())
									.replaceAll("\\{item}", args[2])
									.replaceAll("\\{amount}", args[3]));
							
						}
					
					} else {
						
						s.sendMessage(Main.color(Main.getInstance().getConfig().getString("messages.permissionerror")));
						
					}
					
				} else if (args[0].contentEquals("reload")) {
					
					if (s.hasPermission("commanditems.reload")) {
					
					Main.getInstance().reloadConfig();
					LoadItems loadItems = new LoadItems();
					Main.commandItems = loadItems.loadItems();
					s.sendMessage(ChatColor.GREEN + "Config file has been reloaded!");
					
					} else {
						
						s.sendMessage(Main.color(Main.getInstance().getConfig().getString("messages.permissionerror")));
						
					}
					
				} else if (args[0].contentEquals("list")) {
					
					String string = "";
					
					for (final String key : Main.getInstance().getConfig().getConfigurationSection("items").getKeys(false)) {
						
						string += string != "" ? ", " + key : key;
						
					}
					
					s.sendMessage(ChatColor.GOLD + string);
					
				} else {
					
					s.sendMessage(Main.color(Main.getInstance().getConfig().getString("messages.helpmenu")));
					
				}
				
			} else {
				
				s.sendMessage(Main.color(Main.getInstance().getConfig().getString("messages.helpmenu")));
				
			}
		
		} else {
			
			s.sendMessage(Main.color(Main.getInstance().getConfig().getString("messages.permissionerror")));
			
		}
			
		return true;
		
	}
	
}
