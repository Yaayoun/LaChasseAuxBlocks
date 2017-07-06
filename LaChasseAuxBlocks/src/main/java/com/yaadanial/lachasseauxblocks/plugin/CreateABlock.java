package com.yaadanial.lachasseauxblocks.plugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class CreateABlock {

	public void generateCube(Location loc, int length) {
		// Set one corner of the cube to the given location.
		// Uses getBlockN() instead of getN() to avoid casting to an int later.
		int x1 = loc.getBlockX();
		int y1 = loc.getBlockY();
		int z1 = loc.getBlockZ();

		// Figure out the opposite corner of the cube by taking the corner and
		// adding length to all coordinates.
		int x2 = x1 + length;
		int y2 = y1 + length;
		int z2 = z1 + length;

		World world = loc.getWorld();

		// Loop over the cube in the x dimension.
		for (int xPoint = x1; xPoint <= x2; xPoint++) {
			// Loop over the cube in the y dimension.
			for (int yPoint = y1; yPoint <= y2; yPoint++) {
				// Loop over the cube in the z dimension.
				for (int zPoint = z1; zPoint <= z2; zPoint++) {
					// Get the block that we are currently looping over.
					Block currentBlock = world.getBlockAt(xPoint, yPoint, zPoint);
					// Set the block to type 57 (Diamond block!)
					currentBlock.setType(Material.DIAMOND_BLOCK);
				}
			}
		}
	}
}
