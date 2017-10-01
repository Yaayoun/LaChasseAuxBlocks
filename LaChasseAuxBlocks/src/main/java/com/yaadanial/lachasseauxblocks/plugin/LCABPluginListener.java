package com.yaadanial.lachasseauxblocks.plugin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class LCABPluginListener implements Listener {

	private LCABPlugin plugin = null;

	public LCABPluginListener(LCABPlugin p) {
		this.plugin = p;
	}

	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent event) {
		if (!this.plugin.isGameRunning()) {
			event.getPlayer().setGameMode(GameMode.CREATIVE);
		}
		plugin.getBlocksFoundByPlayer().addBlocksFoundByPlayer(event.getPlayer().getName(), 0);
		plugin.getScoreBoardManager().addToScoreboard(event.getPlayer());
		Bukkit.getScheduler().runTaskLater(this.plugin, new BukkitRunnable() {

			@Override
			public void run() {
				plugin.getScoreBoardManager().updatePlayerListName(event.getPlayer());
			}
		}, 1L);
	}

	@EventHandler
	public void onBlockBreakEvent(final BlockBreakEvent event) {
		if (this.plugin.isGameRunning() && plugin.getAltar().getBlocks().contains(event.getBlock())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockBurnEvent(final BlockBurnEvent event) {
		if (this.plugin.isGameRunning() && plugin.getAltar().getBlocks().contains(event.getBlock())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockExplodeEvent(final EntityExplodeEvent event) {
		boolean isExplodeAltar = false;
		if (this.plugin.isGameRunning()) {
			for (Block blockAltar : plugin.getAltar().getBlocks()) {
				for (Block blockExplode : event.blockList()) {
					if (blockAltar.getX() == blockExplode.getX() && blockAltar.getY() == blockExplode.getY() && blockAltar.getZ() == blockExplode.getZ()) {
						event.setCancelled(true);
						return;
					}
				}
			}
			if (isExplodeAltar) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBlockPlaceEvent(final BlockPlaceEvent event) {
		boolean isOnTheAltar = false;
		if (this.plugin.isGameRunning() && plugin.getAltar().getBlocks().contains(event.getBlock())) {
			for (Block block : plugin.getAltar()) {
				if (block.getX() == event.getBlock().getX() && block.getY() <= event.getBlock().getY() && block.getZ() == event.getBlock().getZ()) {
					isOnTheAltar = true;
				}
			}
			if (isOnTheAltar) {
				event.setCancelled(true);
			}
		}
	}
}
