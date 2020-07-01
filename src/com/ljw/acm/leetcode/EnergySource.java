package com.ljw.acm.leetcode;

/**
 * @author duke.fu
 * @date 2020-6-30 16:31
 */
public class EnergySource {

    private final long MAX_LEVEL = 100;

    private long level = 50;

    private static boolean keepRunning = true;

    public EnergySource() {
        this.replenish();;
        new Thread(() -> replenish()).start();
    }


    public long getUnitsAvaiable() {
        System.out.println(level);
        return level;
    }

    public boolean useEnergy(final long units) {
        if (units > 0 && level >= units) {
            level -= units;
            return true;
        }
        return false;
    }

    public void stopEnergySource() {
        keepRunning = false;
    }

    public void replenish() {
        while (keepRunning) {
            if (level < MAX_LEVEL) {
                level++;
            }
            System.out.println(level);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 响应中断
            }
        }
    }

    public static void main(String[] args) {
        final EnergySource energySource = new EnergySource();

    }
}
