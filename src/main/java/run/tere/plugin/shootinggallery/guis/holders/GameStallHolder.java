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
        inventory.setItem(1, GUIItemContainer.getFromSelectItem());
        inventory.setItem(3, GUIItemContainer.getToSelectItem());
        inventory.setItem(5, GUIItemContainer.getChangeStallNameItem());
        inventory.setItem(7, GUIItemContainer.getDeleteSelectItem());
    }

    public GameStall getGameStall() {
        return gameStall;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

}
