package org.makershaven.claimfly;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


class PlayerTracker {
    private Map<Player, Aviator> flyingPlayers = new HashMap<>(); //TODO Going to need to change to UUID, Aviator to make persistent with login/logout?
    private Plugin plugin;


    PlayerTracker(ClaimFly plugin) {
        this.plugin = plugin;

    }

    Aviator getAviator(Player player) {
        return flyingPlayers.get(player);
    }

    boolean flyingPlayersContains(Player player) {

        return flyingPlayers.containsKey(player);
    }

    int getFlyingPlayersSize() {

        return flyingPlayers.size();
    }

    Set<Player> getFlyingPlayersSet(){
        return flyingPlayers.keySet();
    }

    void addFlyingPlayer(Player player) {
        flyingPlayers.put(player, new Aviator(plugin));
    }

    void removeFlyingPlayer(Player player) {
        flyingPlayers.remove(player);
    }


}
