package org.makershaven.claimfly;

import org.bukkit.plugin.Plugin;

import java.io.Serializable;


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

}
