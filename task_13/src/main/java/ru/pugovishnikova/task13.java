package ru.pugovishnikova;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class task13 {
    public static final int CARS_COUNT = 4;
    public static String[] WINNERS = new String[4];
    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        CountDownLatch countDownLatch2 = new CountDownLatch(CARS_COUNT);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT);
        Semaphore semaphore = new Semaphore(CARS_COUNT/2);
        System.out.println("ATTENTION >>> Preparing!!!");

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cyclicBarrier, semaphore, countDownLatch, WINNERS, countDownLatch2);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            countDownLatch.await();
            System.out.println( "\n" + WINNERS[0].toUpperCase() + " IS A WINNER!!!\n");
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        try {
            countDownLatch2.await();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("ATTENTION  >>> RACE FINISHED!!!");

    }
}

    class Race {

        private ArrayList<Stage> stages;
        public ArrayList<Stage> getStages() { return stages; }
        public Race(Stage... stages) {
            this.stages = new ArrayList<>(Arrays.asList(stages));
        }

    }

     class Tunnel extends Stage {
        public Tunnel() {
            this.length = 80;
            this.description = "Tunnel " + length + " meters";
        }
        @Override
        public void go(Car c) {
            try {
                try {
                    System.out.println(c.getName() + " preparing to stage (wait): " +
                            description);
                    System.out.println(c.getName() + " started the stage: " + description);
                    Thread.sleep(length / c.getSpeed() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(c.getName() + " finished stage: " +
                            description);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


 abstract class Stage {
    protected int length;
   // protected int count;

    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}



class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Road " + length + " meters";

    }
    @Override
    public void go(Car c) {

        try {
            System.out.println(c.getName() + " started the stage: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " finished the stage: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



class Car implements Runnable {
    private String[] winners;
    CyclicBarrier cyclicBarrier;
    Semaphore semaphore;

    CountDownLatch countDownLatch, countDownLatch2;
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public String[] getWinners() {
        return winners;
    }

    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CyclicBarrier cyclicBarrier, Semaphore semaphore, CountDownLatch countDownLatch,String[] winners, CountDownLatch countDownLatch2) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Participant #" + CARS_COUNT;
        this.cyclicBarrier = cyclicBarrier;
        this.semaphore = semaphore;
        this.countDownLatch = countDownLatch;
        this.winners = winners;
        this.countDownLatch2 = countDownLatch2;
    }

    @Override
    public void run() {
        int i=0;
        try {

            System.out.println(this.name + " preparing");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " prepared");
            cyclicBarrier.await();

            race.getStages().get(0).go(this);
            semaphore.acquire();
            race.getStages().get(1).go(this);
            semaphore.release();
            race.getStages().get(2).go(this);
            winners[i++]=this.getName();
            countDownLatch.countDown();
            countDownLatch2.countDown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




