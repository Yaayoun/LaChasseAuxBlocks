package com.yaadanial.lachasseauxblocks.plugin;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreBoardManager {

	private LCABPlugin plugin = null;
	private Scoreboard scoreBoard = null;
	private String name = "LCAB";
	private NumberFormat formatter = new DecimalFormat("00");

	public ScoreBoardManager(LCABPlugin p) {
		this.plugin = p;
		this.scoreBoard = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
	}

	/**
	 * Création du scoreboard
	 */
	public void setMatchInfo() {
		createPlayerList();
		createOrRegenerateSidebar();
	}

	/**
	 * Regenerer la sidebar du scoreboard
	 */
	public void regenerateSidebar() {
		createOrRegenerateSidebar();
	}

	/**
	 * Création du scoreboard dans la sidebar
	 */
	private void createOrRegenerateSidebar() {
		Objective obj = null;
		try {
			obj = scoreBoard.getObjective(name);
			obj.setDisplaySlot(null);
			obj.unregister();
		} catch (Exception e) {

		}
		Random r = new Random();
		name = "LCAB" + r.nextInt(10000000);
		obj = scoreBoard.registerNewObjective(name, "dummy");
		obj = scoreBoard.getObjective(name);

		obj.setDisplayName(this.getScoreboardName());
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "0" + ChatColor.GRAY + " Blocks sur " + ChatColor.WHITE + "0")).setScore(5);
		obj.getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" + Bukkit.getServer().getOnlinePlayers().size() + ChatColor.GRAY + " joueurs")).setScore(4);
		obj.getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" + "0" + ChatColor.GRAY + " teams")).setScore(3);
		obj.getScore(Bukkit.getOfflinePlayer("")).setScore(2);
		obj.getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + formatter.format(plugin.getChronometre().getHours()) + ChatColor.GRAY + ":" + ChatColor.WHITE
				+ formatter.format(plugin.getChronometre().getMinutes()) + ChatColor.GRAY + ":" + ChatColor.WHITE + formatter.format(plugin.getChronometre().getSeconds()))).setScore(1);
	}

	/**
	 * Création du scoreboard dans la playerList
	 */
	private void createPlayerList() {
		Objective obj = scoreBoard.registerNewObjective("Vie", "dummy");
		obj.setDisplayName("Vie");
		obj.setDisplaySlot(DisplaySlot.PLAYER_LIST);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Scoreboard getScoreBoard() {
		return scoreBoard;
	}

	public void setScoreBoard(Scoreboard scoreBoard) {
		this.scoreBoard = scoreBoard;
	}

	public String getScoreboardName() {
		String s = plugin.getConfig().getString("scoreboard", "La Chasse Aux Blocks");
		return s.substring(0, Math.min(s.length(), 20));
	}

	public void updatePlayerListName(Player p) {
		p.setScoreboard(scoreBoard);
		Integer he = (int) Math.round(p.getHealth());
		scoreBoard.getObjective("Vie").getScore(p).setScore(he);
	}

	public void addToScoreboard(Player player) {
		player.setScoreboard(scoreBoard);
		scoreBoard.getObjective("Vie").getScore(player).setScore(0);
		this.updatePlayerListName(player);
	}

	public void setLife(Player entity, int i) {
		entity.setScoreboard(scoreBoard);
		scoreBoard.getObjective("Vie").getScore(entity).setScore(i);
	}

}
