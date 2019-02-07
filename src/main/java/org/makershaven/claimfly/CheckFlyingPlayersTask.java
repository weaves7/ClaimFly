package org.makershaven.claimfly;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Logger;

public class CheckFlyingPlayersTask extends BukkitRunnable {  //This class is updated, but untested.


    private Logger logger;
    private FileConfiguration config;
    private FlyingPlayers flyingPlayers;

    CheckFlyingPlayersTask(ClaimFly plugin) {

        this.flyingPlayers = plugin.flyingPlayers;
        this.config = plugin.getConfig();
        logger = plugin.getLogger();
    }

    @Override
    public void run() {

        if (!config.getBoolean("debug")) {
            flyingPlayers.checkFlyingPlayers();
        } else {
            long sTime = System.nanoTime();
            flyingPlayers.checkFlyingPlayers();
            logger.info("Checking all of the players in the FlyingPlayers Set took " + (System.nanoTime() - sTime)
                    + "ns for a set of " + flyingPlayers.getSetSize());
        }

    }

}