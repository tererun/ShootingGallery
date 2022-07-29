package run.tere.plugin.shootinggallery.guis.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import run.tere.plugin.shootinggallery.ShootingGallery;
import run.tere.plugin.shootinggallery.defines.GameStall;
import run.tere.plugin.shootinggallery.guis.defines.GUIContainer;
import run.tere.plugin.shootinggallery.guis.defines.GUIItemContainer;
import run.tere.plugin.shootinggallery.guis.holders.GamePrizeListHolder;
import run.tere.plugin.shootinggallery.guis.holders.GameStallHolder;
import run.tere.plugin.shootinggallery.guis.holders.GameStallListHolder;
import run.tere.plugin.shootinggallery.handlers.GameStallHandler;
import run.tere.plugin.shootinggallery.utils.ItemStackUtil;
import run.tere.plugin.shootinggallery.utils.JsonUtil;

import java.util.UUID;

public class GUIHandler implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        GameStallHandler gameStallHandler = ShootingGallery.getInstance().getGameStallHandler();
        Inventory clickedInventory = e.getClickedInventory();
        ItemStack clickedItem = e.getCurrentItem();
        if (clickedInventory == null || clickedItem == null) return;
        InventoryHolder inventoryHolder = clickedInventory.getHolder();
        if (inventoryHolder instanceof GameStallHolder) {
            GameStallHolder gameStallHolder = (GameStallHolder) inventoryHolder;
            GameStall gameStall = gameStallHolder.getGameStall();
            String itemTag = ItemStackUtil.getItemKey(clickedItem, GUIItemContainer.GUI_CONTROL_ITEM_KEY);
            if (itemTag == null) return;
            if (itemTag.equalsIgnoreCase(""))
        } else if (inventoryHolder instanceof GameStallListHolder) {
            e.setCancelled(true);
            String itemTag = ItemStackUtil.getItemKey(clickedItem, GUIItemContainer.GUI_STALL_ITEM_KEY);
            if (itemTag == null) return;
            GameStall gameStall = gameStallHandler.getGameStall(UUID.fromString(itemTag));
            if (gameStall == null) return;
            Bukkit.getScheduler().runTask(ShootingGallery.getInstance(), () -> player.openInventory(GUIContainer.createGameStallSettingInventory(gameStall)));
        }
    }

}
