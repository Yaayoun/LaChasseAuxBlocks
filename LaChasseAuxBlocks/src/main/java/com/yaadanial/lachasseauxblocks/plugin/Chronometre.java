package com.yaadanial.lachasseauxblocks.plugin;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Chronometre {

	private LCABPlugin plugin = null;
	private Integer minutes = 0;
	private Integer seconds = 0;
	private Integer hours = 0;

	public Chronometre(LCABPlugin plugin) {
		this.plugin = plugin;
	}

	/**
	 * Commencer le Chronometre
	 */
	public void run() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new BukkitRunnable() {
			@Override
			public void run() {
				plugin.getScoreBoardManager().regenerateSidebar();
				seconds++;
				if (seconds == 60) {
					minutes++;
					seconds = 0;
				}
				if (minutes == 60) {
					hours++;
					minutes = 0;
				}
			}
		}, 20L, 20L);
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public Integer getSeconds() {
		return seconds;
	}

	public void setSeconds(Integer seconds) {
		this.seconds = seconds;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}
}
