package org.makershaven.claimfly;

import org.bukkit.plugin.Plugin;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


class Aviator implements Serializable {
    private boolean showBoundaries;
    private int boundaryDistance;
    private long flightToggleTimeStamp;
    private boolean flightActive;

    Aviator(Plugin plugin){
        this.showBoundaries = plugin.getConfig().getBoolean("boundary.show-by-default");
        this.boundaryDistance = plugin.getConfig().getInt("boundary.show-distance");
        this.flightToggleTimeStamp = System.currentTimeMillis();
        this.flightActive = false;
    }

    Aviator(Map<String,Object> map){
        this.showBoundaries = (boolean) map.get("ShowBoundaries");
        this.boundaryDistance = (int) map.get("BoundaryDistance");
        this.flightToggleTimeStamp = (long) map.get("FlightToggleTimeStamp");
        this.flightActive = (boolean) map.get("FlightActive");
    }

    boolean showBoundaries() {
        return this.showBoundaries;
    }

    void setShowBoundaries(boolean showBoundaries) {
        this.showBoundaries = showBoundaries;
    }

    int getBoundaryDistance() {
        return this.boundaryDistance;
    }

    void setBoundaryDistance(int boundaryDistance) {
        this.boundaryDistance = boundaryDistance;
    }

    long getFlightToggleTimeStamp() {
        return this.flightToggleTimeStamp;
    }

    void setFlightToggleTimeStamp() {
        this.flightToggleTimeStamp = System.currentTimeMillis();
    }

    boolean isFlightActive() {
        return flightActive;
    }

    void setFlightActive(boolean flightActive){
        this.flightActive = flightActive;
    }


    Map<String, Object> serialize() {
        Map<String,Object> map = new HashMap<>();
        map.put("ShowBoundaries",showBoundaries);
        map.put("BoundaryDistance",boundaryDistance);
        map.put("FlightToggleTimeStamp",flightToggleTimeStamp);
        map.put("FlightActive",flightActive);

        return map;
    }
}
