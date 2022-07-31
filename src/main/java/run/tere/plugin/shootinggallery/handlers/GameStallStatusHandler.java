package run.tere.plugin.shootinggallery.handlers;

import org.bukkit.entity.Player;
import run.tere.plugin.shootinggallery.defines.GameStall;
import run.tere.plugin.shootinggallery.defines.GameStallStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameStallStatusHandler {

    private List<GameStallStatus> gameStallStatuses;

    public GameStallStatusHandler(GameStallHandler gameStallHandler) {
        this.gameStallStatuses = new ArrayList<>();
        for (GameStall gameStall : gameStallHandler.getGameStalls()) {
            this.gameStallStatuses.add(new GameStallStatus(gameStall.getUUID()));
        }
    }

    public GameStallStatus getGameStallStatus(UUID uuid) {
        for (GameStallStatus gameStallStatus : gameStallStatuses) {
            if (gameStallStatus.getUUID().equals(uuid)) {
                return gameStallStatus;
            }
        }
        return null;
    }

    public GameStallStatus getGameStallStatus(Player player) {
        for (GameStallStatus gameStallStatus : gameStallStatuses) {
            if (gameStallStatus.getNowPlaying().equals(player)) {
                return gameStallStatus;
            }
        }
        return null;
    }

    public List<GameStallStatus> getGameStallStatuses() {
        return gameStallStatuses;
    }

}
