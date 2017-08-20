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
		event.getPlayer().setGameMode(GameMode.SPECTATOR);
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
		if (this.plugin.isGameRunning() && plugin.getAltars().contains(event.getBlock())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockBurnEvent(final BlockBurnEvent event) {
		if (this.plugin.isGameRunning() && plugin.getAltars().contains(event.getBlock())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockExplodeEvent(final EntityExplodeEvent event) {
		if (this.plugin.isGameRunning()) {
			for (Block blockAltar : plugin.getAltars()) {
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
		if (this.plugin.isGameRunning() && plugin.getAltars().contains(event.getBlock())) {
			event.setCancelled(true);
		} else if (this.plugin.isGameRunning()) {
			for (LCABTeam team : plugin.getTeams()) {
				if (team.getAltar().getPlacingBlocks().contains(event.getBlock())) {
					int index = 0;
					for (Block placingBlock : team.getAltar().getPlacingBlocks()) {
						//si le block est dans un emplacement à block
						if (placingBlock.getX() == event.getBlock().getX() && placingBlock.getY() == event.getBlock().getY() && placingBlock.getZ() == event.getBlock().getZ()) {
							for (Player player : team.getPlayers()) {
								//si c'est le joueur est dans la team
								if (event.getPlayer().equals(player)) {
									if (theGoodBlock(event, team, index)) {
										//si c'est le bon Block
										team.getAltar().getBlocks().add(event.getBlock());
										plugin.getBlocksFindByTeam().addPointToTeam(team.getDisplayName());
										if (plugin.getBlocksFindByTeam().getBlocksFindByTeam().get(team.getDisplayName()) >= plugin.getBlocksFindByTeam().getNbblocksFindMax()) {
											plugin.logToChat(team.getChatColor() + team.getDisplayName() + ChatColor.DARK_GREEN + " a gagné cette chasse aux blocks !");
											plugin.getScoreBoardManager().getChronometre().stop();
											plugin.setGameRunning(false);
											plugin.setAllPlayersIntoSpectate();
										}
									} else {
										event.getPlayer().sendMessage(ChatColor.RED + "Ce n'est pas le bon block !");
										event.setCancelled(true);
									}
									return;
								}
							}
							event.getPlayer().sendMessage(ChatColor.RED + "Ce n'est pas l'Autel de votre Team !");
							event.setCancelled(true);
							return;
						}
						index++;
					}
				}
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
						player.sendMessage(ChatColor.GREEN + "la team " + team.getChatColor() + team.getDisplayName() + ChatColor.GREEN + " a bien été supprimé !");
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
								player.sendMessage(ChatColor.GREEN + "le joueur " + ChatColor.DARK_GREEN + playerToDelete.getDisplayName() + ChatColor.GREEN + " a bien été supprimé de la team "
										+ team.getChatColor() + team.getDisplayName() + ChatColor.GREEN + " !");
							}
							return;
						}
					}
				}
			}
		}
	}

	private boolean theGoodBlock(final BlockPlaceEvent event, LCABTeam team, int index) {
		SpecialCaseBlock specialCaseBlock = new SpecialCaseBlock();
		for (BlockTypeData block : specialCaseBlock.getSpecialCaseBlock()) {
			if (team.getAltar().getRandomBlocks().get(index).getMaterial().getId() == block.getMaterial().getId()) {
				return team.getAltar().getRandomBlocks().get(index).getMaterial().getId() == event.getBlock().getTypeId();
			}
		}
		return team.getAltar().getRandomBlocks().get(index).getMaterial().getId() == event.getBlock().getTypeId()
				&& team.getAltar().getRandomBlocks().get(index).getData() == event.getBlock().getData();
	}
}
