package com.yaadanial.lachasseauxblocks.plugin;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Classe de gestion du scoreboard
 * 
 * @author Yaadanial
 *
 */
public class ScoreBoardManager {

	private LCABPlugin plugin = null;
	private Scoreboard scoreBoard = null;
	private String name = "LCAB";
	private NumberFormat formatter = new DecimalFormat("00");
	private Chronometre chronometre = null;

	public ScoreBoardManager(LCABPlugin plugin) {
		this.plugin = plugin;
		this.scoreBoard = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
		this.chronometre = new Chronometre(plugin);
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
		Objective objective = null;
		try {
			objective = scoreBoard.getObjective(name);
			objective.setDisplaySlot(null);
			objective.unregister();
		} catch (Exception e) {

		}
		Random random = new Random();
		name = "LCAB" + random.nextInt(10000000);
		objective = scoreBoard.registerNewObjective(name, "dummy");
		objective = scoreBoard.getObjective(name);

		objective.setDisplayName(this.getScoreboardName());
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + formatter.format(chronometre.getHours()) + ChatColor.GRAY + ":" + ChatColor.WHITE + formatter.format(chronometre.getMinutes())
				+ ChatColor.GRAY + ":" + ChatColor.WHITE + formatter.format(chronometre.getSeconds()))).setScore(1);
		objective.getScore(Bukkit.getOfflinePlayer("")).setScore(2);
		int index = 3;
		Set<String> keys = plugin.getBlocksFindByTeam().getBlocksFindByTeam().keySet();
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			LCABTeam team = plugin.findTeam(key);
			if (team != null) {
				objective.getScore(Bukkit.getOfflinePlayer(team.getChatColor() + team.getDisplayName() + ChatColor.GRAY + " : " + ChatColor.WHITE
						+ plugin.getBlocksFindByTeam().getBlocksFindByTeam().get(team.getDisplayName()).toString() + ChatColor.GRAY + "/" + ChatColor.WHITE
						+ plugin.getBlocksFindByTeam().getNbblocksFindMax())).setScore(index++);
			}
		}
		objective.getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" + plugin.getTeams().size() + ChatColor.GRAY + " teams")).setScore(index++);
		objective.getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" + Bukkit.getServer().getOnlinePlayers().size() + ChatColor.GRAY + " joueurs")).setScore(index++);
	}

	/**
	 * Création du scoreboard dans la playerList
	 */
	private void createPlayerList() {
		Objective objective = scoreBoard.registerNewObjective("BlocksFind", "dummy");
		objective.setDisplayName("BlocksFind");
		objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);
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

	public Chronometre getChronometre() {
		return chronometre;
	}

	public void setChronometre(Chronometre chronometre) {
		this.chronometre = chronometre;
	}

	public String getScoreboardName() {
		String s = plugin.getConfig().getString("scoreboard", "La Chasse Aux Blocks");
		return s.substring(0, Math.min(s.length(), 20));
	}

	public void updatePlayerListName(Player player) {
		for (LCABTeam team : plugin.getTeams()) {
			for (Player playerInTeam : team.getPlayers()) {
				if (playerInTeam.equals(player)) {
					Integer blocksFind = (int) plugin.getBlocksFindByTeam().getBlocksFindByTeam().get(team.getDisplayName());
					scoreBoard.getObjective("BlocksFind").getScore(player).setScore(blocksFind);
					player.setScoreboard(scoreBoard);
					return;
				}
			}
		}
		scoreBoard.getObjective("BlocksFind").getScore(player).setScore(0);
		player.setScoreboard(scoreBoard);
	}

	public void addToScoreboard(Player player) {
		player.setScoreboard(scoreBoard);
		scoreBoard.getObjective("BlocksFind").getScore(player).setScore(0);
		this.updatePlayerListName(player);
	}

	public void setNbBlocksFind(LCABTeam team, int i) {
		for (Player player : team.getPlayers()) {
			scoreBoard.getObjective("BlocksFind").getScore(player).setScore(i);
			player.setScoreboard(scoreBoard);
		}
	}

	/**
	 * Création du scoreboard
	 */
	public void restartChronometre() {
		chronometre = new Chronometre(plugin);
	}

}
