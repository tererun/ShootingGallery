package run.tere.plugin.shootinggallery;

import org.bukkit.plugin.java.JavaPlugin;
import run.tere.plugin.shootinggallery.handlers.GameStallHandler;

public final class ShootingGallery extends JavaPlugin {

    private static ShootingGallery instance;

    private GameStallHandler gameStallHandler;

    @Override
    public void onEnable() {
        instance = this;
        this.gameStallHandler = GameStallHandler.loadGameStallHandler();
    }

    @Override
    public void onDisable() {

    }

    public static ShootingGallery getInstance() {
        return instance;
    }

    public GameStallHandler getGameStallHandler() {
        return gameStallHandler;
    }

}
