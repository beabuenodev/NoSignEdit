package dev.beabueno.nosignedit;

import lombok.Cleanup;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class SignOwnershipManager {
    //data
    private HashMap<Location, UUID> signdata;
    //sql
    private static final String SELECT_SIGN = "SELECT UUID, username, WorldUUID, x, y, z FROM sign";
    private static final String INSERT_SIGN = "INSERT INTO sign(UUID, username, WorldUUID, x, y, z) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_SIGN = "DELETE FROM sign WHERE x = ? AND y = ? AND z = ? AND WorldUUID = ?";

    public SignOwnershipManager () {
        signdata = new HashMap<Location, UUID>();
    }

    public boolean addSign (Location l, UUID o) {
        signdata.put(l, o);

        String username = Bukkit.getPlayer(o).getName();

        try {
            @Cleanup PreparedStatement stmt = Database.getConnection().prepareStatement(INSERT_SIGN);
            stmt.setString(1, o.toString());
            stmt.setString(2, username);
            stmt.setString(3, l.getWorld().getUID().toString());
            stmt.setInt(4, l.getBlockX());
            stmt.setInt(5, l.getBlockY());
            stmt.setInt(6, l.getBlockZ());

            int res = stmt.executeUpdate();

            if(res == 1) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeSign (Location l) {
        signdata.remove(l);

        try {
            @Cleanup PreparedStatement stmt = Database.getConnection().prepareStatement(DELETE_SIGN);
            stmt.setInt(1,l.getBlockX());
            stmt.setInt(2,l.getBlockY());
            stmt.setInt(3,l.getBlockZ());
            stmt.setString(4, l.getWorld().getUID().toString());

            int res = stmt.executeUpdate();

            if(res == 1) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public UUID getSignAtLocation (Location l) {
        return signdata.get(l);
    }

    private boolean runSelect() {
        try {
            @Cleanup PreparedStatement stmt = Database.getConnection().prepareStatement(SELECT_SIGN);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String UUIDaux = rs.getString("UUID");
                String username = rs.getString("username");
                String worldUUID = rs.getString("WorldUUID");
                int x = rs.getInt("x");
                int y = rs.getInt("y");
                int z = rs.getInt("z");
                Location l = new Location(Bukkit.getWorld(worldUUID), x, y, z);
                signdata.put(l, UUID.fromString(UUIDaux));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
