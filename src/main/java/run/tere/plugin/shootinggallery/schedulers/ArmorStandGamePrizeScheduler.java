package run.tere.plugin.shootinggallery.schedulers;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import run.tere.plugin.shootinggallery.ShootingGallery;
import run.tere.plugin.shootinggallery.defines.ArmorStandGamePrize;
import run.tere.plugin.shootinggallery.defines.GameStall;
import run.tere.plugin.shootinggallery.defines.GameStallStatus;

import java.util.Random;

public class ArmorStandGamePrizeScheduler extends BukkitRunnable {

    @Override
    public void run() {
        for (GameStallStatus gameStallStatus : ShootingGallery.getInstance().getGameStallStatusHandler().getGameStallStatuses()) {
            GameStall gameStall = gameStallStatus.getGameStall();
            if (gameStall.getFromJsonLocation() == null || gameStall.getToJsonLocation() == null) continue;
            Location fromLocation = gameStall.getFromLocation();
            Location toLocation = gameStall.getToLocation();
            Vector direction = fromLocation.toVector().clone().subtract(toLocation.toVector().clone()).normalize();
            for (ArmorStandGamePrize armorStandGamePrize : gameStallStatus.getArmorStandGamePrizes()) {
                ArmorStand armorStand = armorStandGamePrize.getPrizeStand();
                if (armorStand.isDead()) continue;
                Location armorStandLocation = armorStand.getLocation();
                double sign;
                double fromToDistance = fromLocation.distanceSquared(toLocation);
                if (armorStandGamePrize.isGoing()) {
                    sign = 1;
                    double distance = toLocation.distanceSquared(armorStandLocation);
                    if (distance <= 0.6) {
                        armorStandGamePrize.setGoing(false);
                        sign = -1;
                    } else if (fromToDistance <= distance + 9) {
                        armorStand.teleport(toLocation);
                        armorStandGamePrize.setGoing(false);
                        sign = -1;
                    }
                } else {
                    sign = -1;
                    double distance = fromLocation.distanceSquared(armorStandLocation);
                    if (distance <= 0.6) {
                        armorStandGamePrize.setGoing(true);
                        sign = 1;
                    } else if (fromToDistance <= distance + 9) {
                        armorStand.teleport(fromLocation);
                        armorStandGamePrize.setGoing(true);
                        sign = 1;
                    }
                }
                Random random = new Random();
                armorStand.teleport(armorStandLocation.clone().add(direction.clone().multiply(sign * ((double) random.nextInt(50)) / 100)));
            }
        }
    }

}
