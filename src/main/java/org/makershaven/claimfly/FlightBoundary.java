package org.makershaven.claimfly;

import me.ryanhamshire.GriefPrevention.Claim;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

//X East[+] West[-] // Z North[-] South[+]
class FlightBoundary {
    private int checkDistance;
    private Player player;
    private Claim claimAtPlayer;
    private Claims claims;
    private Location playerLoc;
    private ClaimFly plugin;
    private Particle particle = Particle.REDSTONE;
    Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(255, 0, 0), 10.0F);

    FlightBoundary(ClaimFly plugin){
        this.claims = new Claims();
        this.plugin = plugin;
    }

    void showFlightBoundaries(Player player){
        PlayerTracker playerTracker = plugin.playerTracker;
        Aviator aviator = playerTracker.getAviator(player);
        if(!aviator.showBoundaries()){
            return;
        }
        int checkDistance = aviator.getBoundaryDistance();
        Claim claimAtPlayer = claims.getClaim(player);
        Location playerLoc = player.getLocation();

        if(claimAtPlayer!= null){
            Location[] locs = new Location[4];
            locs[0] = new Location(player.getWorld(),playerLoc.getBlockX()+ .5,playerLoc.getBlockY()+2,claimAtPlayer.getLesserBoundaryCorner().getBlockZ()+.5);
            locs[1] = new Location(player.getWorld(),claimAtPlayer.getLesserBoundaryCorner().getBlockX()+.5,playerLoc.getBlockY()+2,playerLoc.getBlockZ()+.5);
            locs[2] = new Location(player.getWorld(),playerLoc.getBlockX()+.5,playerLoc.getBlockY()+2,claimAtPlayer.getGreaterBoundaryCorner().getBlockZ()+.5);
            locs[3] = new Location(player.getWorld(),claimAtPlayer.getGreaterBoundaryCorner().getBlockX()+.5,playerLoc.getBlockY()+2,playerLoc.getBlockZ()+.5);

            for(int i =0; i <= 3;i++){
                if(playerLoc.distance(locs[i]) <= checkDistance){
                    player.spawnParticle(particle,locs[i],1,dustOptions);
                    player.spawnParticle(particle,locs[i].subtract(0,1,0),1,dustOptions);
                }
            }


        }
    }


}
