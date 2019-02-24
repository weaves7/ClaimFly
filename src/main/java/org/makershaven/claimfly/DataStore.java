package org.makershaven.claimfly;


import java.util.Map;
import java.util.UUID;

public interface DataStore {

    void saveAviator(UUID uuid, Aviator aviator);

    void saveAllAviators(Map<UUID, Aviator> uuidAviatorMap);

    Aviator loadAviator(UUID uuid);

    void saveToDisk();


}
