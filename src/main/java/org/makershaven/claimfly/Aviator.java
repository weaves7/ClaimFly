package org.makershaven.claimfly;

import org.bukkit.plugin.Plugin;

import java.io.Serializable;


class Aviator implements Serializable {
    private boolean showBoundaries;
    private int boundaryDistance;
    private long flightToggleTimeStamp;

    Aviator(Plugin plugin){
        this.showBoundaries = plugin.getConfig().getBoolean("boundary.show-by-default");
        this.boundaryDistance = plugin.getConfig().getInt("boundary.show-distance");
        this.flightToggleTimeStamp = System.currentTimeMillis();
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

}
