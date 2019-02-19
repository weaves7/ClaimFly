package org.makershaven.claimfly;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;


class PlayerTracker {
    private Map<UUID, Aviator> flyingPlayers = new HashMap<>();
    private Set<Player> trackedPlayersSet;
    private Plugin plugin;


    PlayerTracker(ClaimFly plugin) {
        this.plugin = plugin;

    }

    Aviator getAviator(Player player) {
        return flyingPlayers.get(player.getUniqueId());
    }


    boolean flyingPlayersContains(Player player) {

        return flyingPlayers.containsKey(player.getUniqueId());
    }

    int getFlyingPlayersSize() {

        return flyingPlayers.size();
    }

    Set<Player> getTrackedPlayersSet(){
        trackedPlayersSet.clear();
        for(UUID uuid : flyingPlayers.keySet()){
            plugin.getServer().getPlayer(uuid);
        }
       return  trackedPlayersSet;
    }

    void addFlyingPlayer(Player player) {
        flyingPlayers.put(player.getUniqueId(), new Aviator(plugin));
    }

    void removeFlyingPlayer(Player player) {
        flyingPlayers.remove(player.getUniqueId());
    }


}
