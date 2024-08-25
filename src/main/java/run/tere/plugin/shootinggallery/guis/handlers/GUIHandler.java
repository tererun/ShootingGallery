package run.tere.plugin.shootinggallery.guis.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import run.tere.plugin.shootinggallery.ShootingGallery;
import run.tere.plugin.shootinggallery.defines.GamePrize;
import run.tere.plugin.shootinggallery.defines.GameStall;
import run.tere.plugin.shootinggallery.defines.GameStallStatus;
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

import java.util.*;

public class GUIHandler implements Listener {

    public GUIHandler() {
        Bukkit.getServer().getPluginManager().registerEvents(this, ShootingGallery.getInstance());
    }
    private Map<UUID, BukkitTask> listeningPlayers = new HashMap<>();
    private Map<UUID, GameStall> listeningPlayerGameStalls = new HashMap<>();

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        if (listeningPlayers.containsKey(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            BukkitTask task = listeningPlayers.get(e.getPlayer().getUniqueId());
            task.cancel();
            listeningPlayers.remove(e.getPlayer().getUniqueId());
            String message = e.getMessage();
            listeningPlayerGameStalls.get(e.getPlayer().getUniqueId()).setName(message);
            listeningPlayerGameStalls.remove(e.getPlayer().getUniqueId());
            ChatUtil.sendMessage(e.getPlayer(), "§a名称を変更しました!");
        }
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
            } else if (itemTag.equalsIgnoreCase("changeStallNameItem")) {
                listeningPlayers.put(player.getUniqueId(), Bukkit.getScheduler().runTaskLater(ShootingGallery.getInstance(), () -> {
                    listeningPlayers.remove(player.getUniqueId());
                    listeningPlayerGameStalls.remove(player.getUniqueId());
                    ChatUtil.sendMessage(player, "§c名称入力がタイムアウトしました");
                }, 20 * 30));
                listeningPlayerGameStalls.put(player.getUniqueId(), gameStall);
                ChatUtil.sendMessage(player, "§a名称を入力してください!(チャットに30秒以内で入力してください)");
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
                    ShootingGallery.getInstance().getGameStallStatusHandler().getGameStallStatuses().add(new GameStallStatus(gameStall));
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
            GameStallStatus gameStallStatus = ShootingGallery.getInstance().getGameStallStatusHandler().getGameStallStatus(gameStall.getUUID());
            List<GamePrize> gamePrizeList = new ArrayList<>();
            for (ItemStack itemStack : closedInventory.getContents()) {
                if (itemStack != null) gamePrizeList.add(new GamePrize(itemStack));
            }
            gameStall.setPrizes(gamePrizeList);
            gameStallStatus.reset();
            ChatUtil.sendMessage(player, "§aアイテムを設定しました!");
        }
    }

}
