package d2a.models;

import d2a.controller.sql.SQLController;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class SteamID {
    private String mDisplayName;
    private String mSteamID;

    public SteamID(String displayName, String steamID) {
        mDisplayName = displayName;
        mSteamID = steamID;
    }

    public void save(SQLController db) {
        String insertStatement = "INSERT INTO steamid(displayname, steamid) VALUES(?,?)";
        try {
            Connection connection = db.database();
            PreparedStatement pstmt = connection.prepareStatement(insertStatement);
            pstmt.setString(1, mDisplayName);
            pstmt.setString(2, mSteamID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger("SteamID").log(Level.SEVERE, "Error inserting into table \"steamid\", values "
                    + mDisplayName + " " + mSteamID);
            e.printStackTrace();
        }
    }


    public static ArrayList<SteamID> getAll(SQLController db) {
        String selectStatement = "SELECT * FROM steamid";

        try {
            Statement stmt = db.database().createStatement();
            ResultSet rs = stmt.executeQuery(selectStatement);
            ArrayList<SteamID> steamIDArrayList = new ArrayList<>();
            while (rs.next()) {
                steamIDArrayList.add(new SteamID(rs.getString("displayname"), rs.getString("steamid")));
            }
            return steamIDArrayList;
        } catch (SQLException e) {
            Logger.getLogger("SteamID").log(Level.SEVERE, "Error retrieving table \"steamid\"");
            e.printStackTrace();
        }
        return null;
    }

    public static class Model extends SQLController.LocalDataBaseModel {
        public Model() {

        }

        @Override
        public void createDB(Connection connection) {
            String createStatement = "CREATE TABLE IF NOT EXISTS steamid(index INTEGER PRIMARY KEY NOT NULL, "
                    + "displayname TEXT NOT NULL, "
                    + "steamid TEXT NOT NULL);";

            try {
                Statement stmt = connection.createStatement();
                stmt.execute(createStatement);
            } catch (SQLException e) {
                Logger.getLogger("SteamID").log(Level.SEVERE, "Error creating table \"steamid\"");
            }
        }
    }
}
