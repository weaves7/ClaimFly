package org.makershaven.claimfly;

import org.bukkit.entity.Player;

import java.util.*;


class PlayerTracker {
    private Map<UUID, Aviator> flyingPlayers = new HashMap<>();
    private Set<Player> trackedPlayersSet = new HashSet<>();
    private ClaimFly plugin;


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

    Map<UUID,Aviator> getFlyingPlayers(){
        return  flyingPlayers;
    }

    Set<Player> getTrackedPlayersSet(){
        trackedPlayersSet.clear();
        for(UUID uuid : flyingPlayers.keySet()){
            Player player = plugin.getServer().getPlayer(uuid);
            if(player != null && player.isOnline()) {
                trackedPlayersSet.add(player);
            }
        }
       return  trackedPlayersSet;
    }

    void addFlyingPlayer(Player player) {
        Aviator aviator = plugin.dataStore.loadAviator(player.getUniqueId());

        flyingPlayers.put(player.getUniqueId(),aviator !=null ? aviator : new Aviator(plugin));
    }

    void removeFlyingPlayer(Player player) {
        flyingPlayers.remove(player.getUniqueId());

    }


}
