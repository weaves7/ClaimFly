package org.makershaven.claimfly;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class FlightToggleListener implements Listener {        //This class is updated take two, seems good so far.


    //private Claims claims;
    private ClaimFly plugin;
    private FlightCheck fCheck;


    FlightToggleListener(ClaimFly plugin) {

        this.plugin = plugin;
        //this.claims = plugin.claims;
        this.fCheck = new FlightCheck(plugin);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onFlightToggle(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        String checkResult;

        if(plugin.config.getBoolean("debug")) {
            long sTime = System.nanoTime();
            checkResult = fCheck.check(player);
            plugin.getLogger().info("FlightToggle check took " + (System.nanoTime() - sTime)+"ns");

        }
        else{
            checkResult = fCheck.check(player);
        }

        if (event.isFlying()) {
            if (!checkResult.equals("allow")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', checkResult));
            }
        }

    }
}


/*        if (event.isFlying()) {
            if (player.hasPermission("claimfly.use")) {
                if (claims.isInClaim(player)) {
                    if (claims.isClaimOwner (player) || claims.hasAccessTrust (player) || player.hasPermission ("claimfly.claims.others")) {
                        return;
                    }                               //Maybe change these to !checks and do the else statements?
                    if (claims.isInAdminClaim (player) && player.hasPermission ("claimfly.claims.admin")) {
                        return;
                    }
                    else {
                        event.setCancelled (true);
                        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.cannot-fly-this-claim")));
                        event.getPlayer().sendMessage(ChatColor.RED + "Ask, " + claims.getClaim(player).getOwnerName() + " for /accesstrust");
                    }
                }
                else if (!claims.isInClaim(player)) {

                    if (!player.hasPermission("claimfly.claims.unclaimed")) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.cannot-fly-outside-claims")));
                    }

                }
            }
            else {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.cannot-fly-on-server")));
            }
        }


    }


}*/
