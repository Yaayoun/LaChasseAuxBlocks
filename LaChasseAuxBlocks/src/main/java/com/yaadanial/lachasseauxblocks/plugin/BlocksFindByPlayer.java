package com.yaadanial.lachasseauxblocks.plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe de gestion de joueur et de leurs blocks trouvés
 * 
 * @author Yaadanial
 *
 */
public class BlocksFindByPlayer {

	private Map<String, Integer> blocksFindByPlayer;
	private Integer nbblocksFindMax;

	public BlocksFindByPlayer(Integer nbblocksFindMax) {
		this.nbblocksFindMax = nbblocksFindMax;
		this.blocksFindByPlayer = new HashMap<String, Integer>();
	}

	public Map<String, Integer> getBlocksFindByPlayer() {
		return blocksFindByPlayer;
	}

	public void addBlocksFindByPlayer(String Player, Integer blocksFind) {
		this.blocksFindByPlayer.put(Player, blocksFind);
	}

	public Integer getNbblocksFindMax() {
		return nbblocksFindMax;
	}

	public void setNbblocksFindMax(Integer nbblocksFindMax) {
		this.nbblocksFindMax = nbblocksFindMax;
	}

	public void addPointToPlayer(String Player) {
		if (blocksFindByPlayer.get(Player) != null) {
			blocksFindByPlayer.put(Player, blocksFindByPlayer.get(Player) + 1);
		}
	}
}
