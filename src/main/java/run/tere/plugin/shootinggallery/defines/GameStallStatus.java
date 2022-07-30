package run.tere.plugin.shootinggallery.defines;

import org.bukkit.entity.Player;

import java.util.UUID;

public class GameStallStatus {

    private UUID uuid;
    private Player nowPlaying;

    public GameStallStatus(UUID uuid) {
        this.uuid = uuid;
        this.nowPlaying = null;
    }

    public UUID getUUID() {
        return uuid;
    }

    public Player getNowPlaying() {
        return nowPlaying;
    }

    public void setNowPlaying(Player nowPlaying) {
        this.nowPlaying = nowPlaying;
    }

}
