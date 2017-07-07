package com.yaadanial.lachasseauxblocks.plugin;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class LCABPluginListener implements Listener {

	private LCABPlugin p = null;

	public LCABPluginListener(LCABPlugin p) {
		this.p = p;
	}
	
	@EventHandler
	public void onBlockBreakEvent(final BlockBreakEvent ev) {
		if (this.p.isGameRunning() && p.getAltar().contains(ev.getBlock())) {
			ev.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlaceEvent(final BlockPlaceEvent ev) {
		boolean isOnTheAltar = false;
		if (this.p.isGameRunning() && p.getAltar().contains(ev.getBlock())) {
			for (Block block : p.getAltar()){
				if (block.getX()==ev.getBlock().getX() && block.getY()<=ev.getBlock().getY() && block.getZ()==ev.getBlock().getZ()){
					isOnTheAltar = true;
				}
			}
			if (isOnTheAltar){
				ev.setCancelled(true);
			}
		}
	}
}
