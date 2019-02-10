package org.makershaven.claimfly;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class FlightToggleListener implements Listener {


    private ClaimFly plugin;
    private FlightCheck flightCheck;
    private PlayerTracker playerTracker;


    FlightToggleListener(ClaimFly plugin) {
        this.plugin = plugin;
        this.flightCheck = new FlightCheck(plugin);
        this.playerTracker = plugin.playerTracker;

    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onFlightToggle(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        String checkResult;

        if (event.isFlying()) {
            if (plugin.config.getBoolean("debug")) {
                long sTime = System.nanoTime();
                checkResult = flightCheck.check(player);
                plugin.getLogger().info("FlightToggle check took " + (System.nanoTime() - sTime) + "ns");

            }
            else {
                checkResult = flightCheck.check(player);
            }


            if (!checkResult.equals(flightCheck.getFLIGHT_ALLOWED())) {

                Aviator aviator = playerTracker.getAviator(event.getPlayer());
                int toggleCooldown = plugin.getConfig().getInt("flight-toggle-cooldown") * 50;

                if ((System.currentTimeMillis() - aviator.getFlightToggleTimeStamp()) <= toggleCooldown) {
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
                            plugin.getConfig().getString("message.dont-spam-space")));
                    event.getPlayer().setAllowFlight(false);
                }
                else {
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', checkResult));
                }
                aviator.setFlightToggleTimeStamp();
                event.setCancelled(true);
            }
        }

    }
}

