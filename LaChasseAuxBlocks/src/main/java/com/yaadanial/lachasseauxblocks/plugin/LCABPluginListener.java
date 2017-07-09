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
		if (this.p.isGameRunning() && p.getAltar().getBlocks().contains(ev.getBlock())) {
			ev.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockBurnEvent(final BlockBurnEvent ev) {
		if (this.p.isGameRunning() && p.getAltar().getBlocks().contains(ev.getBlock())) {
			ev.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockExplodeEvent(final EntityExplodeEvent ev) {
		if (this.p.isGameRunning()) {
			for (Block blockAltar : p.getAltar().getBlocks()) {
				for (Block blockExplode : ev.blockList()) {
					if (blockAltar.getX() == blockExplode.getX() && blockAltar.getY() == blockExplode.getY() && blockAltar.getZ() == blockExplode.getZ()) {
						ev.setCancelled(true);
					}
				}
			}
		}
	}

	@EventHandler
	public void onBlockPlaceEvent(final BlockPlaceEvent ev) {
		if (this.p.isGameRunning() && p.getAltar().getBlocks().contains(ev.getBlock())) {
			ev.setCancelled(true);
		} else if (this.p.isGameRunning() && p.getAltar().getPlacingBlocks().contains(ev.getBlock())) {
			for (Block placingBlock : p.getAltar().getPlacingBlocks()) {
				if (placingBlock.getX() == ev.getBlock().getX() && placingBlock.getY() == ev.getBlock().getY() && placingBlock.getZ() == ev.getBlock().getZ()) {
					p.getAltar().getBlocks().add(ev.getBlock());
				}
			}
		}
	}
}
