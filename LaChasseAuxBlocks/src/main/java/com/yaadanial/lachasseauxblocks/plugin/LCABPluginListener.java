package com.yaadanial.lachasseauxblocks.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.conversations.Conversation;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
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
			int index = 0;
			for (Block placingBlock : plugin.getAltar().getPlacingBlocks()) {
				//si le block est dans un emplacement à block
				if (placingBlock.getX() == event.getBlock().getX() && placingBlock.getY() == event.getBlock().getY() && placingBlock.getZ() == event.getBlock().getZ()) {
					//si c'est le bon Block
					if (theGoodBlock(event, index)) {
						plugin.getAltar().getBlocks().add(event.getBlock());
						plugin.getBlocksFindByPlayer().addPointToPlayer(plugin.getAltar().getPlayer().getName());
						if (plugin.getBlocksFindByPlayer().getBlocksFindByPlayer().get(plugin.getAltar().getPlayer().getName()) >= plugin.getBlocksFindByPlayer().getNbblocksFindMax()) {
							plugin.logToChat(ChatColor.GREEN + plugin.getAltar().getPlayer().getName() + " a gagné cette chasse aux blocks !");
							plugin.getScoreBoardManager().getChronometre().stop();
							plugin.setGameRunning(false);
							plugin.restartAltar();
						}
						return;
					} else {
						event.getPlayer().sendMessage(ChatColor.RED + "Ce n'est pas le bon block !");
						event.getPlayer().sendMessage(ChatColor.RED + "" + plugin.getAltar().getRandomBlocks().get(index).getTypeId() + " != " + event.getBlock().getTypeId());
						event.getPlayer().sendMessage(ChatColor.RED + "" + plugin.getAltar().getRandomBlocks().get(index).getData() + " != " + event.getBlock().getData());
						event.setCancelled(true);
					}
				}
				index++;
			}

		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getInventory().getName().equals("- Teams -")) {
			Player player = (Player) event.getWhoClicked();
			event.setCancelled(true);
			if (event.getCurrentItem().getType() == Material.DIAMOND) {
				player.closeInventory();
				plugin.getConversationFactory("teamPrompt").buildConversation(player).begin();
			} else if (event.getCurrentItem().getType() == Material.BEACON) {
				player.closeInventory();
				plugin.openWindowTeam(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()), player);
			}
		} else {
			for (LCABTeam team : plugin.getTeams()) {
				if (event.getInventory().getName().equals("- " + team.getDisplayName() + " -")) {
					Player player = (Player) event.getWhoClicked();
					event.setCancelled(true);
					if (event.getCurrentItem().getType() == Material.DIAMOND) {
						player.closeInventory();
						Conversation conversation = plugin.getConversationFactory("playerPrompt").buildConversation(player);
						conversation.getContext().setSessionData("nomTeam", team.getDisplayName());
						conversation.getContext().setSessionData("color", team.getChatColor());
						conversation.begin();
					} else if (event.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
						player.closeInventory();
						plugin.openWindowDeleteTeam(team.getDisplayName(), player);
					} else if (event.getCurrentItem().getType() == Material.BEACON) {
						player.closeInventory();
						plugin.openWindowDeletePlayer(team.getDisplayName(), ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()), player);
					}
					return;
				} else if (event.getInventory().getName().equals("- Êtes-vous sûr de vouloir supprimer la team " + team.getDisplayName() + " -")) {
					Player player = (Player) event.getWhoClicked();
					event.setCancelled(true);
					if (event.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
						player.closeInventory();
					} else if (event.getCurrentItem().getType() == Material.DIAMOND) {
						player.closeInventory();
						plugin.deleteATeam(team);
					}
					return;
				} else {
					for (Player playerToDelete : team.getPlayers()) {
						if (event.getInventory().getName().equals("- Êtes-vous sûr de vouloir supprimer " + playerToDelete.getDisplayName() + " de la team " + team.getDisplayName() + " -")) {
							Player player = (Player) event.getWhoClicked();
							event.setCancelled(true);
							if (event.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
								player.closeInventory();
							} else if (event.getCurrentItem().getType() == Material.DIAMOND) {
								player.closeInventory();
								plugin.deleteAPlayerToATeam(team, playerToDelete);
							}
							return;
						}
					}
				}
			}
		}
	}

	private boolean theGoodBlock(final BlockPlaceEvent event, int index) {
		SpecialCaseBlock specialCaseBlock = new SpecialCaseBlock();
		for (BlockTypeData block : specialCaseBlock.getSpecialCaseBlock()) {
			if (plugin.getAltar().getRandomBlocks().get(index).getTypeId() == block.getMaterial().getId()) {
				return plugin.getAltar().getRandomBlocks().get(index).getTypeId() == event.getBlock().getTypeId();
			}
		}
		return plugin.getAltar().getRandomBlocks().get(index).getTypeId() == event.getBlock().getTypeId() && plugin.getAltar().getRandomBlocks().get(index).getData() == event.getBlock().getData();
	}
}
