package run.tere.plugin.shootinggallery.guis.holders;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import run.tere.plugin.shootinggallery.ShootingGallery;
import run.tere.plugin.shootinggallery.defines.GameStall;
import run.tere.plugin.shootinggallery.guis.defines.GUIItemContainer;

public class GameStallListHolder implements InventoryHolder {

    private Inventory inventory;

    public GameStallListHolder() {
        this.inventory = Bukkit.createInventory(this, 54, "§a§l射的屋台一覧");
        this.initInventory();
    }

    private void initInventory() {
        for (GameStall gameStall : ShootingGallery.getInstance().getGameStallHandler().getGameStalls()) {
            inventory.addItem(GUIItemContainer.createGameStallItem(gameStall));
        }
        inventory.setItem(53, GUIItemContainer.getCreateSelectItem());
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

}
