package org.makershaven.claimfly;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Logger;

public class CheckFlyingPlayersTask extends BukkitRunnable {


    private Logger logger;
    private FileConfiguration config;
    private PlayerTracker playerTracker;
    private FlightCheck flightCheck;

    CheckFlyingPlayersTask(ClaimFly plugin) {

        this.playerTracker = plugin.playerTracker;
        this.config = plugin.getConfig();
        logger = plugin.getLogger();
        this.flightCheck = new FlightCheck(plugin);
    }

    @Override
    public void run() {

        if (!config.getBoolean("debug")) {
            checkFlyingPlayers();
        } else {
            long sTime = System.nanoTime();
            checkFlyingPlayers();
            logger.info("Checking all of the players in the flyingPlayers Set took " + (System.nanoTime() - sTime)
                    + "ns for a set of " + playerTracker.getFlyingPlayersSize());
        }

    }
    private void checkFlyingPlayers() {
        String checkResult;

        for (Player player : playerTracker.getTrackedPlayersSet()) {
            if (player.isFlying()) {
                checkResult = flightCheck.check(player);
                if (!checkResult.equals(flightCheck.getFLIGHT_ALLOWED())) {
                    player.setFlying(false);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', checkResult));
                }
            }
        }
    }

}