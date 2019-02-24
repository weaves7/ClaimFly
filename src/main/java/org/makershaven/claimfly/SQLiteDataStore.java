package org.makershaven.claimfly;



import java.sql.Connection;
import java.util.Map;
import java.util.UUID;

//Java Database Connectivity theory by telusko
public class SQLiteDataStore implements DataStore {

    Connection con;
    ClaimFly plugin;

    SQLiteDataStore(ClaimFly plugin){
        this.plugin = plugin;
    }


    @Override
    public void saveAviator(UUID uuid, Aviator aviator) {

    }

    @Override
    public void saveAllAviators(Map<UUID, Aviator> uuidAviatorMap) {

    }

    @Override
    public Aviator loadAviator(UUID uuid) {
        return null;
    }

    @Override
    public void saveToDisk() {

    }



}
