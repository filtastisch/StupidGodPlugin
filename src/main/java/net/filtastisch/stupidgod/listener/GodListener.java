package net.filtastisch.stupidgod.listener;

import net.filtastisch.stupidgod.StupidGod;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class GodListener implements Listener {

    private final StupidGod plugin = StupidGod.getPlugin();

    @EventHandler
    public void onDamageEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (plugin.getGodModePlayers().contains(p.getUniqueId())) {
                e.setCancelled(true);
            }
        }
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if (plugin.getGodModePlayers().contains(p.getUniqueId())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamageBlock(EntityDamageByBlockEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (plugin.getGodModePlayers().contains(p.getUniqueId())) {
                e.setCancelled(true);
            }
        }
    }

}
