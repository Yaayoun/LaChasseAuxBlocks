package com.yaadanial.lachasseauxblocks.plugin;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class LCABPlugin extends JavaPlugin {

	private Logger logger = null;
	private Boolean gameRunning = false;
	private Altar altar = null;
	private ScoreBoardManager scoreBoardManager = null;
	private Chronometre chronometre = null;
	private BlocksFoundByPlayer blocksFoundByPlayer = null;

	@Override
	public void onEnable() {

		logger = Bukkit.getLogger();
		logger.info("LCABPlugin loaded!");
		altar = new Altar();
		getServer().getWorlds().get(0).setTime(6000L);
		getServer().getWorlds().get(0).setStorm(false);
		getServer().getWorlds().get(0).setDifficulty(Difficulty.HARD);

		getServer().getPluginManager().registerEvents(new LCABPluginListener(this), this);

		chronometre = new Chronometre(this);

		scoreBoardManager = new ScoreBoardManager(this);
		scoreBoardManager.setMatchInfo();

		blocksFoundByPlayer = new BlocksFoundByPlayer(0);
	}

	@Override
	public void onDisable() {
		logger.info("LCABPlugin unloaded!");
	}

	public boolean onCommand(final CommandSender s, Command c, String l, String[] a) {
		String command = c.getName().toLowerCase();
		switch (command) {
		case "lcab":
			if (!(s instanceof Player)) {
				s.sendMessage(ChatColor.RED + "Vous devez être un joueur");
				return true;
			}
			Player pl = (Player) s;
			if (!pl.isOp()) {
				pl.sendMessage(ChatColor.RED + "Lolnope.");
				return true;
			}
			if (a.length == 0) {
				pl.sendMessage("Usage : /lcab <start|stop|tp>");
				return true;
			}
			if (a[0].equalsIgnoreCase("start")) {
				if (!this.gameRunning) {
					altar.generate(pl);

					this.logToChat(ChatColor.GREEN + "--- L'Autel a Spawn ---");
					this.gameRunning = true;
					this.chronometre.run();
					blocksFoundByPlayer = new BlocksFoundByPlayer(2);
					for (Player player : getServer().getOnlinePlayers()) {
						blocksFoundByPlayer.addBlocksFoundByPlayer(player.getName(), 0);
					}
				} else {
					this.logToChat(ChatColor.RED + "La Chasse est déjà lancée !");
				}
				return true;
			} else if (a[0].equalsIgnoreCase("stop")) {
				if (this.gameRunning == false) {
					this.logToChat(ChatColor.RED + "Aucune chasse en cours !");
					return true;
				}
				// On arrête la chasse.
				this.logToChat(ChatColor.YELLOW + "--- La chasse a été annulée par " + s.getName() + " ---");
				this.gameRunning = false;
				altar = new Altar();
				chronometre = new Chronometre(this);
				return true;
			} else if (a[0].equalsIgnoreCase("tp")) {
				// Téléporte le joueur sur l'autel
				if (a.length <= 1) {
					pl.sendMessage("Usage : /lcab tp <player>");
					return true;
				}
				if (this.gameRunning == false) {
					pl.sendMessage("La chasse doit être lancée pour se tp sur l'autel");
					return true;
				}
				pl.sendMessage("Cette fonctionnalité sera implémentée dans une version ultérieure");
				return true;
			} else if (a[0].equalsIgnoreCase("debug")) {
				AskingBlock ab = new AskingBlock();
				int i = 0;
				for (BlockTypeData blockTypeData : ab.getAskingBlock()) {
					Block block = pl.getWorld().getBlockAt(pl.getLocation().getBlockX() + ++i, pl.getLocation().getBlockY() - 1, pl.getLocation().getBlockZ());
					block.setType(Material.STONE);
					block.setData((byte) 6);
					Block block1 = pl.getWorld().getBlockAt(pl.getLocation().getBlockX() + i, pl.getLocation().getBlockY(), pl.getLocation().getBlockZ());
					block1.setType(blockTypeData.getMaterial());
					block1.setData((byte) (int) blockTypeData.getData());
					logger.info(i + "/" + ab.getAskingBlock().size() + " : Le Block " + block1.getType() + " a spawn");
				}
				return true;
			}
		}
		return false;
	}

	public boolean isGameRunning() {
		return this.gameRunning;
	}

	public Altar getAltar() {
		return this.altar;
	}

	public void logToChat(String log) {
		Bukkit.getServer().broadcastMessage(log);
	}

	public ScoreBoardManager getScoreBoardManager() {
		return scoreBoardManager;
	}

	public void setScoreBoardManager(ScoreBoardManager scoreBoardManager) {
		this.scoreBoardManager = scoreBoardManager;
	}

	public Chronometre getChronometre() {
		return chronometre;
	}

	public void setChronometre(Chronometre chronometre) {
		this.chronometre = chronometre;
	}

	public BlocksFoundByPlayer getBlocksFoundByPlayer() {
		return blocksFoundByPlayer;
	}

	public void setBlocksFoundByPlay(BlocksFoundByPlayer blocksFoundByPlayer) {
		this.blocksFoundByPlayer = blocksFoundByPlayer;
	}
}
