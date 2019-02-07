package org.makershaven.claimfly;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class ClaimFly extends JavaPlugin { //This class is updated, but untested.

    FlyingPlayers flyingPlayers;
    PluginDescriptionFile pdfFile;
    BukkitTask checkFlyingPlayersTask;
    FileConfiguration config;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        flyingPlayers = new FlyingPlayers(this);
        pdfFile = getDescription();
        config = getConfig();


        // Start the checking task on the flying players
        startCheckTask(this, 1, 20* config.getInt("check-interval"));

        // register my listeners
        getServer().getPluginManager().registerEvents(new FlightToggleListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);

        // register my commands
        getCommand("ClaimFly").setExecutor(new Commands(this));

        // adds all players on the server to be checked, needed for /reload or
        // for players that join before plugin loads
        getServer().getOnlinePlayers().forEach(player -> flyingPlayers.putFlyingPlayer(player));

    }



    void startCheckTask(Plugin plugin, long delay, long interval) {
        this.checkFlyingPlayersTask = new CheckFlyingPlayersTask(this).runTaskTimer(plugin, delay, interval);
    }

}
