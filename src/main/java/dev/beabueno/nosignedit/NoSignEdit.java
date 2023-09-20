package dev.beabueno.nosignedit;

import lombok.Getter;
import lombok.extern.java.Log;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Log
public final class NoSignEdit extends JavaPlugin {

    @Getter
    private static NoSignEdit plugin = null;
    private static Config config = null;

    @Override
    public void onEnable() {
        log.info("[NoSignEdit] Enabled!");
        NoSignEdit.plugin = this;

        //Parse config
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        NoSignEdit.config = new Config();

        // DB connection
        Database.getConnection();
        if (! Database.isConnected()) {
            log.severe("Error connecting to database!!!");
            Bukkit.getServer().shutdown();
            Bukkit.shutdown();
        }
        log.info(String.format("[NoSignEdit] connected to DB at %s", config.getDatabaseConfig().getUri() ));

        // Register listeners
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onDisable() {
        log.info("[NoSignEdit] Disabled!");
        Database.disconnect();
    }

    public static Config getPluginConfig() {
        return NoSignEdit.config;
    }
}
