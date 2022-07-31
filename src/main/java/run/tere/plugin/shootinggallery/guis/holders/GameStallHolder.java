package run.tere.plugin.shootinggallery.guis.holders;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import run.tere.plugin.shootinggallery.defines.GameStall;
import run.tere.plugin.shootinggallery.guis.defines.GUIItemContainer;

public class GameStallHolder implements InventoryHolder {

    private GameStall gameStall;
    private Inventory inventory;

    public GameStallHolder(GameStall gameStall) {
        this.gameStall = gameStall;
        this.inventory = Bukkit.createInventory(this, 9, gameStall.getName());
        this.initInventory();
    }

    private void initInventory() {
        inventory.addItem(
                GUIItemContainer.getFromSelectItem(),
                GUIItemContainer.getToSelectItem(),
                GUIItemContainer.getChangePrizeItem(),
                GUIItemContainer.getChangeStallNameItem(),
                GUIItemContainer.getSpawnClerkItem(),
                GUIItemContainer.getDeleteSelectItem()
        );
    }

    public GameStall getGameStall() {
        return gameStall;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

}
