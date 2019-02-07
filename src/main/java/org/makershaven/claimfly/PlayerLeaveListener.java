package org.makershaven.claimfly;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.logging.Logger;

public class PlayerLeaveListener implements Listener {  //This class is updated, but untested.

    private ClaimFly plugin;
    private FlyingPlayers flyingPlayers;
    private Logger logger;

    PlayerLeaveListener(ClaimFly plugin) {
        this.plugin = plugin;
        this.flyingPlayers = plugin.flyingPlayers;
        logger = plugin.getLogger();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (flyingPlayers.setContains(player)) {

            player.setFlying(false);
            if (plugin.getConfig().getBoolean("debug")) {
                logger.info("Player disconnected leaving " + flyingPlayers.getSetSize() + " players in the set.");
            }
            flyingPlayers.removeFlyingPlayer(player);

        }
    }

}
