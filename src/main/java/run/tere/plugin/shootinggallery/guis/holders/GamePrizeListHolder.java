package run.tere.plugin.shootinggallery.guis.holders;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import run.tere.plugin.shootinggallery.ShootingGallery;
import run.tere.plugin.shootinggallery.defines.GameStall;

import java.util.UUID;

public class GamePrizeListHolder implements InventoryHolder {

    private Inventory inventory;

    public GamePrizeListHolder() {
        this.inventory = Bukkit.createInventory(this, 54, "§a§l射的屋台一覧");
        this.initInventory();
    }

    private void initInventory() {
        for (GameStall gameStall : ShootingGallery.getInstance().getGameStallHandler().getGameStalls()) {
            UUID uuid = gameStall.getUUID();
            String name = gameStall.getName();
            inventory.addItem();
        }
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

}
