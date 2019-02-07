package org.makershaven.claimfly;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private FlyingPlayers flyingPlayers;

    PlayerJoinListener(ClaimFly plugin) {

        this.flyingPlayers = plugin.flyingPlayers;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!flyingPlayers.setContains(event.getPlayer())) {
            flyingPlayers.putFlyingPlayer(event.getPlayer());

        }
    }

}
