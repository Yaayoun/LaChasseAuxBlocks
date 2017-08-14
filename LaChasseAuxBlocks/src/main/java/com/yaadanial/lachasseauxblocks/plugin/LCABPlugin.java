package com.yaadanial.lachasseauxblocks.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
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
	private Altar altar = null;
	private ScoreBoardManager scoreBoardManager = null;
	private BlocksFindByPlayer blocksFindByPlayer = null;
	private List<LCABTeam> teams = new ArrayList<LCABTeam>();
	private Map<String, ConversationFactory> conversationFactories = new HashMap<String, ConversationFactory>();
	private LCABPrompts prompts = null;

	@Override
	public void onEnable() {

		prompts = new LCABPrompts(this);
		logger = Bukkit.getLogger();
		logger.info("LCABPlugin loaded!");
		altar = new Altar();
		getServer().getWorlds().get(0).setTime(6000L);
		getServer().getWorlds().get(0).setStorm(false);
		getServer().getWorlds().get(0).setDifficulty(Difficulty.HARD);

		getServer().getPluginManager().registerEvents(new LCABPluginListener(this), this);

		scoreBoardManager = new ScoreBoardManager(this);
		scoreBoardManager.setMatchInfo();

		blocksFindByPlayer = new BlocksFindByPlayer(0);

		conversationFactories.put("teamPrompt", new ConversationFactory(this).withModality(true).withFirstPrompt(prompts.getTNP()).withEscapeSequence("/cancel")
				.thatExcludesNonPlayersWithMessage("Il faut être un joueur ingame.").withLocalEcho(false).addConversationAbandonedListener(this));

		conversationFactories.put("playerPrompt", new ConversationFactory(this).withModality(true).withFirstPrompt(prompts.getPP()).withEscapeSequence("/cancel")
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
				if (teams.size() == 0) {
					for (Player player : getServer().getOnlinePlayers()) {
						LCABTeam team = new LCABTeam(player.getName(), player.getName(), ChatColor.WHITE, this);
						team.addPlayer(player);
						teams.add(team);
					}
				}
				if (!this.gameRunning) {
					altar.generate(pl);

					this.logToChat(ChatColor.GREEN + "--- L'Autel a Spawn ---");
					this.gameRunning = true;
					scoreBoardManager.restartChronometre();
					scoreBoardManager.getChronometre().run();
					blocksFindByPlayer = new BlocksFindByPlayer(2);
					for (Player player : getServer().getOnlinePlayers()) {
						blocksFindByPlayer.addBlocksFindByPlayer(player.getName(), 0);
					}
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
				altar = new Altar();
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

	public boolean createTeam(String name, ChatColor color) {
		if (teams.size() <= 50) {
			teams.add(new LCABTeam(name, name, color, this));
			return true;
		}
		return false;
	}

	public boolean isGameRunning() {
		return this.gameRunning;
	}

	public Altar getAltar() {
		return this.altar;
	}

	public void restartAltar() {
		this.altar = new Altar();
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

	public BlocksFindByPlayer getBlocksFindByPlayer() {
		return blocksFindByPlayer;
	}

	public void setBlocksFindByPlayer(BlocksFindByPlayer blocksFindByPlayer) {
		this.blocksFindByPlayer = blocksFindByPlayer;
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
			abandonedEvent.getContext().getForWhom().sendRawMessage(ChatColor.RED + "Abandonné par " + abandonedEvent.getCanceller().getClass().getName());
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
}
