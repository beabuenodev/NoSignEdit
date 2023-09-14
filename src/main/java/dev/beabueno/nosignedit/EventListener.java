package dev.beabueno.nosignedit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;

public class EventListener implements Listener {
    @EventHandler
    public void onSignPlace (BlockPlaceEvent e) {

    }

    @EventHandler
    public void onSignEdit (SignChangeEvent e) {

    }
}
