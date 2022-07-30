package run.tere.plugin.shootinggallery.utils;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import run.tere.plugin.shootinggallery.ShootingGallery;
import run.tere.plugin.shootinggallery.defines.GameStall;

import java.util.UUID;

public class GameStallUtil {

    public static final NamespacedKey GAME_STALL_CLERK_KEY;

    static {
        GAME_STALL_CLERK_KEY = new NamespacedKey(ShootingGallery.getInstance(), "GameStallClerkKey");
    }

    public static Villager spawnGameStallClerk(Location location, GameStall gameStall) {
        return location.getWorld().spawn(location, Villager.class, villager -> {
            villager.getPersistentDataContainer().set(GAME_STALL_CLERK_KEY, PersistentDataType.STRING, gameStall.getUUID().toString());
            villager.setAI(false);
            villager.setInvulnerable(true);
        });
    }

    public static UUID getGameStallUUID(Entity entity) {
        PersistentDataContainer persistentDataContainer = entity.getPersistentDataContainer();
        if (!persistentDataContainer.has(GAME_STALL_CLERK_KEY, PersistentDataType.STRING)) return null;
        String uuidString = persistentDataContainer.get(GAME_STALL_CLERK_KEY, PersistentDataType.STRING);
        return uuidString == null ? null : UUID.fromString(uuidString);
    }

}
