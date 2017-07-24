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
		obj.getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + formatter.format(plugin.getChronometre().getHours()) + ChatColor.GRAY + ":" + ChatColor.WHITE
				+ formatter.format(plugin.getChronometre().getMinutes()) + ChatColor.GRAY + ":" + ChatColor.WHITE + formatter.format(plugin.getChronometre().getSeconds()))).setScore(1);
		obj.getScore(Bukkit.getOfflinePlayer("")).setScore(2);
		int index = 3;
		for (Player player : plugin.getServer().getOnlinePlayers()) {
			obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GRAY + player.getName() + " : " + ChatColor.WHITE + plugin.getBlocksFoundByPlayer().getBlocksFoundByPlayer().get(player.getName()).toString()
					+ ChatColor.GRAY + "/" + ChatColor.WHITE + plugin.getBlocksFoundByPlayer().getNbblocksFoundMax())).setScore(index++);
		}
		obj.getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" + "0" + ChatColor.GRAY + " teams")).setScore(index++);
		obj.getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" + Bukkit.getServer().getOnlinePlayers().size() + ChatColor.GRAY + " joueurs")).setScore(index++);
	}

	/**
	 * Création du scoreboard dans la playerList
	 */
	private void createPlayerList() {
		Objective obj = scoreBoard.registerNewObjective("BlocksFind", "dummy");
		obj.setDisplayName("BlocksFind");
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

	public void updatePlayerListName(Player player) {
		player.setScoreboard(scoreBoard);
		Integer blocksFind = (int) plugin.getBlocksFoundByPlayer().getBlocksFoundByPlayer().get(player.getName());
		scoreBoard.getObjective("BlocksFind").getScore(player).setScore(blocksFind);
	}

	public void addToScoreboard(Player player) {
		player.setScoreboard(scoreBoard);
		scoreBoard.getObjective("BlocksFind").getScore(player).setScore(0);
		this.updatePlayerListName(player);
	}

	public void setNbBlocksFind(Player entity, int i) {
		entity.setScoreboard(scoreBoard);
		scoreBoard.getObjective("BlocksFind").getScore(entity).setScore(i);
	}

}
