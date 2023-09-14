package dev.beabueno.nosignedit;

import java.util.HashMap;
import java.util.UUID;

public class SignOwnershipManager {
    //data
    private HashMap<Integer, SignOwnership> signdata;
    private int currentid;

    public SignOwnershipManager () {
        currentid = 0;
        signdata = new HashMap<Integer, SignOwnership>();
    }

    public void addSign (SignOwnership s) {
        signdata.put(currentid + 1, s);
        currentid++;
    }

    public void addSign (UUID owner, int x, int y, int z) {
        SignOwnership s = new SignOwnership (owner, x, y, z);
        addSign(s);
    }
}
