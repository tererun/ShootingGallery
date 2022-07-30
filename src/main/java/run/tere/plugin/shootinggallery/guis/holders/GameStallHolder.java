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
        inventory.setItem(0, GUIItemContainer.getFromSelectItem());
        inventory.setItem(2, GUIItemContainer.getToSelectItem());
        inventory.setItem(4, GUIItemContainer.getChangePrizeItem());
        inventory.setItem(6, GUIItemContainer.getChangeStallNameItem());
        inventory.setItem(8, GUIItemContainer.getDeleteSelectItem());
    }

    public GameStall getGameStall() {
        return gameStall;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

}
