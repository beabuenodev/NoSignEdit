package dev.beabueno.nosignedit;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    private FileConfiguration config;
    @Getter
    private DatabaseConfig databaseConfig;
    public Config() {
        this.config = NoSignEdit.getPlugin().getConfig();
        this.databaseConfig = new DatabaseConfig(config.getString("db.uri"), config.getString("db.user"), config.getString("db.pw"));
    }
}
