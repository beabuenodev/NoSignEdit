package dev.beabueno.nosignedit;

import lombok.Value;

@Value
public class DatabaseConfig {
    String uri;
    String user;
    String pw;
}
