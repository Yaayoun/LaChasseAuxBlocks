package com.yaadanial.lachasseauxblocks.plugin;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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

	public void initializeGame() {
		Location location = new Location(plugin.getServer().getWorlds().get(0), 3, 63, 6 * numberOfTeam);
		for (Player p : players) {
			p.teleport(location);
			p.setGameMode(GameMode.SURVIVAL);
			p.setHealth(20);
			p.setFoodLevel(20);
			p.setExhaustion(5F);
			p.getInventory().clear();
			p.getInventory().setArmorContents(new ItemStack[] { new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR) });
			p.setExp(0L + 0F);
			p.setLevel(0);
			p.closeInventory();
			p.getActivePotionEffects().clear();
			p.setBedSpawnLocation(location, true);
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
