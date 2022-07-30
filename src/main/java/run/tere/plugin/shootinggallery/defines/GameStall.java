package run.tere.plugin.shootinggallery.defines;

import org.bukkit.Location;
import run.tere.plugin.shootinggallery.ShootingGallery;
import run.tere.plugin.shootinggallery.handlers.GameStallHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameStall {

    private String uuid;
    private String name;
    private JsonLocation fromLocation;
    private JsonLocation toLocation;
    private List<GamePrize> prizes;

    public GameStall(String name) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.fromLocation = null;
        this.toLocation = null;
        this.prizes = new ArrayList<>();
    }

    public UUID getUUID() {
        return UUID.fromString(uuid);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.save();
    }

    public Location fromLocation() {
        return JsonLocation.toBukkitLocation(fromLocation);
    }

    public Location toLocation() {
        return JsonLocation.toBukkitLocation(toLocation);
    }

    public void setFromLocation(Location fromLocation) {
        this.fromLocation = JsonLocation.toJsonLocation(fromLocation);
        this.save();
    }

    public void setToLocation(Location toLocation) {
        this.toLocation = JsonLocation.toJsonLocation(toLocation);
        this.save();
    }

    public void setPrizes(List<GamePrize> prizes) {
        this.prizes = prizes;
        save();
    }

    public List<GamePrize> getPrizes() {
        return prizes;
    }

    public void save() {
        GameStallHandler.saveGameStallHandler(ShootingGallery.getInstance().getGameStallHandler());
    }

}
