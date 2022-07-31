package run.tere.plugin.shootinggallery.handlers;

import run.tere.plugin.shootinggallery.ShootingGallery;
import run.tere.plugin.shootinggallery.defines.GameStall;
import run.tere.plugin.shootinggallery.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameStallHandler {

    private List<GameStall> gameStalls;

    public GameStallHandler() {
        this.gameStalls = new ArrayList<>();
    }

    public GameStall getGameStall(UUID uuid) {
        for (GameStall gameStall : gameStalls) {
            if (gameStall.getUUID().equals(uuid)) {
                return gameStall;
            }
        }
        return null;
    }

    public List<GameStall> getGameStalls() {
        return gameStalls;
    }

    public void save() {
        saveGameStallHandler(this);
    }

    public static void saveGameStallHandler(GameStallHandler gameStallHandler) {
        JsonUtil.toJson(ShootingGallery.getInstance(), gameStallHandler, "gameStallHandler.json");
    }

    public static GameStallHandler loadGameStallHandler() {
        GameStallHandler gameStallHandler = (GameStallHandler) JsonUtil.fromJson(ShootingGallery.getInstance(), "gameStallHandler.json", GameStallHandler.class);
        if (gameStallHandler == null) {
            gameStallHandler = new GameStallHandler();
            saveGameStallHandler(gameStallHandler);
        }
        return gameStallHandler;
    }

}
