package org.makershaven.claimfly;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class YamlDataStore implements DataStore {


    private File file;
    private YamlConfiguration dataFile;

    YamlDataStore(ClaimFly plugin) {
        file = new File(plugin.getDataFolder(), "data.yml");
        loadDataFile();
        createFile();
    }

    private void createFile() {
        if (!file.exists()) {
            try {
                dataFile.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void loadDataFile() {

        dataFile = YamlConfiguration.loadConfiguration(file);

    }


    @Override
    public void saveAviator(UUID uuid, Aviator aviator) {
        if (!dataFile.getKeys(false).contains(uuid.toString())) {
            dataFile.createSection(uuid.toString());
        }
        dataFile.set(uuid.toString(), aviator.serialize());


    }

    @Override
    public void saveAllAviators(Map<UUID, Aviator> map) {

        for (UUID uuid : map.keySet()) {
            saveAviator(uuid, map.get(uuid));
        }
    }


    @Override
    public Aviator loadAviator(UUID uuid) {
        Aviator aviator;

        if (dataFile.getConfigurationSection(uuid.toString()) != null) {
            Map<String, Object> map = dataFile.getConfigurationSection(uuid.toString()).getValues(false);

            return new Aviator(map);
        }
        return null;
    }

    @Override
    public void saveToDisk() {
        try {
            dataFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
