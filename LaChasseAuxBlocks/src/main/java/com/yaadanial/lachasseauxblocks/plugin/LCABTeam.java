package com.yaadanial.lachasseauxblocks.plugin;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Classe de gestion des Équipes
 * 
 * @author Yaadanial
 *
 */
public class LCABTeam {

	private String name;
	private String displayName;
	private ChatColor color;
	private LCABPlugin plugin;
	private int numberOfTeam;
	private ArrayList<Player> players = new ArrayList<Player>();
	private Altar altar = null;

	public LCABTeam(String name, String displayName, ChatColor color, int numberOfTeam, LCABPlugin plugin) {
		this.name = name;
		this.displayName = displayName;
		this.color = color;
		this.numberOfTeam = numberOfTeam;
		this.plugin = plugin;
		this.altar = new Altar(this, plugin);

		Scoreboard scoreboard = this.plugin.getScoreBoardManager().getScoreBoard();
		scoreboard.registerNewTeam(this.name);

		Team team = scoreboard.getTeam(this.name);
		team.setDisplayName(this.displayName);
		team.setCanSeeFriendlyInvisibles(true);
		team.setPrefix(this.color + "");
	}

	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void addPlayer(Player playerExact) {
		players.add(playerExact);
		plugin.getScoreBoardManager().getScoreBoard().getTeam(this.name).addPlayer(playerExact);
	}

	public void teleportTo(Location lo) {
		for (Player p : players) {
			p.teleport(lo);
		}
	}

	public ChatColor getChatColor() {
		return color;
	}

	public Altar getAltar() {
		return altar;
	}

	public void setAltar(Altar altar) {
		this.altar = altar;
	}

	public int getNumberOfTeam() {
		return numberOfTeam;
	}

	public void setNumberOfTeam(int numberOfTeam) {
		this.numberOfTeam = numberOfTeam;
	}
}
