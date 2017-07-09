package com.yaadanial.lachasseauxblocks.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class LCABPlugin extends JavaPlugin {

	private Logger logger = null;
	private Boolean gameRunning = false;
	private List<Block> altar = new ArrayList<Block>();
	private ScoreBoardManager scoreBoardManager = null;
	private Chronometre chronometre = null;
	private BlocksFindByPlayer blocksFindByPlayer = null;

	@Override
	public void onEnable() {

		logger = Bukkit.getLogger();
		logger.info("LCABPlugin loaded!");
		getServer().getWorlds().get(0).setTime(6000L);
		getServer().getWorlds().get(0).setStorm(false);
		getServer().getWorlds().get(0).setDifficulty(Difficulty.HARD);

		getServer().getPluginManager().registerEvents(new LCABPluginListener(this), this);

		chronometre = new Chronometre(this);

		scoreBoardManager = new ScoreBoardManager(this);
		scoreBoardManager.setMatchInfo();

		blocksFindByPlayer = new BlocksFindByPlayer(0);
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
				pl.sendMessage("Usage : /lcab <start|stop|tp>");
				return true;
			}
			if (a[0].equalsIgnoreCase("start")) {
				if (!this.gameRunning) {
					createAltar(pl);

					this.logToChat(ChatColor.GREEN + "--- L'Autel a Spawn ---");
					this.gameRunning = true;
					this.chronometre.run();
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
				this.gameRunning = false;
				altar = new ArrayList<Block>();
				chronometre = new Chronometre(this);
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
			}
		}
		return false;
	}

	/**
	 * Créer l'Autel
	 * 
	 * @param pl le Joueur
	 */
	private void createAltar(Player pl) {
		World w = pl.getWorld();

		createBaseOfAltar(pl, w);
		createBlockAndesite(pl, w, 3, 0, -1);
		createBlockAndesite(pl, w, 3, 0, 1);
		createBackWallOfAltar(pl, w);
	}

	/**
	 * Créer la Base de l'Autel
	 * 
	 * @param pl le Joueur
	 * @param w le Monde
	 */
	private void createBaseOfAltar(Player pl, World w) {
		for (int i = 0; i < 5; i++) {
			for (int j = -2; j < 3; j++) {
				createBlockAndesite(pl, w, 2 + i, -1, j);
			}
		}
	}

	/**
	 * Créer le Mur du fond de l'Autel
	 * 
	 * @param pl le Joueur
	 * @param w le Monde
	 */
	private void createBackWallOfAltar(Player pl, World w) {
		for (int i = 0; i < 3; i++) {
			for (int j = -2; j < 3; j++) {
				createBlockGranite(pl, w, 6, i, j);
			}
		}
		createBlockRandom(pl, w, 6, 1, -1);
		createBlockRandom(pl, w, 6, 1, 1);
	}

	/**
	 * Créer un Block d'Andésite
	 * 
	 * @param pl le Joueur
	 * @param w le Monde
	 * @param x la position en X du Block
	 * @param y la position en Y du Block
	 * @param z la position en Z du Block
	 */
	private void createBlockAndesite(Player pl, World w, int x, int y, int z) {
		Block block = w.getBlockAt(pl.getLocation().getBlockX() + x, pl.getLocation().getBlockY() + y, pl.getLocation().getBlockZ() + z);
		block.setType(Material.STONE);
		block.setData((byte) 6);
		altar.add(block);
	}

	/**
	 * Créer un Block de Granite
	 * 
	 * @param pl le Joueur
	 * @param w le Monde
	 * @param x la position en X du Block
	 * @param y la position en Y du Block
	 * @param z la position en Z du Block
	 */
	private void createBlockGranite(Player pl, World w, int x, int y, int z) {
		Block block = w.getBlockAt(pl.getLocation().getBlockX() + x, pl.getLocation().getBlockY() + y, pl.getLocation().getBlockZ() + z);
		block.setType(Material.STONE);
		block.setData((byte) 2);
		altar.add(block);
	}

	/**
	 * Créer un Block Aléatoire
	 * 
	 * @param pl le Joueur
	 * @param w le Monde
	 * @param x la position en X du Block
	 * @param y la position en Y du Block
	 * @param z la position en Z du Block
	 */
	private void createBlockRandom(Player pl, World w, int x, int y, int z) {
		Block block = w.getBlockAt(pl.getLocation().getBlockX() + x, pl.getLocation().getBlockY() + y, pl.getLocation().getBlockZ() + z);
		Random random = new Random();
		Material[] materials = Material.values();
		int size = materials.length;
		int index = random.nextInt(size);
		Material randomMaterial = materials[index];
		block.setType(randomMaterial);
		altar.add(block);
		this.logToChat(ChatColor.GRAY + "Le Block " + randomMaterial.name() + " est à chercher ! (id=" + randomMaterial.getId() + ")");
	}

	public boolean isGameRunning() {
		return this.gameRunning;
	}

	public List<Block> getAltar() {
		return this.altar;
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

	public Chronometre getChronometre() {
		return chronometre;
	}

	public void setChronometre(Chronometre chronometre) {
		this.chronometre = chronometre;
	}

	public BlocksFindByPlayer getBlocksFindByPlayer() {
		return blocksFindByPlayer;
	}

	public void setBlocksFindByPlayer(BlocksFindByPlayer blocksFindByPlayer) {
		this.blocksFindByPlayer = blocksFindByPlayer;
	}
}
