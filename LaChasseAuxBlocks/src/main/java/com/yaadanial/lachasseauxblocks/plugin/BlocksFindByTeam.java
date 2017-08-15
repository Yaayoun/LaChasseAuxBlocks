package com.yaadanial.lachasseauxblocks.plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe de gestion pour le nombre de blocks trouvés par team
 * 
 * @author Yaadanial
 *
 */
public class BlocksFindByTeam {

	private Map<String, Integer> blocksFindByTeam;
	private Integer nbblocksFindMax;

	public BlocksFindByTeam(Integer nbblocksFindMax) {
		this.nbblocksFindMax = nbblocksFindMax;
		this.blocksFindByTeam = new HashMap<String, Integer>();
	}

	public Map<String, Integer> getBlocksFindByTeam() {
		return blocksFindByTeam;
	}

	public void addBlocksFindByTeam(String team, Integer blocksFind) {
		this.blocksFindByTeam.put(team, blocksFind);
	}

	public Integer getNbblocksFindMax() {
		return nbblocksFindMax;
	}

	public void setNbblocksFindMax(Integer nbblocksFindMax) {
		this.nbblocksFindMax = nbblocksFindMax;
	}

	public void addPointToTeam(String team) {
		if (blocksFindByTeam.get(team) != null) {
			blocksFindByTeam.put(team, blocksFindByTeam.get(team) + 1);
		}
	}
}
