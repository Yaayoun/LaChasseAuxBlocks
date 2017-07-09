package com.yaadanial.lachasseauxblocks.plugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Altar {

	/** Liste des Blocks de l'Autel **/
	private List<Block> blocks = new ArrayList<Block>();

	/** Liste des Blocks à placer dans l'Autel **/
	private List<Block> placingBlocks = new ArrayList<Block>();

	/** Liste des Blocks Aléatoires dans l'Autel **/
	private List<Block> randomBlocks = new ArrayList<Block>();

	public List<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}

	public List<Block> getPlacingBlocks() {
		return placingBlocks;
	}

	public void setPlacingBlocks(List<Block> placingBlocks) {
		this.placingBlocks = placingBlocks;
	}

	public List<Block> getRandomBlocks() {
		return randomBlocks;
	}

	public void setRandomBlocks(List<Block> randomBlocks) {
		this.randomBlocks = randomBlocks;
	}

	/**
	 * Créer l'Autel
	 */
	public void generate(Player pl) {
		World w = pl.getWorld();

		createBaseOfAltar(pl, w);
		createFirstFloorOfAltar(pl, w);
		createSecondFloorOfAltar(pl, w);
		createThirdFloorOfAltar(pl, w);
	}

	/**
	 * Créer la Base de l'Autel
	 * 
	 * @param pl le Joueur
	 * @param w le Monde
	 * 
	 *            Disposition des Blocks : [AAAAA] [AAAAA] [AAAAA] [AAAAA] [AAAAA] avec A = Andésite
	 */
	private void createBaseOfAltar(Player pl, World w) {
		for (int i = 0; i < 5; i++) {
			for (int j = -2; j < 3; j++) {
				blocks.add(BlockUtil.createABlock(pl, w, Material.STONE, 6, 2 + i, -1, j));
			}
		}
	}

	/**
	 * Créer le 1er étage de l'Autel
	 * 
	 * @param pl le Joueur
	 * @param w le Monde
	 * 
	 *            Disposition des Blocks : [GGGGG] [VVVVV] [VVVVV] [VAVAV] [VVVVV] avec G = Granite, V = Vide, A = Andésite
	 */
	private void createFirstFloorOfAltar(Player pl, World w) {
		for (int i = -2; i < 3; i++) {
			//Création de la ligne de granite
			blocks.add(BlockUtil.createABlock(pl, w, Material.STONE, 2, 6, 0, i));
			//Puis 2 lignes de Vide
			blocks.add(BlockUtil.createABlock(pl, w, Material.AIR, 1, 5, 0, i));
			blocks.add(BlockUtil.createABlock(pl, w, Material.AIR, 1, 4, 0, i));
			//La 1ere ligne de Vide
			blocks.add(BlockUtil.createABlock(pl, w, Material.AIR, 1, 2, 0, i));
		}
		//Création de la ligne avec les estrades
		blocks.add(BlockUtil.createABlock(pl, w, Material.AIR, 1, 3, 0, -2));
		blocks.add(BlockUtil.createABlock(pl, w, Material.STONE, 6, 3, 0, -1));
		blocks.add(BlockUtil.createABlock(pl, w, Material.AIR, 1, 3, 0, 0));
		blocks.add(BlockUtil.createABlock(pl, w, Material.STONE, 6, 3, 0, 1));
		blocks.add(BlockUtil.createABlock(pl, w, Material.AIR, 1, 3, 0, 2));
	}

	/**
	 * Créer le 2nd étage de l'Autel
	 * 
	 * @param pl le Joueur
	 * @param w le Monde
	 * 
	 *            Disposition des Blocks : [GRGRG] [VVVVV] [VVVVV] [VPVPV] [VVVVV] avec G = Granite, V = Vide, R = Random, P = block à placer
	 */
	private void createSecondFloorOfAltar(Player pl, World w) {
		//Tirage des 2 random Blocks
		Block randomBlock1 = BlockUtil.createRandomBlock(pl, w, 6, 1, -1);
		Block randomBlock2 = BlockUtil.createRandomBlock(pl, w, 6, 1, 1);

		//Création de la ligne Granite/block aléatoire
		blocks.add(BlockUtil.createABlock(pl, w, Material.STONE, 2, 6, 1, -2));
		blocks.add(randomBlock1);
		blocks.add(BlockUtil.createABlock(pl, w, Material.STONE, 2, 6, 1, 0));
		blocks.add(randomBlock2);
		blocks.add(BlockUtil.createABlock(pl, w, Material.STONE, 2, 6, 1, 2));
		for (int i = -2; i < 3; i++) {
			//Puis 2 lignes de Vide
			blocks.add(BlockUtil.createABlock(pl, w, Material.AIR, 1, 5, 1, i));
			blocks.add(BlockUtil.createABlock(pl, w, Material.AIR, 1, 4, 1, i));
			//La 1ere ligne de Vide
			blocks.add(BlockUtil.createABlock(pl, w, Material.AIR, 1, 2, 1, i));
		}
		//Création des 2 Blocks à Placer
		Block placingBlocks1 = BlockUtil.createABlock(pl, w, Material.AIR, 1, 3, 1, -1);
		Block placingBlocks2 = BlockUtil.createABlock(pl, w, Material.AIR, 1, 3, 1, 1);

		//Création de la ligne avec les blocks à placer
		//ps : les blocks à placer ne font pas partie de l'Autel
		blocks.add(BlockUtil.createABlock(pl, w, Material.AIR, 1, 3, 1, -2));
		blocks.add(BlockUtil.createABlock(pl, w, Material.AIR, 1, 3, 1, 0));
		blocks.add(BlockUtil.createABlock(pl, w, Material.AIR, 1, 3, 1, 2));

		//Sauvegarde des Blocks Aléatoires
		randomBlocks.add(randomBlock1);
		randomBlocks.add(randomBlock2);

		//Sauvegarde des Blocks à placer
		placingBlocks.add(placingBlocks1);
		placingBlocks.add(placingBlocks2);
	}

	/**
	 * Créer le 3ieme étage de l'Autel
	 * 
	 * @param pl le Joueur
	 * @param w le Monde
	 * 
	 *            Disposition des Blocks : [GGGGG] [VVVVV] [VVVVV] [VVVVV] [VVVVV] avec G = Granite, V = Vide
	 */
	private void createThirdFloorOfAltar(Player pl, World w) {
		for (int i = -2; i < 3; i++) {
			//Création de la ligne de granite
			blocks.add(BlockUtil.createABlock(pl, w, Material.STONE, 2, 6, 2, i));
			//Puis 4 lignes de Vide
			blocks.add(BlockUtil.createABlock(pl, w, Material.AIR, 1, 5, 2, i));
			blocks.add(BlockUtil.createABlock(pl, w, Material.AIR, 1, 4, 2, i));
			blocks.add(BlockUtil.createABlock(pl, w, Material.AIR, 1, 3, 2, i));
			blocks.add(BlockUtil.createABlock(pl, w, Material.AIR, 1, 2, 2, i));
		}
	}

}
