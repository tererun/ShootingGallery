package run.tere.plugin.shootinggallery.defines;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameStallStatus {

    private UUID uuid;
    private List<ArmorStand> prizeStands;

    public GameStallStatus(UUID uuid) {
        this.uuid = uuid;
        this.prizeStands = new ArrayList<>();
    }

    public UUID getUUID() {
        return uuid;
    }

}
