package com.yaadanial.lachasseauxblocks.plugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

/**
 * Classe qui permet de savoir quel block peuvent être demandé
 * 
 * @author Yaadanial
 *
 */
public class AskingBlock {

	private List<BlockTypeData> askingBlock;

	public AskingBlock() {
		this.askingBlock = new ArrayList<BlockTypeData>();
		init();
	}

	private void init() {
		//@formatter:off
		askingBlock.add(new BlockTypeData(Material.STONE, 0, "Stone"));										// (ID=1)Stone
		askingBlock.add(new BlockTypeData(Material.STONE, 1, "Granite"));									// (ID=1)Granite
		askingBlock.add(new BlockTypeData(Material.STONE, 2, "Polished Granite"));							// (ID=1)Polished Granite
		askingBlock.add(new BlockTypeData(Material.STONE, 3, "Diorite"));									// (ID=1)Diorite
		askingBlock.add(new BlockTypeData(Material.STONE, 4, "Polished Diorite"));							// (ID=1)Polished Diorite
		askingBlock.add(new BlockTypeData(Material.STONE, 5, "Andesite"));									// (ID=1)Andesite
		askingBlock.add(new BlockTypeData(Material.STONE, 6, "Polished Andesite"));							// (ID=1)Polished Andesite
		askingBlock.add(new BlockTypeData(Material.GRASS, 0, "Grass"));										// (ID=2)Grass
		askingBlock.add(new BlockTypeData(Material.DIRT, 0, "Dirt"));										// (ID=3)Dirt
		askingBlock.add(new BlockTypeData(Material.DIRT, 1, "Coarse Dirt"));								// (ID=3)Coarse Dirt
		askingBlock.add(new BlockTypeData(Material.DIRT, 2, "Podzol"));										// (ID=3)Podzol
		askingBlock.add(new BlockTypeData(Material.COBBLESTONE, 0, "CobbleStone"));							// (ID=4)CobbleStone
		askingBlock.add(new BlockTypeData(Material.WOOD, 0, "Oak Planks"));									// (ID=5)Oak Planks
		askingBlock.add(new BlockTypeData(Material.WOOD, 1, "Spruce Planks"));								// (ID=5)Spruce Planks
		askingBlock.add(new BlockTypeData(Material.WOOD, 2, "Birch Planks"));								// (ID=5)Birch Planks
		askingBlock.add(new BlockTypeData(Material.WOOD, 3, "Jungle Planks"));								// (ID=5)Jungle Planks
		askingBlock.add(new BlockTypeData(Material.WOOD, 4, "Jungle Planks"));								// (ID=5)Acacia Planks
		askingBlock.add(new BlockTypeData(Material.WOOD, 5, "Dark Planks"));								// (ID=5)Dark Planks
		askingBlock.add(new BlockTypeData(Material.SAND, 0, "Sand"));										// (ID=12)Sand
		askingBlock.add(new BlockTypeData(Material.SAND, 1, "Red Sand"));									// (ID=12)Red Sand
		askingBlock.add(new BlockTypeData(Material.GRAVEL, 0, "Gravel"));									// (ID=13)Gravel
		askingBlock.add(new BlockTypeData(Material.GOLD_ORE, 0, "Gold Ore"));								// (ID=14)Gold Ore
		askingBlock.add(new BlockTypeData(Material.IRON_ORE, 0, "Iron Ore"));								// (ID=15)Iron Ore
		askingBlock.add(new BlockTypeData(Material.COAL_ORE, 0, "Coal Ore"));								// (ID=16)Coal Ore
		askingBlock.add(new BlockTypeData(Material.LOG, 0, "Oak Wood"));									// (ID=17)Oak Wood
		askingBlock.add(new BlockTypeData(Material.LOG, 1, "Spruce Wood"));									// (ID=17)Spruce Wood
		askingBlock.add(new BlockTypeData(Material.LOG, 2, "Birch Wood"));									// (ID=17)Birch Wood
		askingBlock.add(new BlockTypeData(Material.LOG, 3, "Jungle Wood"));									// (ID=17)Jungle Wood
		//askingBlock.add(new BlockTypeData(Material.LEAVES, 0, "Oak Leaves"));								// (ID=18)Oak Leaves
		//askingBlock.add(new BlockTypeData(Material.LEAVES, 1, "Spruce Leaves"));							// (ID=18)Spruce Leaves
		//askingBlock.add(new BlockTypeData(Material.LEAVES, 2, "Birch Leaves"));								// (ID=18)Birch Leaves
		//askingBlock.add(new BlockTypeData(Material.LEAVES, 3, "Jungle Leaves"));							// (ID=18)Jungle Leaves
		askingBlock.add(new BlockTypeData(Material.SPONGE, 0, "Sponge"));									// (ID=19)Sponge
		askingBlock.add(new BlockTypeData(Material.SPONGE, 1, "Wet Sponge"));								// (ID=19)Wet Sponge
		askingBlock.add(new BlockTypeData(Material.GLASS, 0, "Glass"));										// (ID=20)Glass
		askingBlock.add(new BlockTypeData(Material.LAPIS_ORE, 0, "Lapis Ore"));								// (ID=21)Lapis Ore
		askingBlock.add(new BlockTypeData(Material.LAPIS_BLOCK, 0, "Lapis Ore"));							// (ID=22)Lapis Block
		askingBlock.add(new BlockTypeData(Material.DISPENSER, 0, "Dispenser"));								// (ID=23)Dispenser
		askingBlock.add(new BlockTypeData(Material.SANDSTONE, 0, "SandStone"));								// (ID=24)SandStone
		askingBlock.add(new BlockTypeData(Material.SANDSTONE, 1, "Chiseled SandStone"));					// (ID=24)Chiseled SandStone
		askingBlock.add(new BlockTypeData(Material.SANDSTONE, 2, "Sprooth SandStone"));						// (ID=24)Sprooth SandStone
		askingBlock.add(new BlockTypeData(Material.NOTE_BLOCK, 0, "Note Block"));							// (ID=25)Note Block
		askingBlock.add(new BlockTypeData(Material.POWERED_RAIL, 0, "Powered Rail"));						// (ID=27)Powered Rail
		askingBlock.add(new BlockTypeData(Material.DETECTOR_RAIL, 0, "Detector_Rail"));						// (ID=28)Detector_Rail
		askingBlock.add(new BlockTypeData(Material.PISTON_STICKY_BASE, 0, "Sticky Piston"));				// (ID=29)Sticky Piston
		askingBlock.add(new BlockTypeData(Material.WEB, 0, "Cobweb"));										// (ID=30)Cobweb
		askingBlock.add(new BlockTypeData(Material.PISTON_BASE, 0, "Piston"));								// (ID=33)Piston
		askingBlock.add(new BlockTypeData(Material.WOOL, 0, "White Wool"));									// (ID=35)White Wool
		askingBlock.add(new BlockTypeData(Material.WOOL, 1, "Orange Wool"));								// (ID=35)Orange Wool
		askingBlock.add(new BlockTypeData(Material.WOOL, 2, "Magenta Wool"));								// (ID=35)Magenta Wool
		askingBlock.add(new BlockTypeData(Material.WOOL, 3, "Light Blue Wool"));							// (ID=35)Light Blue Wool
		askingBlock.add(new BlockTypeData(Material.WOOL, 4, "Yellow Wool"));								// (ID=35)Yellow Wool
		askingBlock.add(new BlockTypeData(Material.WOOL, 5, "Lime Wool"));									// (ID=35)Lime Wool
		askingBlock.add(new BlockTypeData(Material.WOOL, 6, "Pink Wool"));									// (ID=35)Pink Wool
		askingBlock.add(new BlockTypeData(Material.WOOL, 7, "Gray Wool"));									// (ID=35)Gray Wool
		askingBlock.add(new BlockTypeData(Material.WOOL, 8, "Light Gray Wool"));							// (ID=35)Light Gray Wool
		askingBlock.add(new BlockTypeData(Material.WOOL, 9, "Cyan Wool"));									// (ID=35)Cyan Wool
		askingBlock.add(new BlockTypeData(Material.WOOL, 10, "Purple Wool"));								// (ID=35)Purple Wool
		askingBlock.add(new BlockTypeData(Material.WOOL, 11, "Blue Wool"));									// (ID=35)Blue Wool
		askingBlock.add(new BlockTypeData(Material.WOOL, 12, "Brown Wool"));								// (ID=35)Brown Wool
		askingBlock.add(new BlockTypeData(Material.WOOL, 13, "Green Wool"));								// (ID=35)Green Wool
		askingBlock.add(new BlockTypeData(Material.WOOL, 14, "Red Wool"));									// (ID=35)Red Wool
		askingBlock.add(new BlockTypeData(Material.WOOL, 15, "Black Wool"));								// (ID=35)Black Wool
		askingBlock.add(new BlockTypeData(Material.GOLD_BLOCK, 0, "Gold Block"));							// (ID=41)Gold Block
		askingBlock.add(new BlockTypeData(Material.IRON_BLOCK, 0, "Iron Block"));							// (ID=42)Iron Block
		askingBlock.add(new BlockTypeData(Material.STEP, 0, "Stone Slab"));									// (ID=44)Stone Slab
		askingBlock.add(new BlockTypeData(Material.STEP, 1, "SandStone Slab"));								// (ID=44)SandStone Slab
		askingBlock.add(new BlockTypeData(Material.STEP, 3, "CubbleStone Slab"));							// (ID=44)CubbleStone Slab
		askingBlock.add(new BlockTypeData(Material.STEP, 4, "Brick Slab"));									// (ID=44)Brick Slab
		askingBlock.add(new BlockTypeData(Material.STEP, 5, "StoneBrick Slab"));							// (ID=44)StoneBrick Slab
		askingBlock.add(new BlockTypeData(Material.STEP, 6, "Neither Brick Slab"));							// (ID=44)Neither Brick Slab
		askingBlock.add(new BlockTypeData(Material.STEP, 7, "Quartz Slab"));								// (ID=44)Quartz Slab
		askingBlock.add(new BlockTypeData(Material.BRICK, 0, "Brick"));										// (ID=45)Brick
		askingBlock.add(new BlockTypeData(Material.TNT, 0, "TNT"));											// (ID=46)TNT
		askingBlock.add(new BlockTypeData(Material.BOOKSHELF, 0, "Bookshelf"));								// (ID=47)Bookshelf
		askingBlock.add(new BlockTypeData(Material.MOSSY_COBBLESTONE, 0, "Mossy CobbleStone"));				// (ID=48)Mossy CobbleStone
		askingBlock.add(new BlockTypeData(Material.OBSIDIAN, 0, "Obsidian"));								// (ID=49)Obsidian
		askingBlock.add(new BlockTypeData(Material.TORCH, 0, "Torch"));										// (ID=50)Torch
		askingBlock.add(new BlockTypeData(Material.WOOD_STAIRS, 0, "Oak Wood Stairs"));						// (ID=53)Oak Wood Stairs
		askingBlock.add(new BlockTypeData(Material.CHEST, 0, "Chest"));										// (ID=54)Chest
		askingBlock.add(new BlockTypeData(Material.DIAMOND_ORE, 0, "Diamond Ore"));							// (ID=56)Diamond Ore
		askingBlock.add(new BlockTypeData(Material.DIAMOND_BLOCK, 0, "Diamond Block"));						// (ID=57)Diamond Block
		askingBlock.add(new BlockTypeData(Material.WORKBENCH, 0, "Workbench"));								// (ID=58)Workbench
		askingBlock.add(new BlockTypeData(Material.FURNACE, 0, "Furnace"));									// (ID=61)Furnace
		askingBlock.add(new BlockTypeData(Material.SIGN_POST, 0, "Sign"));									// (ID=63)Sign
		askingBlock.add(new BlockTypeData(Material.RAILS, 0, "Rails"));										// (ID=66)Rails
		askingBlock.add(new BlockTypeData(Material.COBBLESTONE_STAIRS, 0, "CobbleStone Stairs"));			// (ID=67)CobbleStone Stairs
		//askingBlock.add(new BlockTypeData(Material.LEVER, 0, "Lever"));									// (ID=69)Lever
		askingBlock.add(new BlockTypeData(Material.STONE_PLATE, 0, "Stone Plate"));							// (ID=70)Stone Plate
		askingBlock.add(new BlockTypeData(Material.WOOD_PLATE, 0, "Wood Plate"));							// (ID=72)Wood Plate
		askingBlock.add(new BlockTypeData(Material.REDSTONE_ORE, 0, "Redstone Ore"));						// (ID=73)Redstone Ore
		askingBlock.add(new BlockTypeData(Material.REDSTONE_TORCH_ON, 0, "Redstone Torch"));				// (ID=76)Redstone Torch
		askingBlock.add(new BlockTypeData(Material.ICE, 0, "Ice"));											// (ID=79)Ice
		askingBlock.add(new BlockTypeData(Material.SNOW_BLOCK, 0, "Snow Block"));							// (ID=80)Snow Block
		askingBlock.add(new BlockTypeData(Material.CLAY, 0, "Clay Block"));									// (ID=82)Clay Block
		askingBlock.add(new BlockTypeData(Material.JUKEBOX, 0, "JukeBox"));									// (ID=84)JukeBox
		askingBlock.add(new BlockTypeData(Material.FENCE, 0, "Oak Fence"));									// (ID=85)Oak Fence
		askingBlock.add(new BlockTypeData(Material.PUMPKIN, 0, "Pumpkin"));									// (ID=86)Pumpkin
		askingBlock.add(new BlockTypeData(Material.NETHERRACK, 0, "Netherrack"));							// (ID=87)Netherrack
		askingBlock.add(new BlockTypeData(Material.SOUL_SAND, 0, "Soul Sand"));								// (ID=88)Soul Sand
		askingBlock.add(new BlockTypeData(Material.GLOWSTONE, 0, "GlowStone"));								// (ID=89)GlowStone
		askingBlock.add(new BlockTypeData(Material.JACK_O_LANTERN, 0, "Jack'o'Lantern"));					// (ID=91)Jack'o'Lantern
		askingBlock.add(new BlockTypeData(Material.CAKE_BLOCK, 0, "Cake"));									// (ID=92)Cake
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS, 0, "White Stained Glass"));				// (ID=95)White Stained Glass
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS, 1, "Orange Stained Glass"));				// (ID=95)Orange Stained Glass
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS, 2, "Magenta Stained Glass"));				// (ID=95)Magenta Stained Glass
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS, 3, "Light Blue Stained Glass"));			// (ID=95)Light Blue Stained Glass
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS, 4, "Yellow Stained Glass"));				// (ID=95)Yellow Stained Glass
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS, 5, "Lime Stained Glass"));				// (ID=95)Lime Stained Glass
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS, 6, "Pink Stained Glass"));				// (ID=95)Pink Stained Glass
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS, 7, "Gray Stained Glass"));				// (ID=95)Gray Stained Glass
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS, 8, "Light Gray Stained Glass"));			// (ID=95)Light Gray Stained Glass
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS, 9, "Cyan Stained Glass"));				// (ID=95)Cyan Stained Glass
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS, 10, "Purple Stained Glass"));				// (ID=95)Purple Stained Glass
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS, 11, "Blue Stained Glass"));				// (ID=95)Blue Stained Glass
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS, 12, "Brown Stained Glass"));				// (ID=95)Brown Stained Glass
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS, 13, "Green Stained Glass"));				// (ID=95)Green Stained Glass
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS, 14, "Red Stained Glass"));				// (ID=95)Red Stained Glass
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS, 15, "Black Stained Glass"));				// (ID=95)Black Stained Glass
		askingBlock.add(new BlockTypeData(Material.TRAP_DOOR, 0, "Trap Door"));								// (ID=96)Trap Door
		askingBlock.add(new BlockTypeData(Material.SMOOTH_BRICK, 0, "Stone Brick"));						// (ID=98)Stone Brick
		askingBlock.add(new BlockTypeData(Material.SMOOTH_BRICK, 1, "Mossy Stone Brick"));					// (ID=98)Mossy Stone Brick
		askingBlock.add(new BlockTypeData(Material.SMOOTH_BRICK, 2, "Cracked Stone Brick"));				// (ID=98)Cracked Stone Brick
		askingBlock.add(new BlockTypeData(Material.SMOOTH_BRICK, 3, "Chiseled Stone Brick"));				// (ID=98)Chiseled Stone Brick
		askingBlock.add(new BlockTypeData(Material.HUGE_MUSHROOM_1, 0, "Brown Mushroom Block"));			// (ID=99)Brown Mushroom Block
		askingBlock.add(new BlockTypeData(Material.HUGE_MUSHROOM_2, 0, "Red Mushroom Block"));				// (ID=100)Red Mushroom Block
		askingBlock.add(new BlockTypeData(Material.IRON_FENCE, 0, "Iron Fence"));							// (ID=101)Iron Fence
		askingBlock.add(new BlockTypeData(Material.THIN_GLASS, 0, "Glass Pane"));							// (ID=102)Glass Pane
		askingBlock.add(new BlockTypeData(Material.MELON_BLOCK, 0, "Melon"));								// (ID=103)Melon Block
		askingBlock.add(new BlockTypeData(Material.FENCE_GATE, 0, "Fence Gate"));							// (ID=107)Fence Gate
		askingBlock.add(new BlockTypeData(Material.BRICK_STAIRS, 0, "Brick Stairs"));						// (ID=108)Brick Stairs
		askingBlock.add(new BlockTypeData(Material.SMOOTH_STAIRS, 0, "Stone Brick Stairs"));				// (ID=109)Stone Brick Stairs
		askingBlock.add(new BlockTypeData(Material.MYCEL, 0, "Mycelium"));									// (ID=110)Mycelium
		askingBlock.add(new BlockTypeData(Material.NETHER_BRICK, 0, "Nether Brick"));						// (ID=112)Nether Brick
		askingBlock.add(new BlockTypeData(Material.NETHER_FENCE, 0, "Nether Fence"));						// (ID=113)Nether Fence
		askingBlock.add(new BlockTypeData(Material.NETHER_BRICK_STAIRS, 0, "Nether Brick Stairs"));			// (ID=114)Nether Brick Stairs
		askingBlock.add(new BlockTypeData(Material.ENCHANTMENT_TABLE, 0, "Enchantment Table"));				// (ID=116)Enchantment Table
		askingBlock.add(new BlockTypeData(Material.BREWING_STAND, 0, "Brewing Stand"));						// (ID=117)Brewing Stand
		askingBlock.add(new BlockTypeData(Material.CAULDRON, 0, "Cauldron"));								// (ID=118)Cauldron
		askingBlock.add(new BlockTypeData(Material.ENDER_STONE, 0, "Ender Stone"));							// (ID=121)Ender Stone
		askingBlock.add(new BlockTypeData(Material.DRAGON_EGG, 0, "Dragon Egg"));							// (ID=122)Dragon Egg
		askingBlock.add(new BlockTypeData(Material.REDSTONE_LAMP_OFF, 0, "Redstone Lamp"));					// (ID=123)Redstone Lamp
		askingBlock.add(new BlockTypeData(Material.WOOD_STEP, 0, "Oak Wood Slab"));							// (ID=123)Oak Wood Slab
		askingBlock.add(new BlockTypeData(Material.WOOD_STEP, 1, "Spruce Wood Slab"));						// (ID=123)Spruce Wood Slab
		askingBlock.add(new BlockTypeData(Material.WOOD_STEP, 2, "Birch Wood Slab"));						// (ID=123)Birch Wood Slab
		askingBlock.add(new BlockTypeData(Material.WOOD_STEP, 3, "Jungle Wood Slab"));						// (ID=123)Jungle Wood Slab
		askingBlock.add(new BlockTypeData(Material.WOOD_STEP, 4, "Acacia Wood Slab"));						// (ID=123)Acacia Wood Slab
		askingBlock.add(new BlockTypeData(Material.WOOD_STEP, 5, "Dark Wood Slab"));						// (ID=123)Dark Wood Slab
		askingBlock.add(new BlockTypeData(Material.SANDSTONE_STAIRS, 0, "Sand Stone Stairs"));				// (ID=128)Sand Stone Stairs
		askingBlock.add(new BlockTypeData(Material.EMERALD_ORE, 0, "Emerald Ore"));							// (ID=129)Emerald Ore
		askingBlock.add(new BlockTypeData(Material.ENDER_CHEST, 0, "Ender Chest"));							// (ID=130)Ender Chest
		//askingBlock.add(new BlockTypeData(Material.TRIPWIRE_HOOK, 0, "Tripwire Hook"));					// (ID=131)Tripwire Hook
		askingBlock.add(new BlockTypeData(Material.EMERALD_BLOCK, 0, "Emerald Block"));						// (ID=133)Emerald Block
		askingBlock.add(new BlockTypeData(Material.SPRUCE_WOOD_STAIRS, 0, "Spruce Wood Stairs"));			// (ID=134)Spruce Wood Stairs
		askingBlock.add(new BlockTypeData(Material.BIRCH_WOOD_STAIRS, 0, "Birch Wood Stairs"));				// (ID=135)Birch Wood Stairs
		askingBlock.add(new BlockTypeData(Material.JUNGLE_WOOD_STAIRS, 0, "Jungle Wood Stairs"));			// (ID=136)Jungle Wood Stairs
		askingBlock.add(new BlockTypeData(Material.BEACON, 0, "Beacon"));									// (ID=138)Beacon
		askingBlock.add(new BlockTypeData(Material.COBBLE_WALL, 0, "Cobble Wall"));							// (ID=139)Cobble Wall
		askingBlock.add(new BlockTypeData(Material.COBBLE_WALL, 1, "Mossy Cobble Wall"));					// (ID=139)Mossy Cobble Wall
		askingBlock.add(new BlockTypeData(Material.FLOWER_POT, 0, "Flower Pot"));							// (ID=140)Flower Pot
		askingBlock.add(new BlockTypeData(Material.ANVIL, 0, "Anvil"));										// (ID=145)Anvil
		askingBlock.add(new BlockTypeData(Material.TRAPPED_CHEST, 0, "Trapped Chest"));						// (ID=146)Trapped Chest
		askingBlock.add(new BlockTypeData(Material.GOLD_PLATE, 0, "Gold Plate"));							// (ID=147)Gold Plate
		askingBlock.add(new BlockTypeData(Material.IRON_PLATE, 0, "Iron Plate"));							// (ID=148)Iron Plate
		askingBlock.add(new BlockTypeData(Material.REDSTONE_COMPARATOR_OFF, 0, "Comparator"));				// (ID=149)Comparator
		askingBlock.add(new BlockTypeData(Material.DAYLIGHT_DETECTOR, 0, "Daylight Detector"));				// (ID=151)Daylight Detector
		askingBlock.add(new BlockTypeData(Material.REDSTONE_BLOCK, 0, "Redstone Block"));					// (ID=152)Redstone Block
		askingBlock.add(new BlockTypeData(Material.QUARTZ_ORE, 0, "Quartz Ore"));							// (ID=153)Quartz Ore
		askingBlock.add(new BlockTypeData(Material.HOPPER, 0, "Hopper"));									// (ID=154)Hopper
		askingBlock.add(new BlockTypeData(Material.QUARTZ_BLOCK, 0, "Quartz Block"));						// (ID=155)Quartz Block
		askingBlock.add(new BlockTypeData(Material.QUARTZ_BLOCK, 1, "Chiseled Quartz Block"));				// (ID=155)Chiseled Quartz Block
		askingBlock.add(new BlockTypeData(Material.QUARTZ_BLOCK, 2, "Pillar Quartz Block"));				// (ID=155)Pillar Quartz Block
		askingBlock.add(new BlockTypeData(Material.QUARTZ_STAIRS, 0, "Quartz Stairs"));						// (ID=156)Quartz Stairs
		askingBlock.add(new BlockTypeData(Material.ACTIVATOR_RAIL, 0, "Activator Rail"));					// (ID=157)Activator Rail
		askingBlock.add(new BlockTypeData(Material.DROPPER, 0, "Dropper"));									// (ID=158)Dropper
		askingBlock.add(new BlockTypeData(Material.STAINED_CLAY, 0, "White Stained Clay"));					// (ID=159)White Stained Clay
		askingBlock.add(new BlockTypeData(Material.STAINED_CLAY, 1, "White Stained Clay"));					// (ID=159)Orange Stained Clay
		askingBlock.add(new BlockTypeData(Material.STAINED_CLAY, 2, "Magenta Stained Clay"));				// (ID=159)Magenta Stained Clay
		askingBlock.add(new BlockTypeData(Material.STAINED_CLAY, 3, "Light Blue Stained Clay"));			// (ID=159)Light Blue Stained Clay
		askingBlock.add(new BlockTypeData(Material.STAINED_CLAY, 4, "Yellow Stained Clay"));				// (ID=159)Yellow Stained Clay
		askingBlock.add(new BlockTypeData(Material.STAINED_CLAY, 5, "Lime Stained Clay"));					// (ID=159)Lime Stained Clay
		askingBlock.add(new BlockTypeData(Material.STAINED_CLAY, 6, "Pink Stained Clay"));					// (ID=159)Pink Stained Clay
		askingBlock.add(new BlockTypeData(Material.STAINED_CLAY, 7, "Gray Stained Clay"));					// (ID=159)Gray Stained Clay
		askingBlock.add(new BlockTypeData(Material.STAINED_CLAY, 8, "Light Gray Stained Clay"));			// (ID=159)Light Gray Stained Clay
		askingBlock.add(new BlockTypeData(Material.STAINED_CLAY, 9, "Cyan Stained Clay"));					// (ID=159)Cyan Stained Clay
		askingBlock.add(new BlockTypeData(Material.STAINED_CLAY, 10, "Purple Stained Clay"));				// (ID=159)Purple Stained Clay
		askingBlock.add(new BlockTypeData(Material.STAINED_CLAY, 11, "Blue Stained Clay"));					// (ID=159)Blue Stained Clay
		askingBlock.add(new BlockTypeData(Material.STAINED_CLAY, 12, "Brown Stained Clay"));				// (ID=159)Brown Stained Clay
		askingBlock.add(new BlockTypeData(Material.STAINED_CLAY, 13, "Green Stained Clay"));				// (ID=159)Green Stained Clay
		askingBlock.add(new BlockTypeData(Material.STAINED_CLAY, 14, "Red Stained Clay"));					// (ID=159)Red Stained Clay
		askingBlock.add(new BlockTypeData(Material.STAINED_CLAY, 15, "Black Stained Clay"));				// (ID=159)Black Stained Clay
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS_PANE, 0, "White Stained Glass Pane"));		// (ID=160)White Stained Glass Pane
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS_PANE, 1, "Orange Stained Glass Pane"));	// (ID=160)Orange Stained Glass Pane
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS_PANE, 2, "Magenta Stained Glass Pane"));	// (ID=160)Magenta Stained Glass Pane
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS_PANE, 3, "Light Blue Stained Glass Pane"));// (ID=160)Light Blue Stained Glass Pane
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS_PANE, 4, "Yellow Stained Glass Pane"));	// (ID=160)Yellow Stained Glass Pane
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS_PANE, 5, "Lime Stained Glass Pane"));		// (ID=160)Lime Stained Glass Pane
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS_PANE, 6, "Pink Stained Glass Pane"));		// (ID=160)Pink Stained Glass Pane
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS_PANE, 7, "Gray Stained Glass Pane"));		// (ID=160)Gray Stained Glass Pane
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS_PANE, 8, "Light Gray Stained Glass Pane"));// (ID=160)Light Gray Stained Glass Pane
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS_PANE, 9, "Cyan Stained Glass Pane"));		// (ID=160)Cyan Stained Glass Pane
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS_PANE, 10, "Purple Stained Glass Pane"));	// (ID=160)Purple Stained Glass Pane
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS_PANE, 11, "Blue Stained Glass Pane"));		// (ID=160)Blue Stained Glass Pane
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS_PANE, 12, "Brown Stained Glass Pane"));	// (ID=160)Brown Stained Glass Pane
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS_PANE, 13, "Green Stained Glass Pane"));	// (ID=160)Green Stained Glass Pane
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS_PANE, 14, "Red Stained Glass Pane"));		// (ID=160)Red Stained Glass Pane
		askingBlock.add(new BlockTypeData(Material.STAINED_GLASS_PANE, 15, "Black Stained Glass Pane"));	// (ID=160)Black Stained Glass Pane
		//askingBlock.add(new BlockTypeData(Material.LEAVES_2, 0, "Acacia Leaves"));							// (ID=161)Acacia Leaves
		//askingBlock.add(new BlockTypeData(Material.LEAVES_2, 1, "Dark Oak Leaves"));						// (ID=161)Dark Oak Leaves
		askingBlock.add(new BlockTypeData(Material.LOG_2, 0, "Acacia Wood"));								// (ID=162)Acacia Wood
		askingBlock.add(new BlockTypeData(Material.LOG_2, 1, "Dark Oak Wood"));								// (ID=162)Dark Oak Wood
		askingBlock.add(new BlockTypeData(Material.ACACIA_STAIRS, 0, "Acacia Stairs"));						// (ID=163)Acacia Stairs
		askingBlock.add(new BlockTypeData(Material.DARK_OAK_STAIRS, 0, "Dark Oak Stairs"));					// (ID=164)Dark Oak Stairs
		askingBlock.add(new BlockTypeData(Material.SLIME_BLOCK, 0, "Slime Block"));							// (ID=165)Slime Block
		askingBlock.add(new BlockTypeData(Material.IRON_TRAPDOOR, 0, "Iron Trapdoor"));						// (ID=167)Iron Trapdoor
		askingBlock.add(new BlockTypeData(Material.PRISMARINE, 0, "Prismarine"));							// (ID=168)Prismarine
		askingBlock.add(new BlockTypeData(Material.SEA_LANTERN, 0, "Sea Lantern"));							// (ID=169)Sea Lantern
		askingBlock.add(new BlockTypeData(Material.HAY_BLOCK, 0, "Hay Bale"));								// (ID=170)Hay Bale
		askingBlock.add(new BlockTypeData(Material.CARPET, 0, "White Carpet"));								// (ID=171)White Carpet
		askingBlock.add(new BlockTypeData(Material.CARPET, 1, "Orange Carpet"));							// (ID=171)Orange Carpet
		askingBlock.add(new BlockTypeData(Material.CARPET, 2, "Magenta Carpet"));							// (ID=171)Magenta Carpet
		askingBlock.add(new BlockTypeData(Material.CARPET, 3, "Light Blue Carpet"));						// (ID=171)Light Blue Carpet
		askingBlock.add(new BlockTypeData(Material.CARPET, 4, "Yellow Carpet"));							// (ID=171)Yellow Carpet
		askingBlock.add(new BlockTypeData(Material.CARPET, 5, "Lime Carpet"));								// (ID=171)Lime Carpet
		askingBlock.add(new BlockTypeData(Material.CARPET, 6, "Pink Carpet"));								// (ID=171)Pink Carpet
		askingBlock.add(new BlockTypeData(Material.CARPET, 7, "Gray Carpet"));								// (ID=171)Gray Carpet
		askingBlock.add(new BlockTypeData(Material.CARPET, 8, "Light Gray Carpet"));						// (ID=171)Light Gray Carpet
		askingBlock.add(new BlockTypeData(Material.CARPET, 9, "Cyan Carpet"));								// (ID=171)Cyan Carpet
		askingBlock.add(new BlockTypeData(Material.CARPET, 10, "Purple Carpet"));							// (ID=171)Purple Carpet
		askingBlock.add(new BlockTypeData(Material.CARPET, 11, "Blue Carpet"));								// (ID=171)Blue Carpet
		askingBlock.add(new BlockTypeData(Material.CARPET, 12, "Brown Carpet"));							// (ID=171)Brown Carpet
		askingBlock.add(new BlockTypeData(Material.CARPET, 13, "Green Carpet"));							// (ID=171)Green Carpet
		askingBlock.add(new BlockTypeData(Material.CARPET, 14, "Red Carpet"));								// (ID=171)Red Carpet
		askingBlock.add(new BlockTypeData(Material.CARPET, 15, "Black Carpet"));							// (ID=171)Black Carpet
		askingBlock.add(new BlockTypeData(Material.HARD_CLAY, 0, "Hard Clay"));								// (ID=172)Hard Clay
		askingBlock.add(new BlockTypeData(Material.COAL_BLOCK, 0, "Coal Block"));							// (ID=173)Coal Block
		askingBlock.add(new BlockTypeData(Material.PACKED_ICE, 0, "Packed Ice"));							// (ID=174)Packed Ice
		askingBlock.add(new BlockTypeData(Material.RED_SANDSTONE, 0, "Red Sandstone"));						// (ID=179)Red Sandstone
		askingBlock.add(new BlockTypeData(Material.RED_SANDSTONE, 1, "Chiseled Red Sandstone"));			// (ID=179)Chiseled Red Sandstone
		askingBlock.add(new BlockTypeData(Material.RED_SANDSTONE, 2, "Smooth Red Sandstone"));				// (ID=179)Smooth Red Sandstone
		askingBlock.add(new BlockTypeData(Material.RED_SANDSTONE_STAIRS, 0, "Red Sandstone Stairs"));		// (ID=180)Red Sandstone Stairs
		askingBlock.add(new BlockTypeData(Material.STONE_SLAB2, 0, "Red Sandstone Slab"));					// (ID=182)Red Sandstone Slab
		askingBlock.add(new BlockTypeData(Material.SPRUCE_FENCE_GATE, 0, "Spruce Fence Gate"));				// (ID=183)Spruce Fence Gate
		askingBlock.add(new BlockTypeData(Material.BIRCH_FENCE_GATE, 0, "Birch Fence Gate"));				// (ID=184)Birch Fence Gate
		askingBlock.add(new BlockTypeData(Material.JUNGLE_FENCE_GATE, 0, "Jungle Fence Gate"));				// (ID=185)Jungle Fence Gate
		askingBlock.add(new BlockTypeData(Material.DARK_OAK_FENCE_GATE, 0, "Dark Oak Fence Gate"));			// (ID=186)Dark Oak Fence Gate
		askingBlock.add(new BlockTypeData(Material.ACACIA_FENCE_GATE, 0, "Acacia Fence Gate"));				// (ID=187)Acacia Fence Gate
		askingBlock.add(new BlockTypeData(Material.SPRUCE_FENCE, 0, "Spruce Fence"));						// (ID=188)Spruce Fence
		askingBlock.add(new BlockTypeData(Material.BIRCH_FENCE, 0, "Birch Fence"));							// (ID=189)Birch Fence
		askingBlock.add(new BlockTypeData(Material.JUNGLE_FENCE, 0, "Jungle Fence"));						// (ID=190)Jungle Fence
		askingBlock.add(new BlockTypeData(Material.DARK_OAK_FENCE, 0, "Dark Oak Fence"));					// (ID=191)Dark Oak Fence
		askingBlock.add(new BlockTypeData(Material.ACACIA_FENCE, 0, "Acacia Fence"));						// (ID=192)Acacia Fence
		askingBlock.add(new BlockTypeData(Material.END_ROD, 0, "End Rod"));									// (ID=198)End Rod
		askingBlock.add(new BlockTypeData(Material.PURPUR_BLOCK, 0, "Purpur Block"));						// (ID=201)Purpur Block
		askingBlock.add(new BlockTypeData(Material.PURPUR_PILLAR, 0, "Purpur Pillar"));						// (ID=202)Purpur Pillar
		askingBlock.add(new BlockTypeData(Material.PURPUR_STAIRS, 0, "Purpur Stairs"));						// (ID=203)Purpur Stairs
		askingBlock.add(new BlockTypeData(Material.PURPUR_SLAB, 0, "Purpur Slab"));							// (ID=205)Purpur Slab
		askingBlock.add(new BlockTypeData(Material.END_BRICKS, 0, "End Brick"));							// (ID=206)End Brick
		askingBlock.add(new BlockTypeData(Material.MAGMA, 0, "Magma Block"));								// (ID=202)Magma Block
		askingBlock.add(new BlockTypeData(Material.NETHER_WART_BLOCK, 0, "Nether Wart Block"));				// (ID=203)Nether Wart Block
		askingBlock.add(new BlockTypeData(Material.RED_NETHER_BRICK, 0, "Red Nether Brick"));				// (ID=205)Red Nether Brick
		askingBlock.add(new BlockTypeData(Material.BONE_BLOCK, 0, "Bone Block"));							// (ID=206)Bone Block
		//@formatter:on
	}

	public BlockTypeData findBlock(Material material, byte data) {
		for (BlockTypeData block : askingBlock) {
			if (block.getMaterial().equals(material) && ((byte) (int) block.getData()) == data) {
				return block;
			}
		}
		return null;
	}

	public List<BlockTypeData> getAskingBlock() {
		return askingBlock;
	}

	public void setAskingBlock(List<BlockTypeData> askingBlock) {
		this.askingBlock = askingBlock;
	}
}
