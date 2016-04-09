package com.yaadanial.LaChasseAuxBlocks.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Plug extends JavaPlugin {

	@Override
    public void onEnable() {
        getLogger().info("onEnable has been invoked!");
    }
    
    @Override
    public void onDisable() {
    	getLogger().info("onDisable has been invoked!");
    }

}
