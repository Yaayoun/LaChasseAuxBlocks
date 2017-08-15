package com.yaadanial.lachasseauxblocks.plugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * Classe de gestion de l'autel
 * 
 * @author Yaadanial
 *
 */
public class Altar {

	private static final int SURFACE = 63;

	/** Le plugin **/
	private LCABPlugin plugin;

	/** L'Autel apartient à la team **/
	private LCABTeam team;

	/** Liste des Blocks de l'Autel **/
	private List<Block> blocks;

	/** Liste des Blocks à teamacer dans l'Autel **/
	private List<Block> placingBlocks;

	/** Liste des Blocks Aléatoires dans l'Autel **/
	private List<BlockTypeData> randomBlocks;

	public Altar(LCABTeam team, LCABPlugin plugin) {
		this.team = team;
		this.plugin = plugin;
		this.blocks = new ArrayList<Block>();
		this.placingBlocks = new ArrayList<Block>();
		this.randomBlocks = new ArrayList<BlockTypeData>();
	}

	public LCABTeam getTeam() {
		return team;
	}

	public void setTeam(LCABTeam team) {
		this.team = team;
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}

	public List<Block> getPlacingBlocks() {
		return placingBlocks;
	}

	public void setPlacingBlocks(List<Block> teamacingBlocks) {
		this.placingBlocks = teamacingBlocks;
	}

	public List<BlockTypeData> getRandomBlocks() {
		return randomBlocks;
	}

	public void setRandomBlocks(List<BlockTypeData> randomBlocks) {
		this.randomBlocks = randomBlocks;
	}

	/**
	 * Créer l'Autel
	 */
	public void generate(List<BlockTypeData> randomBlocks) {
		World world = plugin.getServer().getWorlds().get(0);
		this.randomBlocks = randomBlocks;

		createBaseOfAltar(world);
		createFirstFloorOfAltar(world);
		createSecondFloorOfAltar(world);
		createThirdFloorOfAltar(world);
		sendMessageToPlayers();
	}

	/**
	 * Écrit à tous les joueurs quels blocks il doivent chercher
	 */
	private void sendMessageToPlayers() {
		for (Player player : team.getPlayers()) {
			for (BlockTypeData block : randomBlocks) {
				player.sendMessage(ChatColor.GRAY + "Vous devez chercher un block de '" + block.getName() + "'");
			}
		}
	}

	/**
	 * Créer la Base de l'Autel
	 * 
	 * @param world le Monde
	 * 
	 *            Disposition des Blocks : [AAAAA] [AAAAA] [AAAAA] [AAAAA] [AAAAA] avec A = Andésite
	 */
	private void createBaseOfAltar(World world) {
		for (int i = 0; i < 5; i++) {
			for (int j = -2; j < 3; j++) {
				blocks.add(BlockUtil.createABlock(world, Material.STONE, 6, 2 + i, -1 + SURFACE, j + (6 * team.getNumberOfTeam())));
			}
		}
	}

	/**
	 * Créer le 1er étage de l'Autel
	 * 
	 * @param world le Monde
	 * 
	 *            Disposition des Blocks : [GGGGG] [VVVVV] [VVVVV] [VAVAV] [VVVVV] avec G = Granite, V = Vide, A = Andésite
	 */
	private void createFirstFloorOfAltar(World world) {
		for (int i = -2; i < 3; i++) {
			//Création de la ligne de granite
			blocks.add(BlockUtil.createABlock(world, Material.STONE, 2, 6, 0 + SURFACE, i + (6 * team.getNumberOfTeam())));
			//Puis 2 lignes de Vide
			blocks.add(BlockUtil.createABlock(world, Material.AIR, 1, 5, 0 + SURFACE, i + (6 * team.getNumberOfTeam())));
			blocks.add(BlockUtil.createABlock(world, Material.AIR, 1, 4, 0 + SURFACE, i + (6 * team.getNumberOfTeam())));
			//La 1ere ligne de Vide
			blocks.add(BlockUtil.createABlock(world, Material.AIR, 1, 2, 0 + SURFACE, i + (6 * team.getNumberOfTeam())));
		}
		//Création de la ligne avec les estrades
		blocks.add(BlockUtil.createABlock(world, Material.AIR, 1, 3, 0 + SURFACE, -2 + (6 * team.getNumberOfTeam())));
		blocks.add(BlockUtil.createABlock(world, Material.STONE, 6, 3, 0 + SURFACE, -1 + (6 * team.getNumberOfTeam())));
		blocks.add(BlockUtil.createABlock(world, Material.AIR, 1, 3, 0 + SURFACE, 0 + (6 * team.getNumberOfTeam())));
		blocks.add(BlockUtil.createABlock(world, Material.STONE, 6, 3, 0 + SURFACE, 1 + (6 * team.getNumberOfTeam())));
		blocks.add(BlockUtil.createABlock(world, Material.AIR, 1, 3, 0 + SURFACE, 2 + (6 * team.getNumberOfTeam())));
	}

	/**
	 * Créer le 2nd étage de l'Autel
	 * 
	 * @param world le Monde
	 * 
	 *            Disposition des Blocks : [GRGRG] [VVVVV] [VVVVV] [VPVPV] [VVVVV] avec G = Granite, V = Vide, R = Random, P = block à teamacer
	 */
	private void createSecondFloorOfAltar(World world) {

		//Création de la ligne Granite/block aléatoire
		blocks.add(BlockUtil.createABlock(world, Material.STONE, 2, 6, 1 + SURFACE, -2 + (6 * team.getNumberOfTeam())));
		blocks.add(BlockUtil.createABlock(world, randomBlocks.get(0).getMaterial(), randomBlocks.get(0).getData(), 6, 1 + SURFACE, -1 + (6 * team.getNumberOfTeam())));
		blocks.add(BlockUtil.createABlock(world, Material.STONE, 2, 6, 1 + SURFACE, 0 + (6 * team.getNumberOfTeam())));
		blocks.add(BlockUtil.createABlock(world, randomBlocks.get(1).getMaterial(), randomBlocks.get(1).getData(), 6, 1 + SURFACE, 1 + (6 * team.getNumberOfTeam())));
		blocks.add(BlockUtil.createABlock(world, Material.STONE, 2, 6, 1 + SURFACE, 2 + (6 * team.getNumberOfTeam())));
		for (int i = -2; i < 3; i++) {
			//Puis 2 lignes de Vide
			blocks.add(BlockUtil.createABlock(world, Material.AIR, 1, 5, 1 + SURFACE, i + (6 * team.getNumberOfTeam())));
			blocks.add(BlockUtil.createABlock(world, Material.AIR, 1, 4, 1 + SURFACE, i + (6 * team.getNumberOfTeam())));
			//La 1ere ligne de Vide
			blocks.add(BlockUtil.createABlock(world, Material.AIR, 1, 2, 1 + SURFACE, i + (6 * team.getNumberOfTeam())));
		}
		//Création des 2 Blocks à placer
		Block placingBlocks1 = BlockUtil.createABlock(world, Material.AIR, 1, 3, 1 + SURFACE, -1 + (6 * team.getNumberOfTeam()));
		Block placingBlocks2 = BlockUtil.createABlock(world, Material.AIR, 1, 3, 1 + SURFACE, 1 + (6 * team.getNumberOfTeam()));

		//Création de la ligne avec les blocks à placer
		//ps : les blocks à placer ne font pas partie de l'Autel
		blocks.add(BlockUtil.createABlock(world, Material.AIR, 1, 3, 1 + SURFACE, -2 + (6 * team.getNumberOfTeam())));
		blocks.add(BlockUtil.createABlock(world, Material.AIR, 1, 3, 1 + SURFACE, 0 + (6 * team.getNumberOfTeam())));
		blocks.add(BlockUtil.createABlock(world, Material.AIR, 1, 3, 1 + SURFACE, 2 + (6 * team.getNumberOfTeam())));

		//Sauvegarde des Blocks à placer
		placingBlocks.add(placingBlocks1);
		placingBlocks.add(placingBlocks2);
	}

	/**
	 * Créer le 3ieme étage de l'Autel
	 * 
	 * @param world le Monde
	 * 
	 *            Disposition des Blocks : [GGGGG] [VVVVV] [VVVVV] [VVVVV] [VVVVV] avec G = Granite, V = Vide
	 */
	private void createThirdFloorOfAltar(World world) {
		for (int i = -2; i < 3; i++) {
			//Création de la ligne de granite
			blocks.add(BlockUtil.createABlock(world, Material.STONE, 2, 6, 2 + SURFACE, i + (6 * team.getNumberOfTeam())));
			//Puis 4 lignes de Vide
			blocks.add(BlockUtil.createABlock(world, Material.AIR, 1, 5, 2 + SURFACE, i + (6 * team.getNumberOfTeam())));
			blocks.add(BlockUtil.createABlock(world, Material.AIR, 1, 4, 2 + SURFACE, i + (6 * team.getNumberOfTeam())));
			blocks.add(BlockUtil.createABlock(world, Material.AIR, 1, 3, 2 + SURFACE, i + (6 * team.getNumberOfTeam())));
			blocks.add(BlockUtil.createABlock(world, Material.AIR, 1, 2, 2 + SURFACE, i + (6 * team.getNumberOfTeam())));
		}
	}

}
