package run.tere.plugin.shootinggallery.defines;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import run.tere.plugin.shootinggallery.ShootingGallery;

import java.util.*;

public class GameStallStatus {

    private GameStall gameStall;
    private List<ArmorStandGamePrize> armorStandGamePrizes;

    public GameStallStatus(GameStall gameStall) {
        this.gameStall = gameStall;
        this.armorStandGamePrizes = new ArrayList<>();
        reset();
    }

    public void reset() {
        removeAll();
        for (GamePrize gamePrize : gameStall.getPrizes()) {
            ItemStack itemStack = gamePrize.getPrizeItem().clone();
            Random random = new Random();
            Bukkit.getScheduler().runTaskLater(ShootingGallery.getInstance(), () -> {
                ArmorStandGamePrize armorStandGamePrize = new ArmorStandGamePrize(this, itemStack, gameStall.getFromLocation());
                armorStandGamePrizes.add(armorStandGamePrize);
            }, random.nextInt(10));
        }
    }

    public void remove(ArmorStandGamePrize armorStandGamePrize) {
        armorStandGamePrize.getPrizeStand().remove();
        armorStandGamePrizes.remove(armorStandGamePrize);
        if (armorStandGamePrizes.size() <= 3) {
            reset();
        }
    }

    public void removeAll() {
        Iterator<ArmorStandGamePrize> armorStandGamePrizeIterator = armorStandGamePrizes.iterator();
        while (armorStandGamePrizeIterator.hasNext()) {
            ArmorStandGamePrize armorStandGamePrize = armorStandGamePrizeIterator.next();
            armorStandGamePrize.getPrizeStand().remove();
            armorStandGamePrizeIterator.remove();
        }
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
