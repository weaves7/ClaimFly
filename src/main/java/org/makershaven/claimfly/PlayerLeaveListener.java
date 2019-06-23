package org.makershaven.claimfly;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {

    private PlayerTracker playerTracker;
    ClaimFly plugin;

    PlayerLeaveListener(ClaimFly plugin) {

        this.plugin = plugin;
        this.playerTracker = plugin.playerTracker;

    }


    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Aviator aviator = playerTracker.getAviator(player);

        if(player.getAllowFlight() && player.isFlying()) {
            aviator.setFlightActive(true);
        }
        else {
            aviator.setFlightActive(false);
        }

        plugin.dataStore.saveAviator(player.getUniqueId(),aviator);

        if (playerTracker.flyingPlayersContains(player)) {

            playerTracker.removeFlyingPlayer(player);

        }
    }

}
