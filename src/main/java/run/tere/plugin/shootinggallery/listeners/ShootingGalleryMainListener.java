package run.tere.plugin.shootinggallery.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import run.tere.plugin.shootinggallery.ShootingGallery;
import run.tere.plugin.shootinggallery.defines.GameStall;
import run.tere.plugin.shootinggallery.utils.GameStallUtil;

import java.util.UUID;

public class ShootingGalleryMainListener implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        Entity rightClickedEntity = e.getRightClicked();
        UUID gameStallUUID = GameStallUtil.getGameStallUUID(rightClickedEntity);
        if (gameStallUUID == null) return;
        GameStall gameStall = ShootingGallery.getInstance().getGameStallHandler().getGameStall(gameStallUUID);
        if (gameStall == null) return;

    }

}
