package org.fishbits.commanditems;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.fishbits.commanditems.createitems.CommandItem;

public class OnClick implements Listener {

	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		
		boolean correctHand;
		
			if (Main.isLegacy(Bukkit.getVersion()) || e.getHand().equals(EquipmentSlot.HAND)) {
				
				correctHand = true;
				
			} else {correctHand = false;}
			
		
		
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) 
				|| (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) 
						&& correctHand)) {

			CheckItem checkItem = new CheckItem();
			CommandItem commandItem = checkItem.checkItem(Main.getItemInHandUpdated(p));
			
			if (commandItem.commands != null) {
				
				e.setCancelled(true);
				
				if (commandItem.redeemable) {
					
					ItemStack itemStack = Main.getItemInHandUpdated(p);
					
					itemStack.setAmount(itemStack.getAmount() - 1);
					
					if (Main.isLegacy(Bukkit.getVersion())) {
						
						p.setItemInHand(itemStack);
						
					} else {
					
						p.getInventory().setItemInMainHand(itemStack);
					
					}
					
				}
				
				for (String command : commandItem.commands) {
					
					String[] commandArray = command.split(" ");
					
					if (commandArray[0].equalsIgnoreCase("player")) {

						p.performCommand(command.substring(7, command.length()));
						
					} else {
					
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Main.format(command, p));
					
					}
				}
				
			}

		}
		
	}
	
}
