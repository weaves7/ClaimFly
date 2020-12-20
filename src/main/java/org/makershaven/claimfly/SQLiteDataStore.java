package org.makershaven.claimfly;


import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;


public class SQLiteDataStore implements DataStore {

    private Connection con;
    ClaimFly plugin;


    SQLiteDataStore(ClaimFly plugin) {
        this.plugin = plugin;
        con = getSQLConnection();
        createTables();
    }

//Todo get rid of concat, and change from if else to insert if not exist or whatever the proper thing is needed here.
    @Override
    public void saveAviator(UUID uuid, Aviator aviator) {
        try {
            PreparedStatement preStatement = con.prepareStatement("SELECT UUID FROM AVIATORS WHERE EXISTS " +
                    "(SELECT UUID FROM AVIATORS WHERE UUID='" + uuid.toString() + "');");
            ResultSet resultSet = preStatement.executeQuery();

            if (resultSet.next()) {
                PreparedStatement updateStatement = con.prepareStatement("UPDATE AVIATORS SET AVIATOR='" + aviator.serialize().toString() +
                        "' WHERE UUID='" + uuid.toString() + "';");
                updateStatement.executeUpdate();
                updateStatement.close();
            }
            else {
                PreparedStatement addStatement = con.prepareStatement("INSERT INTO AVIATORS VALUES (?,?);");
                addStatement.setString(1, uuid.toString());
                addStatement.setString(2, aviator.serialize().toString());
                addStatement.executeUpdate();
                addStatement.close();
            }
            preStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveAllAviators(Map<UUID, Aviator> uuidAviatorMap) {
        for (UUID uuid : uuidAviatorMap.keySet()) {
            saveAviator(uuid, uuidAviatorMap.get(uuid));
        }
    }

    @Override
    public Aviator loadAviator(UUID uuid) {


        Aviator aviator = null;
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT AVIATOR FROM AVIATORS WHERE UUID='" + uuid.toString() + "';");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                aviator = new Aviator(rs.getString("AVIATOR"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aviator;
    }

    @Override
    public void saveToDisk() {
        plugin.getLogger().info("SQLite database saved.");
    }

    private Connection getSQLConnection() {
        String dbname = "data";
        File dataFolder = new File(plugin.getDataFolder(), dbname + ".db");
        if (!dataFolder.exists()) {
            try {
                //noinspection ResultOfMethodCallIgnored
                dataFolder.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "File write error: " + dbname + ".db");
            }
        }
        try {
            if (con != null && !con.isClosed()) {
                return con;
            }
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return con;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "SQLite exception on initialize", ex);
        } catch (ClassNotFoundException ex) {
            plugin.getLogger().log(Level.SEVERE, "You need the SQLite JDBC library. Google it. Put it in /lib folder.");
        }
        return null;
    }

    private void createTables() {
        try {
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS AVIATORS " +
                    "(UUID TEXT PRIMARY KEY   NOT NULL," +
                    "AVIATOR            TEXT  NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
