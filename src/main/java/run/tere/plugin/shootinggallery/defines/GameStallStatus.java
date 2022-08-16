package run.tere.plugin.shootinggallery.defines;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameStallStatus {

    private GameStall gameStall;
    private List<ArmorStandGamePrize> armorStandGamePrizes;

    public GameStallStatus(GameStall gameStall) {
        this.gameStall = gameStall;
        this.armorStandGamePrizes = new ArrayList<>();
    }

    public ArmorStandGamePrize getArmorStandGamePrize(UUID armorStandUUID) {
        for (ArmorStandGamePrize armorStandGamePrize : armorStandGamePrizes) {
            if (armorStandGamePrize.getPrizeStand().getUniqueId().equals(armorStandUUID)) {
                return armorStandGamePrize;
            }
        }
        return null;
    }

    public List<ArmorStandGamePrize> getArmorStandGamePrizes() {
        return armorStandGamePrizes;
    }

    public GameStall getGameStall() {
        return gameStall;
    }

}
