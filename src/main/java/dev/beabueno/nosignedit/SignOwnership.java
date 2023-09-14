package dev.beabueno.nosignedit;

import java.util.UUID;

public class SignOwnership {
    //data
    private UUID owner;
    private int x;
    private int y;
    private int z;

    public SignOwnership(UUID owner, int x, int y, int z) {
        this.owner = owner;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public UUID getOwner() {
        return owner;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
