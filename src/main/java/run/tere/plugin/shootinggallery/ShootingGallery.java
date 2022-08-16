package run.tere.plugin.shootinggallery;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import run.tere.plugin.shootinggallery.commands.ShootingGalleryMainCommand;
import run.tere.plugin.shootinggallery.guis.handlers.GUIHandler;
import run.tere.plugin.shootinggallery.handlers.GameStallHandler;
import run.tere.plugin.shootinggallery.handlers.GameStallStatusHandler;
import run.tere.plugin.shootinggallery.listeners.ShootingGalleryMainListener;

public final class ShootingGallery extends JavaPlugin {

    private static ShootingGallery instance;

    private GameStallHandler gameStallHandler;
    private GameStallStatusHandler gameStallStatusHandler;
    private GUIHandler guiHandler;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        gameStallHandler = GameStallHandler.loadGameStallHandler();
        gameStallStatusHandler = new GameStallStatusHandler(gameStallHandler);
        guiHandler = new GUIHandler();

        registerCommands();
        registerEvents();
    }

    private void registerCommands() {
        getCommand("sg").setExecutor(new ShootingGalleryMainCommand());
    }

    private void registerEvents() {
        registerEvent(new ShootingGalleryMainListener());
    }

    private void registerEvent(Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, this);
    }

    public static ShootingGallery getInstance() {
        return instance;
    }

    public GUIHandler getGUIHandler() {
        return guiHandler;
    }

    public GameStallStatusHandler getGameStallStatusHandler() {
        return gameStallStatusHandler;
    }

    public GameStallHandler getGameStallHandler() {
        return gameStallHandler;
    }

}
