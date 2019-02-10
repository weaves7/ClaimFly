package org.makershaven.claimfly;

import org.bukkit.plugin.Plugin;

class Aviator {
    private boolean showBoundaries;
    private int boundaryDistance;
    private long flightToggleTimeStamp;

    Aviator(Plugin plugin){//TODO Add field for Player?
        this.showBoundaries = true;
        this.boundaryDistance = plugin.getConfig().getInt("show-boundary-distance");
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
