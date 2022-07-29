package run.tere.plugin.shootinggallery.guis.defines;

import org.bukkit.inventory.Inventory;
import run.tere.plugin.shootinggallery.defines.GameStall;
import run.tere.plugin.shootinggallery.guis.holders.GamePrizeListHolder;
import run.tere.plugin.shootinggallery.guis.holders.GameStallHolder;
import run.tere.plugin.shootinggallery.guis.holders.GameStallListHolder;

public class GUIContainer {

    public static Inventory createGameStallListInventory() {
        return new GameStallListHolder().getInventory();
    }

    public static Inventory createGameStallSettingInventory(GameStall gameStall) {
        return new GameStallHolder(gameStall).getInventory();
    }

    public static Inventory createGamePrizeListInventory(GameStall gameStall) {
        return new GamePrizeListHolder(gameStall).getInventory();
    }

}
