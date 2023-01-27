package ru.pugovishnikova;

import java.util.Scanner;

public class Robot implements Capabilities {

    Scanner scanner = new Scanner(System.in);

    private String name;
    private int maxRate= 0;//маскимальная скорость, которую возможно развить
    private int maxBattery=0;//время в минутах, которое робот может работать
    private int currentBattery=0;//сколько энергии осталось на работу
    private int maxHeight = 0; //максимальная высота прыжка

    public String getName() {
        return name;
    }

    public int getMaxRate() {
        return maxRate;
    }

    public int getMaxHeight() {
        return maxHeight;
    }


    public Robot(String name, int maxRate, int maxHeight, int maxBattery) {
        this.name = name;
        this.maxRate = maxRate;
        this.maxHeight = maxHeight;
        this.currentBattery = maxBattery;
        this.maxBattery = maxBattery;
    }



    @Override
    public void run(int distance) {
        if (this.maxRate*currentBattery/60 >= distance) {//формула расчета дистанции
            currentBattery -= distance / maxRate * 60; //усталость после испытания
            System.out.println(name + " passed the test");
        }
        else {
            System.out.println(name + " don't have enough battery."); //восмтпноаление энергии
            System.out.println("Do you want to recover it? If yes, input y or Y");
            if (scanner.next().equalsIgnoreCase("Y"))
                currentBattery= maxBattery;

        }
    }

    @Override
    public void jump(int height) {

        if (this.maxHeight >= height) {
            currentBattery -= 0.1*currentBattery;
            System.out.println(name + " passed the test");
        }
        else {
            System.out.println(name + " don't have enough energy.");
            System.out.println("Do you want to recover it? If yes, input y or Y");
            if (scanner.next().equalsIgnoreCase("Y"))
                currentBattery= maxBattery;
        }
    }
}
