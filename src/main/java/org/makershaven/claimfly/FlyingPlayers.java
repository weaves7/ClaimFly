package org.makershaven.claimfly;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashSet;


class FlyingPlayers {


    private HashSet<Player> flyingPlayers = new HashSet<>();
    private FlightCheck fCheck;





    FlyingPlayers(ClaimFly plugin) {
        this.fCheck = new FlightCheck(plugin);

    }


    boolean setContains(Player player) {

        return flyingPlayers.contains(player);
    }

    int getSetSize() {

        return flyingPlayers.size();
    }

    void putFlyingPlayer(Player player) {
        flyingPlayers.add(player);

    }

    void removeFlyingPlayer(Player player) {

        flyingPlayers.remove(player);
    }

    void checkFlyingPlayers() {
        String checkResult;

        for (Player player : flyingPlayers) {

            if (player.isFlying()) {
                checkResult = fCheck.check(player);
                if (!checkResult.equals(fCheck.getFLIGHT_ALLOWED())) {
                    player.setFlying(false);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', checkResult));
                }

            }

        }
    }
}
