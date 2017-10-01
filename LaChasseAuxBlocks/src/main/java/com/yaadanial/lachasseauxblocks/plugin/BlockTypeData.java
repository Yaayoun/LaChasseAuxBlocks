package com.yaadanial.lachasseauxblocks.plugin;

import org.bukkit.Material;

public class BlockTypeData {

	private Material material;
	private Integer data;

	public BlockTypeData(Material material, Integer data) {
		super();
		this.material = material;
		this.data = data;
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
