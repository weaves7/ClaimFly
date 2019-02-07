package org.makershaven.claimfly;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Location;
import org.bukkit.entity.Player;

class Claims {

    Claim getClaim(Player player) {
        Location location = player.getLocation();

        return GriefPrevention.instance.dataStore.getClaimAt(location, true, null);

    }

    boolean hasAccessTrust(Player player) {

        /*This is kinda odd but GP allowAccess returns null when a player has
        trust and a string when they do not*/
        String string = getClaim(player).allowAccess(player);
        return string == null;
    }

    boolean isClaimOwner(Player player) {

        return player.getName().equals(getClaim(player).getOwnerName());
    }

    boolean isInClaim(Player player) {

        return getClaim(player) != null;
    }

    boolean isInAdminClaim(Player player) {

        if (getClaim(player) != null) {

            return getClaim(player).isAdminClaim();
        }
        else
            return false;

    }

}

