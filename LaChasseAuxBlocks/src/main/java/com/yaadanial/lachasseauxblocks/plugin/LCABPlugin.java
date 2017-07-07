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
import org.bukkit.scoreboard.Scoreboard;

public class LCABPlugin extends JavaPlugin {

	private Logger logger = null;
	private Scoreboard sb = null;
	private Boolean gameRunning = false;
	private List<Block> altar = new ArrayList<Block>();

	@Override
	public void onEnable() {

		logger = Bukkit.getLogger();
		logger.info("LCABPlugin loaded!");
		getServer().getWorlds().get(0).setTime(6000L);
		getServer().getWorlds().get(0).setStorm(false);
		getServer().getWorlds().get(0).setDifficulty(Difficulty.HARD);

		getServer().getPluginManager().registerEvents(new LCABPluginListener(this), this);
	}

	@Override
	public void onDisable() {
		logger.info("LCABPlugin unloaded!");
	}

	public boolean onCommand(final CommandSender s, Command c, String l, String[] a) {
		if (c.getName().equalsIgnoreCase("lcab")) {
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
				pl.sendMessage("Usage : /lcab <start>");
				return true;
			}
			if (a[0].equalsIgnoreCase("start")) {
				if (!this.gameRunning) {
					createAltar(pl);

					Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "--- L'Autel a Spawn ---");
					this.gameRunning = true;
				} else {
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "La Chasse est déjà lancée !");
				}
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
		Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "Le Block " + randomMaterial.name() + " est à chercher ! (id=" + randomMaterial.getId() + ")");
	}

	public boolean isGameRunning() {
		return this.gameRunning;
	}

	public List<Block> getAltar() {
		return this.altar;
	}
}
