package com.yaadanial.lachasseauxblocks.plugin;

import java.util.HashMap;
import java.util.Map;

public class BlocksFoundByPlayer {

	private Map<String, Integer> blocksFoundByPlayer;
	private Integer nbblocksFoundMax;

	public BlocksFoundByPlayer(Integer nbblocksFoundMax) {
		this.nbblocksFoundMax = nbblocksFoundMax;
		this.blocksFoundByPlayer = new HashMap<String, Integer>();
	}

	public Map<String, Integer> getBlocksFoundByPlayer() {
		return blocksFoundByPlayer;
	}

	public void addBlocksFoundByPlayer(String Player, Integer blocksFound) {
		this.blocksFoundByPlayer.put(Player, blocksFound);
	}

	public Integer getNbblocksFoundMax() {
		return nbblocksFoundMax;
	}

	public void setNbblocksFoundMax(Integer nbblocksFoundMax) {
		this.nbblocksFoundMax = nbblocksFoundMax;
	}
}
