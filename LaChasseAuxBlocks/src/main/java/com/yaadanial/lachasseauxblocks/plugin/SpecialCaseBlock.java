package com.yaadanial.lachasseauxblocks.plugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

/**
 * Classe qui permet de savoir quel block ont un sens
 * 
 * @author Yaadanial
 *
 */
public class SpecialCaseBlock {
	private List<BlockTypeData> specialCaseBlock;

	public SpecialCaseBlock() {
		this.specialCaseBlock = new ArrayList<BlockTypeData>();
		init();
	}

	private void init() {
		//@formatter:off
		specialCaseBlock.add(new BlockTypeData(Material.DISPENSER, 0, "Dispenser"));							// (ID=23)Dispenser
		specialCaseBlock.add(new BlockTypeData(Material.PISTON_STICKY_BASE, 0, "Sticky Piston"));				// (ID=29)Sticky Piston
		specialCaseBlock.add(new BlockTypeData(Material.PISTON_BASE, 0, "Piston"));								// (ID=33)Piston
		specialCaseBlock.add(new BlockTypeData(Material.WOOD_STAIRS, 0, "Oak Wood Stairs"));					// (ID=53)Oak Wood Stairs
		specialCaseBlock.add(new BlockTypeData(Material.CHEST, 0, "Chest"));									// (ID=54)Chest
		specialCaseBlock.add(new BlockTypeData(Material.FURNACE, 0, "Furnace"));								// (ID=61)Furnace
		specialCaseBlock.add(new BlockTypeData(Material.SIGN_POST, 0, "Sign"));									// (ID=63)Sign
		specialCaseBlock.add(new BlockTypeData(Material.COBBLESTONE_STAIRS, 0, "CobbleStone Stairs"));			// (ID=67)CobbleStone Stairs
		//specialCaseBlock.add(new BlockTypeData(Material.LEVER, 0, "Lever"));									// (ID=69)Lever
		specialCaseBlock.add(new BlockTypeData(Material.JACK_O_LANTERN, 0, "Jack'o'Lantern"));					// (ID=91)Jack'o'Lantern
		specialCaseBlock.add(new BlockTypeData(Material.TRAP_DOOR, 0, "Trap Door"));							// (ID=96)Trap Door
		specialCaseBlock.add(new BlockTypeData(Material.FENCE_GATE, 0, "Fence Gate"));							// (ID=107)Fence Gate
		specialCaseBlock.add(new BlockTypeData(Material.BRICK_STAIRS, 0, "Brick Stairs"));						// (ID=108)Brick Stairs
		specialCaseBlock.add(new BlockTypeData(Material.SMOOTH_STAIRS, 0, "Stone Brick Stairs"));				// (ID=109)Stone Brick Stairs
		specialCaseBlock.add(new BlockTypeData(Material.NETHER_BRICK_STAIRS, 0, "Nether Brick Stairs"));		// (ID=114)Nether Brick Stairs
		specialCaseBlock.add(new BlockTypeData(Material.SANDSTONE_STAIRS, 0, "Sand Stone Stairs"));				// (ID=128)Sand Stone Stairs
		specialCaseBlock.add(new BlockTypeData(Material.ENDER_CHEST, 0, "Ender Chest"));						// (ID=130)Ender Chest
		//specialCaseBlock.add(new BlockTypeData(Material.TRIPWIRE_HOOK, 0, "Tripwire Hook"));					// (ID=131)Tripwire Hook
		specialCaseBlock.add(new BlockTypeData(Material.SPRUCE_WOOD_STAIRS, 0, "Spruce Wood Stairs"));			// (ID=134)Spruce Wood Stairs
		specialCaseBlock.add(new BlockTypeData(Material.BIRCH_WOOD_STAIRS, 0, "Birch Wood Stairs"));			// (ID=135)Birch Wood Stairs
		specialCaseBlock.add(new BlockTypeData(Material.JUNGLE_WOOD_STAIRS, 0, "Jungle Wood Stairs"));			// (ID=136)Jungle Wood Stairs
		specialCaseBlock.add(new BlockTypeData(Material.FLOWER_POT, 0, "Flower Pot"));							// (ID=140)Flower Pot
		specialCaseBlock.add(new BlockTypeData(Material.ANVIL, 0, "Anvil"));									// (ID=145)Anvil
		specialCaseBlock.add(new BlockTypeData(Material.TRAPPED_CHEST, 0, "Trapped Chest"));					// (ID=146)Trapped Chest
		specialCaseBlock.add(new BlockTypeData(Material.REDSTONE_COMPARATOR_OFF, 0, "Comparator"));				// (ID=149)Comparator
		specialCaseBlock.add(new BlockTypeData(Material.QUARTZ_STAIRS, 0, "Quartz Stairs"));					// (ID=156)Quartz Stairs
		specialCaseBlock.add(new BlockTypeData(Material.DROPPER, 0, "Dropper"));								// (ID=158)Dropper
		specialCaseBlock.add(new BlockTypeData(Material.ACACIA_STAIRS, 0, "Acacia Stairs"));					// (ID=163)Acacia Stairs
		specialCaseBlock.add(new BlockTypeData(Material.DARK_OAK_STAIRS, 0, "Dark Oak Stairs"));				// (ID=164)Dark Oak Stairs
		specialCaseBlock.add(new BlockTypeData(Material.IRON_TRAPDOOR, 0, "Iron Trapdoor"));					// (ID=167)Iron Trapdoor
		specialCaseBlock.add(new BlockTypeData(Material.RED_SANDSTONE_STAIRS, 0, "Red Sandstone Stairs"));		// (ID=180)Red Sandstone Stairs
		specialCaseBlock.add(new BlockTypeData(Material.SPRUCE_FENCE_GATE, 0, "Spruce Fence Gate"));			// (ID=183)Spruce Fence Gate
		specialCaseBlock.add(new BlockTypeData(Material.BIRCH_FENCE_GATE, 0, "Birch Fence Gate"));				// (ID=184)Birch Fence Gate
		specialCaseBlock.add(new BlockTypeData(Material.JUNGLE_FENCE_GATE, 0, "Jungle Fence Gate"));			// (ID=185)Jungle Fence Gate
		specialCaseBlock.add(new BlockTypeData(Material.DARK_OAK_FENCE_GATE, 0, "Dark Oak Fence Gate"));		// (ID=186)Dark Oak Fence Gate
		specialCaseBlock.add(new BlockTypeData(Material.ACACIA_FENCE_GATE, 0, "Acacia Fence Gate"));			// (ID=187)Acacia Fence Gate
		specialCaseBlock.add(new BlockTypeData(Material.PURPUR_STAIRS, 0, "Purpur Stairs"));					// (ID=203)Purpur Stairs
		specialCaseBlock.add(new BlockTypeData(Material.END_ROD, 0, "End Rod"));									// (ID=198)End Rod
		//@formatter:on
	}

	public List<BlockTypeData> getSpecialCaseBlock() {
		return specialCaseBlock;
	}

	public void setSpecialCaseBlock(List<BlockTypeData> specialCaseBlock) {
		this.specialCaseBlock = specialCaseBlock;
	}
}
