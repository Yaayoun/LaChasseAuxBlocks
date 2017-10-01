package com.yaadanial.lachasseauxblocks.plugin;

import org.bukkit.Material;

/**
 * Classe de gestion des blocks
 * 
 * @author Yaadanial
 *
 */
public class BlockTypeData {

	private Material material;
	private Integer data;
	private String name;

	public BlockTypeData(Material material, Integer data, String name) {
		super();
		this.material = material;
		this.data = data;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}

}
