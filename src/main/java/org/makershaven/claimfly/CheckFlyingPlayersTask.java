package org.makershaven.claimfly;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CheckFlyingPlayersTask extends BukkitRunnable {

    private PlayerTracker playerTracker;
    private FlightCheck flightCheck;

    CheckFlyingPlayersTask(ClaimFly plugin) {

        this.playerTracker = plugin.playerTracker;
        this.flightCheck = new FlightCheck(plugin);
    }

    @Override
    public void run() {

            checkFlyingPlayers();

    }
    private void checkFlyingPlayers() {
        String checkResult;

        for (Player player : playerTracker.getTrackedPlayersSet()) {
            if (player.isFlying()) {
                checkResult = flightCheck.check(player);
                if (!checkResult.equals(flightCheck.getFLIGHT_ALLOWED())) {
                    player.setFlying(false);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', checkResult));
                }
            }
        }
    }

}