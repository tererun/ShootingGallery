package run.tere.plugin.shootinggallery.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import run.tere.plugin.shootinggallery.ShootingGallery;
import run.tere.plugin.shootinggallery.defines.GameStall;
import run.tere.plugin.shootinggallery.defines.GameStallStatus;
import run.tere.plugin.shootinggallery.handlers.GameStallStatusHandler;
import run.tere.plugin.shootinggallery.utils.ChatUtil;
import run.tere.plugin.shootinggallery.utils.GameStallUtil;

import java.util.UUID;

public class ShootingGalleryMainListener implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        ItemStack handItem = player.getInventory().getItem(player.getInventory().getHeldItemSlot());
        Entity rightClickedEntity = e.getRightClicked();
        UUID gameStallUUID = GameStallUtil.getGameStallUUID(rightClickedEntity);
        GameStallStatusHandler gameStallStatusHandler = ShootingGallery.getInstance().getGameStallStatusHandler();
        if (gameStallUUID == null) return;
        GameStallStatus gameStallStatus = gameStallStatusHandler.getGameStallStatus(gameStallUUID);
        if (gameStallStatus == null) return;
        if (handItem == null) return;
        Material handItemType = handItem.getType();
        if (handItemType == Material.IRON_NUGGET) {

        } else if (handItemType == Material.GOLD_NUGGET) {

        } else {

        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
    }

}
