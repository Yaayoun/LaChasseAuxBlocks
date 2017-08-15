package com.yaadanial.lachasseauxblocks.plugin;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

/**
 * Classe Utilitaire pour les Blocks
 * 
 * @author Yaadanial
 *
 */
public class BlockUtil {

	/**
	 * Créer un Block de Granite
	 * 
	 * @param world le Monde
	 * @param type le type du block à créer
	 * @param data l'extension du block à créer
	 * @param x la position en X du Block
	 * @param y la position en Y du Block
	 * @param z la position en Z du Block
	 * @return le block créé
	 */
	public static Block createABlock(World world, Material type, int data, int x, int y, int z) {
		Block block = world.getBlockAt(x, y, z);
		block.setType(type);
		block.setData((byte) data);
		return block;
	}
}
