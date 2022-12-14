package run.tere.plugin.shootinggallery.guis.holders;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import run.tere.plugin.shootinggallery.defines.GamePrize;
import run.tere.plugin.shootinggallery.defines.GameStall;
import java.util.List;

public class GamePrizeListHolder implements InventoryHolder {

    private GameStall gameStall;
    private Inventory inventory;

    public GamePrizeListHolder(GameStall gameStall) {
        this.gameStall = gameStall;
        this.inventory = Bukkit.createInventory(this, 54, gameStall.getName());
        this.initInventory();
    }

    private void initInventory() {
        List<GamePrize> gamePrizes = gameStall.getPrizes();
        for (int i = 0; i < gamePrizes.size(); i++) {
            GamePrize gamePrize = gamePrizes.get(i);
            inventory.setItem(i, gamePrize.getPrizeItem());
        }
    }

    public GameStall getGameStall() {
        return gameStall;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

}

