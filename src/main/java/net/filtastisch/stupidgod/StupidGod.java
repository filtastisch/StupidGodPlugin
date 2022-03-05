package net.filtastisch.stupidgod;

import net.filtastisch.spigot.SpigotConfig;
import net.filtastisch.stupidgod.command.GodCommand;
import net.filtastisch.stupidgod.listener.GodListener;
import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class StupidGod extends JavaPlugin {

    private SpigotConfig defaultConfig;
    public static StupidGod plugin;
    private String godSelfPermission;
    private List<UUID> godModePlayers;

    @Override
    public void onEnable() {
        plugin = this;
        this.registerDefaultConfig();
        this.loadValues();
        this.loadFeatures();
    }

    @Override
    public void onDisable() {
    }

    public void registerDefaultConfig(){
        this.defaultConfig = new SpigotConfig(this, "config.yml").register();

        this.defaultConfig.registerDefaults();
    }

    public void loadValues(){
        this.godModePlayers = new ArrayList<>();
    }

    public void loadFeatures(){
        PluginManager pm = Bukkit.getPluginManager();
        /*------------- Listener -------------*/
        pm.registerEvents(new GodListener(), this);
        /*------------- Commands -------------*/
        Objects.requireNonNull(this.getCommand("god")).setExecutor(new GodCommand());
    }

    public SpigotConfig getDefaultConfig() {
        return defaultConfig;
    }

    public static StupidGod getPlugin() {
        return plugin;
    }

    public Permission getGodSelfPermission() {
        return new Permission(this.godSelfPermission);
    }

    public List<UUID> getGodModePlayers() {
        return godModePlayers;
    }
}
