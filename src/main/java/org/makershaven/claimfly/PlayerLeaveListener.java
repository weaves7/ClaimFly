package org.makershaven.claimfly;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.logging.Logger;

public class PlayerLeaveListener implements Listener {

    private ClaimFly plugin;
    private PlayerTracker playerTracker;
    private Logger logger;

    PlayerLeaveListener(ClaimFly plugin) {
        this.plugin = plugin;
        this.playerTracker = plugin.playerTracker;
        logger = plugin.getLogger();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (playerTracker.flyingPlayersContains(player)) {

            if (plugin.getConfig().getBoolean("debug")) {
                logger.info("Player disconnected leaving " + playerTracker.getFlyingPlayersSize() + " players in the set.");
            }
            playerTracker.removeFlyingPlayer(player);//TODO remove this for persistence?

        }
    }

}
