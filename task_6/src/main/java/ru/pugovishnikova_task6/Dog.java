package ru.pugovishnikova_task6;

public class Dog extends Animal{

    @Override
    public void run(int a) {
        if (a >= 0 && a<= 500)
            System.out.printf(name + " runs %d meters\n", a);
        else System.out.printf(name + " can't run the distance %d. The max is 500 meters\n", a);
    }

    @Override
    public void swim(int b) {
        if (b>=0 && b<= 10)
            System.out.printf(name + " swims %d meters\n", b);
        else System.out.printf(name + " can't swim the distance %d. The max is 10 meters\n", b);
    }

    public Dog(String names) {
        this.name = names;
    }
}
