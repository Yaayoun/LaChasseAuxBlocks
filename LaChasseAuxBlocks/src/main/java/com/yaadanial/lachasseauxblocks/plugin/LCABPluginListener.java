package com.yaadanial.lachasseauxblocks.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

/**
 * Classe de gestion des events
 * 
 * @author Yaadanial
 *
 */
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
		plugin.getBlocksFindByPlayer().addBlocksFindByPlayer(event.getPlayer().getName(), 0);
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
		if (this.plugin.isGameRunning()) {
			for (Block blockAltar : plugin.getAltar().getBlocks()) {
				for (Block blockExplode : event.blockList()) {
					if (blockAltar.getX() == blockExplode.getX() && blockAltar.getY() == blockExplode.getY() && blockAltar.getZ() == blockExplode.getZ()) {
						event.setCancelled(true);
						return;
					}
				}
			}
		}
	}

	@EventHandler
	public void onBlockPlaceEvent(final BlockPlaceEvent event) {
		if (this.plugin.isGameRunning() && plugin.getAltar().getBlocks().contains(event.getBlock())) {
			event.setCancelled(true);
		} else if (this.plugin.isGameRunning() && plugin.getAltar().getPlacingBlocks().contains(event.getBlock())) {
			for (Block placingBlock : plugin.getAltar().getPlacingBlocks()) {
				if (placingBlock.getX() == event.getBlock().getX() && placingBlock.getY() == event.getBlock().getY() && placingBlock.getZ() == event.getBlock().getZ()) {
					plugin.getAltar().getBlocks().add(event.getBlock());
					plugin.getBlocksFindByPlayer().addPointToPlayer(plugin.getAltar().getPlayer().getName());
					if (plugin.getBlocksFindByPlayer().getBlocksFindByPlayer().get(plugin.getAltar().getPlayer().getName()) >= plugin.getBlocksFindByPlayer().getNbblocksFindMax()) {
						plugin.logToChat(ChatColor.GREEN + plugin.getAltar().getPlayer().getName() + " a gagné cette chasse aux blocks !");
						plugin.getScoreBoardManager().getChronometre().stop();
					}
					return;
				}
			}

		}
	}
}
