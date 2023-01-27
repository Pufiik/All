package ru.pugovishnikova;

import java.util.ArrayList;

public class Task11 {
    public static void main(String[] args) {
        Apple apple = new Apple();
        Orange orange = new Orange();
        Box<Apple> box1 = new Box<Apple>(15);
        box1.put(apple);
        Apple[] apples = new Apple[20];
        for (int i=0; i<20; i++){
            apples[i] = new Apple();
        }
        int a=0;
        System.out.println(box1.getCurrentCount());
        while(box1.put(apples[a])){
            System.out.printf("put apple №%d\n", a++);
            System.out.println(box1.getCurrentCount());
        }


        Box<Orange> box2 = new Box<Orange>(13);
        box2.put(new Orange());
        box2.put(orange);
        System.out.println(box1.compare(box2));
        box1.showBox();
        box2.showBox();



        Box<Apple> box3 = new Box<Apple>(3);
        transApple(box1,box3);

        box1.showBox();
        System.out.println("");
        box3.showBox();
        Box<Apple> box4 = new Box<Apple>(5);
        //trans(box1,box4);
        System.out.println(box3.compare(box2));

        box1.showBox();
        System.out.println("");
        box4.showBox();
        System.out.println(box1.getFruits().getClass().arrayType());
        System.out.println(box1.getFruits().getClass().getName());
        System.out.println(box2.getFruits().getClass().getName());


    }



    public static void transApple(Box<Apple> box1, Box<Apple> box2){
        if (box1.getCurrentCount()>0 && box2.getCurrentCount()<box2.getMaxCount()){
            box1.getFruits().remove(0);
            box2.put(new Apple());
        }
        else System.out.println("box2 is overflowing");
    }

    public static void transOrange(Box<Orange> box1, Box<Orange> box2){
        if (box1.getCurrentCount()>0 && box2.getCurrentCount()<box2.getMaxCount()){
            box1.getFruits().remove(0);
            box2.put(new Orange());
        }
        else System.out.println("box2 is overflowing");
    }
}



   abstract class Fruit{

        protected float weight;
        protected String type;

        float getWeight(){
            return weight;
        }


   }
    class Apple extends Fruit{


        public Apple() {
            super.weight = 1.0f;
            super.type = "Apple";
        }
        @Override
        public String toString() {
            return String.format("%s; %s;", type, weight);
        }

    }

    class Orange extends Fruit{
        public Orange() {
            super.weight = 1.5f;
            super.type = "Orange";
        }
        @Override
        public String toString() {
            return String.format("%s; %f;",type, weight);
        }
    }



    class Box<T extends Fruit>  {
        private int maxCount;

        public int getMaxCount() {
            return maxCount;
        }

        private int currentCount;

        public int getCurrentCount() {
            return currentCount;
        }


        ArrayList<T> fruits = new ArrayList<>();

        public ArrayList<T> getFruits() {
            return fruits;
        }



        public Box(int maxCount) {
            this.maxCount = maxCount;
            this.currentCount=0;
        }

        boolean put(T fruit){
            if (currentCount<maxCount)
                fruits.add(fruit);
            return currentCount++<maxCount;
        }

        float getWeight(){
            return fruits.size()*fruits.get(0).weight;
        }

        boolean compare(Box box){
           return getWeight()==box.getWeight();
        }




        void showBox(){
            for (T item: fruits){
                System.out.println(item);
            }
        }
    }



