package org.makershaven.claimfly;

import me.ryanhamshire.GriefPrevention.Claim;
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
    //private PlayerTracker playerTracker;

    FlightBoundary(ClaimFly plugin){
        this.claims = new Claims();
        this.plugin = plugin;
       // this.playerTracker = plugin.playerTracker;
    }

    void showFlightBoundaries(Player player){
        PlayerTracker playerTracker = plugin.playerTracker;
        if(!playerTracker.getAviator(player).showBoundaries()){
            return;
        }
        int checkDistance = plugin.getConfig().getInt("show-boundary-distance");
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
                    player.spawnParticle(Particle.BARRIER,locs[i],1);
                    player.spawnParticle(Particle.BARRIER,locs[i].subtract(0,1,0),1);
                }
            }


        }
    }


}
