package ru.pugovishnikova;

public class Treadmill implements Trial {

    private final String name;
    private int distance;

    public String getName() {
        return name;
    }

    public Treadmill(int distance, String name) {
        this.name = name;
        this.distance = (distance>0)?distance:distance*-1;
    }

    public int getDistance() {
        return distance;
    }

    public void changeDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public void trial(String name, int distance) {
        System.out.println(name + " is the currentTrial. The distance is " + distance + " km");
    }
}
