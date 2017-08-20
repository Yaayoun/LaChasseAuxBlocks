package com.yaadanial.lachasseauxblocks.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.conversations.ConversationAbandonedListener;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Classe Main du Plugin
 * 
 * @author Yaadanial
 *
 */
public class LCABPlugin extends JavaPlugin implements ConversationAbandonedListener {

	private Logger logger = null;
	private Boolean gameRunning = false;
	private ScoreBoardManager scoreBoardManager = null;
	private BlocksFindByTeam blocksFindByTeam = null;
	private List<LCABTeam> teams = new ArrayList<LCABTeam>();
	private Map<String, ConversationFactory> conversationFactories = new HashMap<String, ConversationFactory>();
	private LCABPrompts prompts = null;
	private AskingBlock askingBlocks = null;

	@Override
	public void onEnable() {

		prompts = new LCABPrompts(this);
		logger = Bukkit.getLogger();
		logger.info("LCABPlugin loaded!");
		getServer().getWorlds().get(0).setTime(6000L);
		getServer().getWorlds().get(0).setStorm(false);
		getServer().getWorlds().get(0).setDifficulty(Difficulty.HARD);

		getServer().getPluginManager().registerEvents(new LCABPluginListener(this), this);

		blocksFindByTeam = new BlocksFindByTeam(0);

		askingBlocks = new AskingBlock();

		scoreBoardManager = new ScoreBoardManager(this);
		scoreBoardManager.setMatchInfo();

		conversationFactories.put("teamPrompt", new ConversationFactory(this).withModality(true).withFirstPrompt(prompts.getTNP()).withEscapeSequence("!cancel")
				.thatExcludesNonPlayersWithMessage("Il faut être un joueur ingame.").withLocalEcho(false).addConversationAbandonedListener(this));

		conversationFactories.put("playerPrompt", new ConversationFactory(this).withModality(true).withFirstPrompt(prompts.getPP()).withEscapeSequence("!cancel")
				.thatExcludesNonPlayersWithMessage("Il faut être un joueur ingame.").withLocalEcho(false).addConversationAbandonedListener(this));
	}

	@Override
	public void onDisable() {
		logger.info("LCABPlugin unloaded!");
	}

	public boolean onCommand(final CommandSender s, Command c, String l, String[] a) {
		String command = c.getName().toLowerCase();
		switch (command) {
		case "lcab":
			if (!(s instanceof Player)) {
				s.sendMessage(ChatColor.RED + "Vous devez être un joueur");
				return true;
			}
			Player pl = (Player) s;
			if (!pl.isOp()) {
				pl.sendMessage(ChatColor.RED + "Lolnope.");
				return true;
			}
			if (a.length == 0) {
				pl.sendMessage("Usage : /lcab <start|stop|tp|team>");
				return true;
			}
			if (a[0].equalsIgnoreCase("start")) {
				if (!this.gameRunning) {
					this.gameRunning = true;
					scoreBoardManager.restartChronometre();
					scoreBoardManager.getChronometre().run();
					blocksFindByTeam = new BlocksFindByTeam(2);
					if (teams.size() == 0) {
						for (Player player : getServer().getOnlinePlayers()) {
							LCABTeam team = new LCABTeam(player.getName(), player.getName(), ChatColor.WHITE, teams.size(), this);
							team.addPlayer(player);
							teams.add(team);
						}
					}

					Random random = new Random();
					List<BlockTypeData> randomBlocks = new ArrayList<BlockTypeData>();
					int index = random.nextInt(askingBlocks.getAskingBlock().size());
					randomBlocks.add(askingBlocks.getAskingBlock().get(index));
					index = random.nextInt(askingBlocks.getAskingBlock().size());
					randomBlocks.add(askingBlocks.getAskingBlock().get(index));
					restartAltar();
					for (LCABTeam team : teams) {
						blocksFindByTeam.addBlocksFindByTeam(team.getDisplayName(), 0);
						team.getAltar().generate(randomBlocks);
						team.initializeGame();
					}
					this.logToChat(ChatColor.GREEN + "--- L'Autel a Spawn ---");
				} else {
					this.logToChat(ChatColor.RED + "La Chasse est déjà lancée !");
				}
				return true;
			} else if (a[0].equalsIgnoreCase("stop")) {
				if (this.gameRunning == false) {
					this.logToChat(ChatColor.RED + "Aucune chasse en cours !");
					return true;
				}
				// On arrête la chasse.
				this.logToChat(ChatColor.YELLOW + "--- La chasse a été annulée par " + s.getName() + " ---");
				scoreBoardManager.getChronometre().stop();
				this.gameRunning = false;
				setAllPlayersIntoSpectate();
				return true;
			} else if (a[0].equalsIgnoreCase("tp")) {
				// Téléporte le joueur sur l'autel
				if (a.length <= 1) {
					pl.sendMessage("Usage : /lcab tp <player>");
					return true;
				}
				if (this.gameRunning == false) {
					pl.sendMessage("La chasse doit être lancée pour se tp sur l'autel");
					return true;
				}
				pl.sendMessage("Cette fonctionnalité sera implémentée dans une version ultérieure");
				return true;
			} else if (a[0].equalsIgnoreCase("team")) {
				Inventory inventory = this.getServer().createInventory(pl, 54, "- Teams -");
				Integer slot = 0;
				ItemStack itemStack = null;
				for (LCABTeam team : teams) {
					itemStack = new ItemStack(Material.BEACON, team.getPlayers().size());
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(team.getChatColor() + team.getDisplayName());
					ArrayList<String> lore = new ArrayList<String>();
					for (Player player : team.getPlayers()) {
						lore.add("- " + player.getDisplayName());
					}
					itemMeta.setLore(lore);
					itemStack.setItemMeta(itemMeta);
					inventory.setItem(slot, itemStack);
					slot++;
				}

				ItemStack itemStack2 = new ItemStack(Material.DIAMOND);
				ItemMeta itemMeta2 = itemStack2.getItemMeta();
				itemMeta2.setDisplayName(ChatColor.AQUA + "" + ChatColor.ITALIC + "Créer une team");
				itemStack2.setItemMeta(itemMeta2);
				inventory.setItem(53, itemStack2);

				pl.openInventory(inventory);
				return true;
			} else if (a[0].equalsIgnoreCase("debug")) {
				AskingBlock askingBlock = new AskingBlock();
				int i = 0;
				for (BlockTypeData blockTypeData : askingBlock.getAskingBlock()) {
					Block block = pl.getWorld().getBlockAt(pl.getLocation().getBlockX() + ++i, pl.getLocation().getBlockY() - 1, pl.getLocation().getBlockZ());
					block.setType(Material.STONE);
					block.setData((byte) 6);
					Block block1 = pl.getWorld().getBlockAt(pl.getLocation().getBlockX() + i, pl.getLocation().getBlockY(), pl.getLocation().getBlockZ());
					block1.setType(blockTypeData.getMaterial());
					block1.setData((byte) (int) blockTypeData.getData());
				}
				return true;
			}
		}
		return false;
	}

	public void setAllPlayersIntoSpectate() {
		for (LCABTeam team : teams) {
			for (Player player : team.getPlayers()) {
				player.setGameMode(GameMode.SPECTATOR);
			}
		}
	}

	public boolean createTeam(String name, ChatColor color) {
		if (teams.size() <= 50) {
			teams.add(new LCABTeam(name, name, color, teams.size(), this));
			return true;
		}
		return false;
	}

	public boolean isGameRunning() {
		return this.gameRunning;
	}

	public void restartAltar() {
		for (LCABTeam team : teams) {
			team.setAltar(new Altar(team, this));
		}
	}

	public List<Block> getAltars() {
		List<Block> altars = new ArrayList<>();
		for (LCABTeam team : teams) {
			altars.addAll(team.getAltar().getBlocks());
		}
		return altars;
	}

	public void logToChat(String log) {
		Bukkit.getServer().broadcastMessage(log);
	}

	public ScoreBoardManager getScoreBoardManager() {
		return scoreBoardManager;
	}

	public void setScoreBoardManager(ScoreBoardManager scoreBoardManager) {
		this.scoreBoardManager = scoreBoardManager;
	}

	public BlocksFindByTeam getBlocksFindByTeam() {
		return blocksFindByTeam;
	}

	public void setBlocksFindByTeam(BlocksFindByTeam blocksFindByTeam) {
		this.blocksFindByTeam = blocksFindByTeam;
	}

	public Boolean getGameRunning() {
		return gameRunning;
	}

	public void setGameRunning(Boolean gameRunning) {
		this.gameRunning = gameRunning;
	}

	public List<LCABTeam> getTeams() {
		return teams;
	}

	public void setTeams(List<LCABTeam> teams) {
		this.teams = teams;
	}

	public LCABTeam getTeam(String name) {
		for (LCABTeam team : teams) {
			if (team.getName().equalsIgnoreCase(name))
				return team;
		}
		return null;
	}

	@Override
	public void conversationAbandoned(ConversationAbandonedEvent abandonedEvent) {
		if (!abandonedEvent.gracefulExit()) {
			abandonedEvent.getContext().getForWhom().sendRawMessage(ChatColor.RED + "Conversation abandonnée !");
		}
	}

	public LCABTeam getTeamForPlayer(Player p) {
		for (LCABTeam team : teams) {
			if (team.getPlayers().contains(p))
				return team;
		}
		return null;
	}

	public ConversationFactory getConversationFactory(String string) {
		if (conversationFactories.containsKey(string))
			return conversationFactories.get(string);
		return null;
	}

	public void openWindowTeam(String teamName, Player player) {
		LCABTeam team = findTeam(teamName);
		Inventory inventory = this.getServer().createInventory(player, 54, "- " + team.getDisplayName() + " -");
		Integer slot = 0;
		ItemStack itemStack = null;
		for (Player playerInTeam : team.getPlayers()) {
			itemStack = new ItemStack(Material.BEACON, 1);
			ItemMeta itemMeta = itemStack.getItemMeta();
			itemMeta.setDisplayName(team.getChatColor() + playerInTeam.getDisplayName());
			ArrayList<String> lore = new ArrayList<String>();
			lore.add("Cliquer pour supprimer ce joueur");
			itemMeta.setLore(lore);
			itemStack.setItemMeta(itemMeta);
			inventory.setItem(slot, itemStack);
			slot++;
		}

		ItemStack itemStack2 = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta itemMeta2 = itemStack2.getItemMeta();
		itemMeta2.setDisplayName(ChatColor.DARK_RED + "Supprimer la team");
		itemStack2.setItemMeta(itemMeta2);
		inventory.setItem(52, itemStack2);

		ItemStack itemStack3 = new ItemStack(Material.DIAMOND);
		ItemMeta itemMeta3 = itemStack3.getItemMeta();
		itemMeta3.setDisplayName(ChatColor.AQUA + "" + ChatColor.ITALIC + "Ajouter un joueur");
		itemStack3.setItemMeta(itemMeta3);
		inventory.setItem(53, itemStack3);

		player.openInventory(inventory);
	}

	public void openWindowDeletePlayer(String teamName, String playerToDelete, Player player) {
		Inventory inventory = this.getServer().createInventory(player, 9, "- Êtes-vous sûr de vouloir supprimer " + playerToDelete + " de la team " + teamName + " -");
		ItemStack itemStack = null;

		itemStack = new ItemStack(Material.REDSTONE_BLOCK, 1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.DARK_RED + "Non");
		itemStack.setItemMeta(itemMeta);
		inventory.setItem(0, itemStack);

		ItemStack itemStack2 = new ItemStack(Material.DIAMOND);
		ItemMeta itemMeta2 = itemStack2.getItemMeta();
		itemMeta2.setDisplayName(ChatColor.DARK_GREEN + "Oui");
		itemStack2.setItemMeta(itemMeta2);
		inventory.setItem(1, itemStack2);

		player.openInventory(inventory);
	}

	public void openWindowDeleteTeam(String teamName, Player player) {
		Inventory inventory = this.getServer().createInventory(player, 9, "- Êtes-vous sûr de vouloir supprimer la team " + teamName + " -");
		ItemStack itemStack = null;

		itemStack = new ItemStack(Material.REDSTONE_BLOCK, 1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.DARK_RED + "Non");
		itemStack.setItemMeta(itemMeta);
		inventory.setItem(0, itemStack);

		ItemStack itemStack2 = new ItemStack(Material.DIAMOND);
		ItemMeta itemMeta2 = itemStack2.getItemMeta();
		itemMeta2.setDisplayName(ChatColor.DARK_GREEN + "Oui");
		itemStack2.setItemMeta(itemMeta2);
		inventory.setItem(1, itemStack2);

		player.openInventory(inventory);
	}

	public void deleteAPlayerToATeam(LCABTeam team, Player player) {
		teams.get(teams.indexOf(team)).getPlayers().remove(player);
	}

	public LCABTeam findTeam(String teamName) {
		for (LCABTeam team : teams) {
			if (team.getDisplayName().equals(teamName)) {
				return team;
			}
		}
		return null;
	}

	public void deleteATeam(LCABTeam team) {
		teams.remove(team);
		scoreBoardManager.getScoreBoard().getTeam(team.getDisplayName()).unregister();
	}

	public AskingBlock getAskingBlocks() {
		return askingBlocks;
	}

	public void setAskingBlocks(AskingBlock askingBlocks) {
		this.askingBlocks = askingBlocks;
	}
}
