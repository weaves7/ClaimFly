package org.makershaven.claimfly;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashSet;


class FlyingPlayers {                      //This class is updated ish

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
                if (!checkResult.equals("allow")) {
                    player.setFlying(false);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', fCheck.check(player)));
                }

            }

        }
    }
}


















            /*

            if (plugin.getConfig().getBoolean("debug")) {
                logger.info("Player, " + player.getName() + ", checked");
            }

            if (player.isFlying()) {
                if (claims.isInClaim(player) && player.hasPermission("claimfly.use")) {
                    if (claims.isClaimOwner(player) || claims.hasAccessTrust(player) || player.hasPermission("claimfly.claims.others")) {
                        continue;
                    }                   //Maybe change these to !checks and do the else statements?
                    if (claims.isInAdminClaim(player) && player.hasPermission("claimfly.claims.admin")) {
                        continue;
                    }
                    else {
                        player.setFlying(false);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.cannot-fly-this-claim")));
                        player.sendMessage(ChatColor.RED + "Ask, " + claims.getClaim(player).getOwnerName() + " for /accesstrust");
                    }
                }       //Probably need an else if.
                else if (!claims.isInClaim(player) && player.hasPermission("claimfly.use")) {
                    if (!player.hasPermission("claimfly.claims.unclaimed")) {
                        player.setFlying(false);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.cannot-fly-outside-claims")));
                    }

                }
                else {
                    player.setFlying(false);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.cannot-fly-on-server")));
                }
            }

        }
    }
}*/


            /*if (player.isFlying()) {
                if (claims.isInClaim(player) && pilot.isPlayer(player)) {
                    if (claims.isClaimOwner(player) || claims.hasAccessTrust(player) || pilot.isAdmin(player)
                            || pilot.isMod(player) || (pilot.isBuilder(player) && claims.isInAdminClaim(player))) {
                        return;
                    } else {
                        pilot.groundPilot(player);
                        groundedPilots.add(player);
                        player.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
                                plugin.getConfig().getString("message.cannot-fly-this-claim")));
                        player.getPlayer().sendMessage(
                                ChatColor.RED + "Ask, " + claims.getClaim(player).getOwnerName() + " for /accesstrust");

                    }
                } else if (!claims.isInClaim(player) && pilot.isAdmin(player) || pilot.isVIP(player)) {
                    return;


                } else if (!pilot.isPlayer(player)) {
                    pilot.groundPilot(player);
                    groundedPilots.add(player);
                    player.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
                            plugin.getConfig().getString("message.cannot-fly-on-server")));

                } else {
                    pilot.groundPilot(player);
                    groundedPilots.add(player);
                    player.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
                            plugin.getConfig().getString("message.cannot-fly-outside-claims")));

                }
            }

            if (!player.isFlying()) {
                groundedPilots.add(player);
            }

        }
        if (plugin.getConfig().getBoolean("debug")) {
            logger.info("Grounding pilots " + groundedPilots.toString());
        }
        flyingPlayers.removeAll(groundedPilots);
        groundedPilots.clear();
        if (plugin.getConfig().getBoolean("debug")) {
            logger.info(groundedPilots.toString() + " left to ground");
        }*/


