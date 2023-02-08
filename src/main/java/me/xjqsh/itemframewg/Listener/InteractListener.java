package me.xjqsh.itemframewg.Listener;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import io.papermc.paper.event.player.PlayerFlowerPotManipulateEvent;
import io.papermc.paper.event.player.PlayerItemFrameChangeEvent;
import me.xjqsh.itemframewg.ItemFrameWg;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InteractListener implements Listener {

    private final ItemFrameWg plugin;

    private final RegionQuery query;

    public InteractListener(ItemFrameWg instance){
        this.plugin=instance;
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        this.query = container.createQuery();
    }

    @EventHandler
    void onChangeItemFrame(PlayerItemFrameChangeEvent event){
        Player player = event.getPlayer();
        if(player.hasPermission("if.item-frame.bypass"))return;

        Location loc = event.getItemFrame().getLocation();
        ApplicableRegionSet set = getSet(loc);

        if(set.queryState(null,ItemFrameWg.item_frame_interact) == StateFlag.State.DENY){
            event.setCancelled(true);
        }
    }

    @EventHandler
    void onChangeFlowerPot(PlayerFlowerPotManipulateEvent event){
        Player player = event.getPlayer();
        if(player.hasPermission("if.flower-pot.bypass"))return;

        Location loc = event.getFlowerpot().getLocation();
        ApplicableRegionSet set = getSet(loc);

        if(set.queryState(null,ItemFrameWg.flower_pot_interact) == StateFlag.State.DENY){
            event.setCancelled(true);
        }
    }

    private ApplicableRegionSet getSet(Location loc) {
        com.sk89q.worldedit.util.Location location = BukkitAdapter.adapt(loc);
        return query.getApplicableRegions(location);
    }
}
