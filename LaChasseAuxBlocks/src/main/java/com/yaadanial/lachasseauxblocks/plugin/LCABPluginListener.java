package com.yaadanial.lachasseauxblocks.plugin;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

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
	public void onBlockBurnEvent(final BlockBurnEvent ev) {
		if (this.p.isGameRunning() && p.getAltar().contains(ev.getBlock())) {
			ev.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockExplodeEvent(final EntityExplodeEvent ev) {
		boolean isExplodeAltar = false;
		if (this.p.isGameRunning()) {
			for (Block blockAltar : p.getAltar()) {
				for (Block blockExplode : ev.blockList()) {
					if (blockAltar.getX() == blockExplode.getX() && blockAltar.getY() == blockExplode.getY() && blockAltar.getZ() == blockExplode.getZ()) {
						p.logToChat("L'Autel ne peut pas exploser !");
						isExplodeAltar = true;
					}
				}
			}
			if (isExplodeAltar) {
				ev.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBlockPlaceEvent(final BlockPlaceEvent ev) {
		boolean isOnTheAltar = false;
		if (this.p.isGameRunning() && p.getAltar().contains(ev.getBlock())) {
			for (Block block : p.getAltar()) {
				if (block.getX() == ev.getBlock().getX() && block.getY() <= ev.getBlock().getY() && block.getZ() == ev.getBlock().getZ()) {
					isOnTheAltar = true;
				}
			}
			if (isOnTheAltar) {
				ev.setCancelled(true);
			}
		}
	}
}
