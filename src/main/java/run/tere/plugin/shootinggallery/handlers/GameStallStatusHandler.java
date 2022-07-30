package run.tere.plugin.shootinggallery.handlers;

import run.tere.plugin.shootinggallery.defines.GameStallStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameStallStatusHandler {

    private List<GameStallStatus> gameStallStatuses;

    public GameStallStatusHandler() {
        this.gameStallStatuses = new ArrayList<>();
    }

    public GameStallStatus getGameStallStatus(UUID uuid) {
        for (GameStallStatus gameStallStatus : gameStallStatuses) {
            if (gameStallStatus.getUUID().equals(uuid)) {
                return gameStallStatus;
            }
        }
        return null;
    }

    public List<GameStallStatus> getGameStallStatuses() {
        return gameStallStatuses;
    }

}
