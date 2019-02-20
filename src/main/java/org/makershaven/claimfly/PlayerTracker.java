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
            Player player = plugin.getServer().getPlayer(uuid);
            if(player.isOnline()) {
                trackedPlayersSet.add(player);
            }
        }
       return  trackedPlayersSet;
    }

    void addFlyingPlayer(Player player) {
        /*Aviator aviator;
        TODO if(storage contains player){
            aviator = aviator from storage;
        }
        else{
            aviator = new Aviator(plugin);
        }*/
        flyingPlayers.put(player.getUniqueId(), new Aviator(plugin));
    }

    void removeFlyingPlayer(Player player) {
        flyingPlayers.remove(player.getUniqueId());
    }


}
