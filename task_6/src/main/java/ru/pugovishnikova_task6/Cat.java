package ru.pugovishnikova_task6;

public class Cat extends Animal {

    @Override
    public void swim(int a) {
        System.out.println(name + "Can't swim");

    }

    @Override
    public void run(int b) {
        if (b>=0 && b<= 200)
            System.out.printf(name + " runs %d meters\n", b);
        else System.out.printf(name + " can't run the distance %d. The max is 200 meters\n", b);
    }

    public Cat(String names) {
        this.name = names;
    }
}
