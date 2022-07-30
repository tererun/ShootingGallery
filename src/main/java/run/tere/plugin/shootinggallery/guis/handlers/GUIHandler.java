package run.tere.plugin.shootinggallery.guis.handlers;

import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import run.tere.plugin.shootinggallery.ShootingGallery;
import run.tere.plugin.shootinggallery.defines.GamePrize;
import run.tere.plugin.shootinggallery.defines.GameStall;
import run.tere.plugin.shootinggallery.guis.defines.GUIContainer;
import run.tere.plugin.shootinggallery.guis.defines.GUIItemContainer;
import run.tere.plugin.shootinggallery.guis.holders.GamePrizeListHolder;
import run.tere.plugin.shootinggallery.guis.holders.GameStallHolder;
import run.tere.plugin.shootinggallery.guis.holders.GameStallListHolder;
import run.tere.plugin.shootinggallery.handlers.GameStallHandler;
import run.tere.plugin.shootinggallery.utils.ChatUtil;
import run.tere.plugin.shootinggallery.utils.GameStallUtil;
import run.tere.plugin.shootinggallery.utils.InventoryUtil;
import run.tere.plugin.shootinggallery.utils.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GUIHandler implements Listener {

    public GUIHandler() {
        Bukkit.getServer().getPluginManager().registerEvents(this, ShootingGallery.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        GameStallHandler gameStallHandler = ShootingGallery.getInstance().getGameStallHandler();
        Inventory clickedInventory = e.getClickedInventory();
        ItemStack clickedItem = e.getCurrentItem();
        if (clickedInventory == null || clickedItem == null) return;
        InventoryHolder inventoryHolder = clickedInventory.getHolder();
        if (inventoryHolder instanceof GameStallHolder) {
            e.setCancelled(true);
            Location playerLocation = player.getLocation();
            GameStallHolder gameStallHolder = (GameStallHolder) inventoryHolder;
            GameStall gameStall = gameStallHolder.getGameStall();
            String itemTag = ItemStackUtil.getItemKey(clickedItem, GUIItemContainer.GUI_CONTROL_ITEM_KEY);
            if (itemTag == null) return;
            if (itemTag.equalsIgnoreCase("fromSelectItem")) {
                InventoryUtil.closeInventory(player);
                gameStall.setFromLocation(playerLocation);
                ChatUtil.sendMessage(player, "§a始点を変更しました!");
            } else if (itemTag.equalsIgnoreCase("toSelectItem")) {
                InventoryUtil.closeInventory(player);
                gameStall.setToLocation(playerLocation);
                ChatUtil.sendMessage(player, "§a終点を変更しました!");
            } else if (itemTag.equalsIgnoreCase("changePrizeItem")) {
                InventoryUtil.openInventory(player, GUIContainer.createGamePrizeListInventory(gameStall));
            } else if (itemTag.equalsIgnoreCase("deleteSelectItem")) {
                InventoryUtil.closeInventory(player);
                gameStallHandler.getGameStalls().remove(gameStall);
                gameStallHandler.save();
                ChatUtil.sendMessage(player, "§a屋台を削除しました!");
            } else if (itemTag.equalsIgnoreCase("changePrizeNameItem")) {
                new AnvilGUI.Builder()
                        .onComplete((anvilPlayer, text) -> {
                            gameStall.setName(text);
                            ChatUtil.sendMessage(anvilPlayer, "§a名称を変更しました!");
                            return AnvilGUI.Response.close();
                        })
                        .text("屋台名をここに入力")
                        .itemLeft(new ItemStack(Material.NAME_TAG))
                        .title("名称を入力してください")
                        .plugin(ShootingGallery.getInstance())
                        .open(player);
            } else if (itemTag.equalsIgnoreCase("spawnClerkItem")) {
                InventoryUtil.closeInventory(player);
                GameStallUtil.spawnGameStallClerk(playerLocation, gameStall);
                ChatUtil.sendMessage(player, "§a店員をスポーンさせました!");
            }
        } else if (inventoryHolder instanceof GameStallListHolder) {
            e.setCancelled(true);
            String guiControlItemTag = ItemStackUtil.getItemKey(clickedItem, GUIItemContainer.GUI_CONTROL_ITEM_KEY);
            String guiStallItemTag = ItemStackUtil.getItemKey(clickedItem, GUIItemContainer.GUI_STALL_ITEM_KEY);
            if (guiControlItemTag != null) {
                if (guiControlItemTag.equalsIgnoreCase("createSelectItem")) {
                    GameStall gameStall = new GameStall("新規屋台");
                    gameStallHandler.getGameStalls().add(gameStall);
                    gameStallHandler.save();
                    ChatUtil.sendMessage(player, "§a屋台を作成しました!");
                }
            } else if (guiStallItemTag != null) {
                GameStall gameStall = gameStallHandler.getGameStall(UUID.fromString(guiStallItemTag));
                if (gameStall == null) return;
                Bukkit.getScheduler().runTask(ShootingGallery.getInstance(), () -> player.openInventory(GUIContainer.createGameStallSettingInventory(gameStall)));
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        Inventory closedInventory = e.getInventory();
        InventoryHolder closedInventoryHolder = closedInventory.getHolder();
        if (closedInventoryHolder instanceof GamePrizeListHolder) {
            GamePrizeListHolder gamePrizeListHolder = (GamePrizeListHolder) closedInventoryHolder;
            GameStall gameStall = gamePrizeListHolder.getGameStall();
            List<GamePrize> gamePrizeList = new ArrayList<>();
            for (ItemStack itemStack : closedInventory.getContents()) {
                if (itemStack != null) gamePrizeList.add(new GamePrize(itemStack));
            }
            gameStall.setPrizes(gamePrizeList);
            ChatUtil.sendMessage(player, "§aアイテムを設定しました!");
        }
    }

}
