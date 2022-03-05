package net.filtastisch.stupidgod.command;

import net.filtastisch.stupidgod.StupidGod;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor {

    private final StupidGod plugin = StupidGod.getPlugin();

    /*
    TODO:
        - /god
        - /god <player>
        - /god -f
        - /god <player> -f
     */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 0) {
                this.toggleGodMode(p);
                String godMsg = this.isGodMode(p) ? "§aDu bist nun im GodMode" : "§cDu bist nicht mehr im GodMode";
                p.sendMessage(godMsg);
            } else if (args.length == 1) {
                //toggle other or self fly
                if (args[0].equalsIgnoreCase("-f") || args[0].equalsIgnoreCase("--fly")) {
                    this.toggleGodMode(p);
                    p.setAllowFlight(true);
                } else {
                    Player target = null;
                    try {
                        target = Bukkit.getPlayer(args[0]);
                    }catch (Exception ignored){
                        p.sendMessage("§cDer spieler ist nicht online!");
                    }
                    if (target != null){
                        this.toggleGodMode(target);
                    }
                }
            } else if (args.length == 2) {
                Player target = null;
                try {
                    target = Bukkit.getPlayer(args[0]);
                }catch (Exception ignored){
                    p.sendMessage("§cDer spieler ist nicht online!");
                }
                if (target != null){
                    this.toggleGodMode(target);
                    target.setAllowFlight(true);
                }}

        }
        return false;
    }

    private void toggleGodMode(Player p) {
        if (plugin.getGodModePlayers().contains(p.getUniqueId())) {
            plugin.getGodModePlayers().remove(p.getUniqueId());
        } else {
            plugin.getGodModePlayers().add(p.getUniqueId());
        }
    }

    private boolean isGodMode(Player p){
        return plugin.getGodModePlayers().contains(p.getUniqueId());
    }

}
