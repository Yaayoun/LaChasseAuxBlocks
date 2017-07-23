package com.yaadanial.lachasseauxblocks.plugin;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Classe de gestion du Chronometre
 * 
 * @author Yaadanial
 *
 */
public class Chronometre {

	private LCABPlugin plugin = null;
	private Integer minutes;
	private Integer seconds;
	private Integer hours;
	private boolean stop;

	public Chronometre(LCABPlugin plugin) {
		this.plugin = plugin;
		this.minutes = 0;
		this.seconds = 0;
		this.hours = 0;
		this.stop = false;
	}

	/**
	 * Commencer le Chronometre
	 */
	public void run() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new BukkitRunnable() {
			@Override
			public void run() {
				plugin.getScoreBoardManager().regenerateSidebar();
				if (!stop) {
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
			}
		}, 20L, 20L);
	}

	/**
	 * Stopper le Chronometre
	 */
	public void stop() {
		stop = true;
	}

	/**
	 * Redemarrer le Chronometre
	 */
	public void start() {
		stop = false;
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
