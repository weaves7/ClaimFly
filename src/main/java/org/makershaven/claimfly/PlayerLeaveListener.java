package org.makershaven.claimfly;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {

    private PlayerTracker playerTracker;

    PlayerLeaveListener(ClaimFly plugin) {

        this.playerTracker = plugin.playerTracker;

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (playerTracker.flyingPlayersContains(player)) {

            playerTracker.removeFlyingPlayer(player);//TODO remove this for persistence? Use a runnable to periodically prune the tracker instead?

        }
    }

}
