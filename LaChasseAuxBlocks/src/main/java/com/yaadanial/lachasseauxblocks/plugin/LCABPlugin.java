package com.yaadanial.lachasseauxblocks.plugin;

import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

public class LCABPlugin extends JavaPlugin {

	private Logger logger = null;
	private Scoreboard sb = null;
	private Random random = null;

	@Override
	public void onEnable() {
		
		logger.info("LCABPlugin loaded!");
		getServer().getWorlds().get(0).setTime(6000L);
		getServer().getWorlds().get(0).setStorm(false);
		getServer().getWorlds().get(0).setDifficulty(Difficulty.HARD);
		
		random = new Random();
		
		getServer().getPluginManager().registerEvents(new LCABPluginListener(this), this);
	}

	@Override
	public void onDisable() {
		logger.info("LCABPlugin unloaded!");
	}
	
	public boolean onCommand(final CommandSender s, Command c, String l, String[] a) {
		if (c.getName().equalsIgnoreCase("lcab")) {
			if (!(s instanceof Player)) {
				s.sendMessage(ChatColor.RED+"Vous devez être un joueur");
				return true;
			}
			Player pl = (Player)s;
			if (!pl.isOp()) {
				pl.sendMessage(ChatColor.RED+"Lolnope.");
				return true;
			}
			if (a.length == 0) {
				pl.sendMessage("Usage : /lcab <start>");
				return true;
			}
			if (a[0].equalsIgnoreCase("start")) {
				World w = pl.getWorld();
				w.getBlockAt(pl.getLocation().getBlockX() + 5, pl.getLocation().getBlockY() + 2, pl.getLocation().getBlockZ()).setType(Material.DIAMOND_BLOCK);
				Bukkit.getServer().broadcastMessage(ChatColor.GREEN+"--- L'Autel à Spawn ---");
			}
		}
		return false;
	}

}
