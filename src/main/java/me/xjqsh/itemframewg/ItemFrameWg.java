package me.xjqsh.itemframewg;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import me.xjqsh.itemframewg.Listener.InteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemFrameWg extends JavaPlugin {

    public static StateFlag item_frame_interact;
    public static StateFlag flower_pot_interact;

    @Override
    public void onLoad() {
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        try {
            StateFlag flag = new StateFlag("item-frame-interact", true);
            registry.register(flag);
            item_frame_interact = flag;
        } catch (FlagConflictException e) {
            Flag<?> existing = registry.get("item-frame-interact");
            if (existing instanceof StateFlag) {
                item_frame_interact = (StateFlag) existing;
            } else {
                getLogger().warning("rua!!!");
            }
        }

        try {
            StateFlag flag = new StateFlag("flower-pot-interact", true);
            registry.register(flag);
            flower_pot_interact = flag;
        } catch (FlagConflictException e) {
            Flag<?> existing = registry.get("flower-pot-interact");
            if (existing instanceof StateFlag) {
                flower_pot_interact = (StateFlag) existing;
            } else {
                getLogger().warning("rua!!!");
            }
        }
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new InteractListener(this),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
