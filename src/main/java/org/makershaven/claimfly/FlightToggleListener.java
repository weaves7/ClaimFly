package org.makershaven.claimfly;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class FlightToggleListener implements Listener {        //This class is updated take two, seems good so far.


    private ClaimFly plugin;
    private FlightCheck fCheck;


    FlightToggleListener(ClaimFly plugin) {

        this.plugin = plugin;
        this.fCheck = new FlightCheck(plugin);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onFlightToggle(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        String checkResult;

        if (event.isFlying()) {
            if (plugin.config.getBoolean("debug")) {
                long sTime = System.nanoTime();
                checkResult = fCheck.check(player);
                plugin.getLogger().info("FlightToggle check took " + (System.nanoTime() - sTime) + "ns");

            }
            else {
                checkResult = fCheck.check(player);
            }


            if (!checkResult.equals(fCheck.getFLIGHT_ALLOWED())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', checkResult));
            }
        }

    }
}

