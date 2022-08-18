package run.tere.plugin.shootinggallery;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import run.tere.plugin.shootinggallery.commands.ShootingGalleryMainCommand;
import run.tere.plugin.shootinggallery.defines.GameStallStatus;
import run.tere.plugin.shootinggallery.guis.handlers.GUIHandler;
import run.tere.plugin.shootinggallery.handlers.GameStallHandler;
import run.tere.plugin.shootinggallery.handlers.GameStallStatusHandler;
import run.tere.plugin.shootinggallery.listeners.ShootingGalleryMainListener;
import run.tere.plugin.shootinggallery.schedulers.ArmorStandGamePrizeScheduler;

public final class ShootingGallery extends JavaPlugin {

    private static ShootingGallery instance;

    private GameStallHandler gameStallHandler;
    private GameStallStatusHandler gameStallStatusHandler;
    private ArmorStandGamePrizeScheduler armorStandGamePrizeScheduler;
    private GUIHandler guiHandler;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        gameStallHandler = GameStallHandler.loadGameStallHandler();
        gameStallStatusHandler = new GameStallStatusHandler(gameStallHandler);
        armorStandGamePrizeScheduler = new ArmorStandGamePrizeScheduler();
        guiHandler = new GUIHandler();

        armorStandGamePrizeScheduler.runTaskTimer(this, 0L, 1L);

        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
        for (GameStallStatus gameStallStatus : gameStallStatusHandler.getGameStallStatuses()) {
            gameStallStatus.removeAll();
        }
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

    public ArmorStandGamePrizeScheduler getArmorStandGamePrizeScheduler() {
        return armorStandGamePrizeScheduler;
    }

    public GameStallHandler getGameStallHandler() {
        return gameStallHandler;
    }

}
