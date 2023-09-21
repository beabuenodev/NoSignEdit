package dev.beabueno.nosignedit;

import com.destroystokyo.paper.MaterialTags;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;

import java.util.UUID;

public class EventListener implements Listener {
    @EventHandler
    public void onSignPlace (BlockPlaceEvent e) {
        if (e.getBlock() instanceof Sign) {
            NoSignEdit.getPlugin().getData().addSign(e.getBlockPlaced().getLocation(), e.getPlayer().getUniqueId());
        }
    }

    @EventHandler
    public void onSignBreak (BlockBreakEvent e) {
        if (e.getBlock() instanceof Sign) {
            NoSignEdit.getPlugin().getData().removeSign(e.getBlock().getLocation());
        }
    }

    @EventHandler
    public void onSignEdit (SignChangeEvent e) {
        UUID signuuid = NoSignEdit.getPlugin().getData().getSignAtLocation(e.getBlock().getLocation());
        UUID playeruuid = e.getPlayer().getUniqueId();
        if (signuuid != playeruuid)
            e.setCancelled(true);
    }
}
