package org.makershaven.claimfly;

import org.bukkit.entity.Player;

class FlightCheck {    //This seems pretty good, needs more testing.

    private Claims claims = new Claims();
    private ClaimFly plugin;
    private String noFlyOnServer;
    private String noFlyThisClaim;
    private String noFlyOutsideClaims;


    FlightCheck(ClaimFly plugin) {
        this.plugin = plugin;
        retrieveMessages();
    }

     String check(Player player) {
        if (!player.hasPermission("claimfly.use")) {
            return noFlyOnServer;
        }

        if (claims.isInAdminClaim(player)) {
            if(claims.hasAccessTrust(player)){
                return "allow";
            }
            else if (!player.hasPermission("claimfly.claims.admin")) {
                return noFlyThisClaim.replace("%ClaimOwner%",claims.getClaim(player).getOwnerName());
            }

        }
        else if (claims.isInClaim(player)) {
            if (!(claims.isClaimOwner(player) || claims.hasAccessTrust(player) || player.hasPermission("claimfly.claims.others"))) {
                return noFlyThisClaim.replace("%ClaimOwner%",claims.getClaim(player).getOwnerName());
            }

        }
        else if (!claims.isInClaim(player)) {
            if (!player.hasPermission("claimfly.claims.unclaimed")) {

                return noFlyOutsideClaims;
            }
        }
         return "allow";  //This was to play nicely with the fp loop. Doesn't help though.
    }

    private void retrieveMessages() {
        noFlyOnServer = plugin.getConfig().getString("message.cannot-fly-on-server");
        noFlyThisClaim = plugin.getConfig().getString("message.cannot-fly-this-claim");
        noFlyOutsideClaims = plugin.getConfig().getString("message.cannot-fly-outside-claims");

    }


}
