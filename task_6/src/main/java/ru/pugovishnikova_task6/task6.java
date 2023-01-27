package ru.pugovishnikova_task6;

import java.util.Random;
import java.util.Scanner;

public class task6 {

    public static void main(String[] argc) {

        int n = 1;
        String str = "";
        int count = 1;
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int RAND = 0;
        int count_cats = 0, count_dogs = 0;

        while (true) {
            System.out.println("Input the count of animals: ");
            str = scanner.nextLine();
            count = checkInt(str);//вызов проверки данных на тип int
            if (count > 0) break;// проверка на положительность
            System.out.println("Invalid count, input one more time");
        }



        Animal[] animals = new Animal[count];

        for (int i=0; i<count; i++){
            System.out.println("THE NEW PROJECT:\n");
            RAND = random.nextInt(2);//0 - comp, 1 - man
            if (RAND == 0) {
                System.out.println("We made a cat, please input his name: ");
                str=scanner.nextLine();
                animals[i]=new Cat(str);
                System.out.println("Input the run distance:");
                while (true){
                    str=scanner.nextLine();
                    n = checkInt(str);
                    if (n>0) break;
                }
                animals[i].run(n);
                System.out.println("Input the swim distance:");
                while (true){
                    str=scanner.nextLine();
                    n = checkInt(str);
                    if (n>0) break;
                }
                animals[i].swim(n);
                System.out.println("");
                count_cats++;
            }

            else {
                System.out.println("We made a dog, please input his name: ");
                str=scanner.nextLine();
                animals[i]=new Dog(str);
                System.out.println("Input the run distance:");
                while (true){
                    str=scanner.nextLine();
                    n = checkInt(str);
                    if (n>0) break;
                }
                animals[i].run(n);
                System.out.println("Input the swim distance:");
                while (true){
                    str=scanner.nextLine();
                    n = checkInt(str);
                    if (n>0) break;
                }
                animals[i].swim(n);
                System.out.println("");
                count_dogs++;

        }
    }
        System.out.printf("count of cats: %d\ncount of dogs: %d\n",count_cats,count_dogs);
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
