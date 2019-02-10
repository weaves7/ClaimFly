package org.makershaven.claimfly;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private PlayerTracker playerTracker;

    PlayerJoinListener(ClaimFly plugin) {

        this.playerTracker = plugin.playerTracker;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!playerTracker.flyingPlayersContains(player)) {
            playerTracker.addFlyingPlayer(player);

        }
    }

}
