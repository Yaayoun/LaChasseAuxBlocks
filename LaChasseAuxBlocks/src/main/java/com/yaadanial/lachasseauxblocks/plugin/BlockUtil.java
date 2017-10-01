package com.yaadanial.lachasseauxblocks.plugin;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BlockUtil {

	/**
	 * Créer un Block de Granite
	 * 
	 * @param pl le Joueur
	 * @param w le Monde
	 * @param type le type du block à créer
	 * @param data l'extension du block à créer
	 * @param x la position en X du Block
	 * @param y la position en Y du Block
	 * @param z la position en Z du Block
	 * @return le block créé
	 */
	public static Block createABlock(Player pl, World w, Material type, int data, int x, int y, int z) {
		Block block = w.getBlockAt(pl.getLocation().getBlockX() + x, pl.getLocation().getBlockY() + y, pl.getLocation().getBlockZ() + z);
		block.setType(type);
		block.setData((byte) data);
		return block;
	}

	/**
	 * Créer un Block Aléatoire
	 * 
	 * @param pl le Joueur
	 * @param w le Monde
	 * @param x la position en X du Block
	 * @param y la position en Y du Block
	 * @param z la position en Z du Block
	 * @return le block créé
	 */
	public static Block createRandomBlock(Player pl, World w, int x, int y, int z) {
		Block block = w.getBlockAt(pl.getLocation().getBlockX() + x, pl.getLocation().getBlockY() + y, pl.getLocation().getBlockZ() + z);
		Random random = new Random();
		Material[] materials = Material.values();
		int size = materials.length;
		int index = random.nextInt(size);
		Material randomMaterial = materials[index];
		block.setType(randomMaterial);
		return block;
	}
}
