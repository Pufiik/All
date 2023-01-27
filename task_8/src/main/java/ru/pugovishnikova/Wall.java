package ru.pugovishnikova;

public class Wall implements Trial {

    private final String name;
    private int height;

    public String getName() {
        return name;
    }



    public Wall(int height, String name) {
        this.name =name;
        this.height = (height>0)?height:height*-1;
    }

    public int getDistance() {
        return height;
    }

    public void changeDistance(int height) {
        this.height = height;
    }

    @Override
    public void trial(String name, int distance) {
        System.out.println(name + " is the currentTrial. The height is " + height + " m" );
    }
}
