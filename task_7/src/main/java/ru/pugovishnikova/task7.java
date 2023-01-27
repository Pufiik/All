package ru.pugovishnikova;

import java.util.Scanner;

public class task7 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = input();//считаем количество еды в тарелке
        Plate plate = new Plate(n);//создадим объект тарелки

        //создадим массив котов
        Cat [] cats = {
                new Cat("Barsik",10),
                new Cat("Persik",100),
                new Cat("Tom", 20),
                new Cat("Tim", 15),
                new Cat("Tesha", 30),
                new Cat("Bim",  50)
        };

        //пройдемся по каждому элементу и покормим котов
        for (int i=0; i<cats.length; i++){
            cats[i].eat(plate);
            cats[i].info();
        }


        plate.info();
        int food = input();
        plate.addFood(food);
        plate.info();
    }


    //функции для проверки на ввод числа, которое надо добавить в кучу
    public static int input(){
        System.out.println("Input int: ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        while (checkInt(str)<=-1) {
            System.out.println("Try one more time:");
            str = scanner.nextLine();
        }
        return Integer.parseInt(str);
    }

    public static int checkInt (String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            System.out.println("Error Input value: you input no value");
            return -1;
        }
    }

}
