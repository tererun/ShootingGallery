package run.tere.plugin.shootinggallery;

import org.bukkit.plugin.java.JavaPlugin;
import run.tere.plugin.shootinggallery.handlers.GameStallHandler;
import run.tere.plugin.shootinggallery.handlers.GameStallStatusHandler;

public final class ShootingGallery extends JavaPlugin {

    private static ShootingGallery instance;

    private GameStallHandler gameStallHandler;
    private GameStallStatusHandler gameStallStatusHandler;

    @Override
    public void onEnable() {
        instance = this;
        this.gameStallHandler = GameStallHandler.loadGameStallHandler();
        this.gameStallStatusHandler = new GameStallStatusHandler(this.gameStallHandler);
    }

    @Override
    public void onDisable() {

    }

    public static ShootingGallery getInstance() {
        return instance;
    }

    public GameStallStatusHandler getGameStallStatusHandler() {
        return gameStallStatusHandler;
    }

    public GameStallHandler getGameStallHandler() {
        return gameStallHandler;
    }

}
