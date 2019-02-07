package org.makershaven.claimfly;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {  //This class is updated, but untested.

    private FlyingPlayers flyingPlayers;

    PlayerJoinListener(ClaimFly plugin) {

        this.flyingPlayers = plugin.flyingPlayers;
    }

    // THis needs more testing!! And a constructor with the plugin pass?
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!flyingPlayers.setContains(event.getPlayer())) {
            flyingPlayers.putFlyingPlayer(event.getPlayer());

        }
    }

}
