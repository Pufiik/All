package ru.pugovishnikova;

import java.util.Scanner;

public class Cat implements Capabilities {
    Scanner scanner = new Scanner(System.in);
    private String name;
    private int maxEnergy=0;
    private int currentEnergy =0;
    private int maxRate=0;
    private int maxHeight = 0; //максимальная высота прыжка


    public String getName() {
        return name;
    }
    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getMaxRate() {
        return maxRate;
    }

    public int getMaxHeight() {
        return maxHeight;
    }


    public Cat(String name, int maxRate, int maxHeight, int maxEnergy) {
        this.name = name;
        this.maxRate = maxRate;
        this.maxHeight = maxHeight;
        this.maxEnergy = maxEnergy;
        this.currentEnergy = maxEnergy;
    }

    @Override
    public void run(int distance)  {
        if (this.maxRate*currentEnergy/60 >=distance) {
            currentEnergy -= distance / maxRate * 60;
            System.out.println(name + " passed the test");
        }
        else {
            System.out.println(name + " don't have enough energy.");
            System.out.println("Do you want to recover it? If yes, input y or Y");
            if (scanner.next().equalsIgnoreCase("Y"))
                currentEnergy= maxEnergy;

        }
    }

    @Override
    public void jump(int height) {
        if (this.maxHeight >= height) {
            currentEnergy -= 0.1*currentEnergy;
            System.out.println(name + " passed the test");
        }
        else {
            System.out.println(name + " don't have enough energy.");
            System.out.println("Do you want to recover it? If yes, input y or Y");
            if (scanner.next().equalsIgnoreCase("Y"))
                currentEnergy= maxEnergy;
        }
    }
}
