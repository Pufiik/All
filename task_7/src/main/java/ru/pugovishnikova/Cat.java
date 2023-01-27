package ru.pugovishnikova;

public class Cat {
    private String name;
    private int appetite;
    private boolean satiety;
    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }
    public void eat(Plate p) {
        if (p.decreaseFood(appetite))
        this.satiety = true;
    }
    public  void info(){
        if (satiety)
            System.out.println(name+" is well-fed.");
        else
            System.out.println(name+" is hungry.");
    }
}
