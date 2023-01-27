package ru.pugovishnikova;

import java.util.Random;
import java.util.Scanner;

public class task8{
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        byte[][] combinations = {{0,1,2}, {0,2,1}, {1,0,2}, {1,2,0},{2,0,1},{2,1,0}}; //рандомные комбинации последовательности прохождения испытаний участниками
        Capabilities[] participants = new Capabilities[3];//массив участников
        Trial[] trials = new Trial[2];//массив испытаний




        System.out.println("Input the treadmillDistance:");
        int distance = intInput();
        Treadmill treadmill = new Treadmill(distance, "trial - treadmill");
        trials[0] = treadmill;//созданиие беговой дорожки


        System.out.println("Input the wallHeight:");
        int height= intInput();
        Wall wall = new Wall(height, "trial - wall");
        trials[1] = wall;//создание стены


        System.out.println("Input the humanName:");
        String humanName = scanner.nextLine();
        System.out.println("Input the humanRate:");
        int humanRate = intInput();
        if (humanRate>40){
            System.out.println("Human's rate is very high! Current rate is 0 km/h");
            humanRate = 0;
        }
        System.out.println("Input the humanEnergy:");
        int humanEnergy = intInput();
        System.out.println("Input the humanHeight:");
        int humanHeight = intInput();

        Human human = new Human(humanName,humanRate,humanHeight,humanEnergy);
        participants[0] = human;//создание человека



        System.out.println("Input the catName:");
        String catName = scanner.nextLine();
        System.out.println("Input the catRate:");
        int catRate = intInput();
        if (catRate>50){
            System.out.println("Cat's rate is very high! Current rate is 0 km/h");
            catRate = 0;
        }
        System.out.println("Input the catEnergy:");
        int catEnergy = intInput();
        System.out.println("Input the catHeight:");
        int catHeight = intInput();

        Cat cat = new Cat(catName,catRate,catHeight,catEnergy);
        participants[1]=cat;//создание кота



        System.out.println("Input the robotName:");
        String robotName = scanner.nextLine();
        System.out.println("Input the robotRate:");
        int robotRate = intInput();
        System.out.println("Input the robotEnergy:");
        int robotEnergy = intInput();
        System.out.println("Input the robotHeight:");
        int robotHeight = intInput();

        Robot robot = new Robot(robotName,robotRate,robotHeight,robotEnergy);
        participants[2]=robot; //создание робота


            int RANDRAND = random.nextInt(2);//генератор рандомного испытания: 0 - стена, 1 - дорожка
            int RAND = random.nextInt(6); //генерация рандомной комбинации последовательности прохождения
                while (true) {
                    trials[RANDRAND].trial(trials[RANDRAND].getName(), trials[RANDRAND].getDistance());



                    participants[combinations[RAND][0]].run(trials[RANDRAND].getDistance());
                    participants[combinations[RAND][1]].run(trials[RANDRAND].getDistance());
                    participants[combinations[RAND][2]].run(trials[RANDRAND].getDistance());

                    System.out.println("Do you want change the runDistance? If yes, input yes or YES."); //изменение дистанции пользователем по желанию
                    if (!scanner.next().equalsIgnoreCase("YES")) break;
                    System.out.println("Input new distance:");
                    int newDistance = intInput();
                    trials[RANDRAND].changeDistance(newDistance);
                }
                while (true){
                    trials[(RANDRAND+1)%2].trial(trials[(RANDRAND+1)%2].getName(), trials[(RANDRAND+1)%2].getDistance());
                    participants[combinations[RAND][0]].jump(trials[(RANDRAND+1)%2].getDistance());
                    participants[combinations[RAND][1]].jump(trials[(RANDRAND+1)%2].getDistance());
                    participants[combinations[RAND][2]].jump(trials[(RANDRAND+1)%2].getDistance());

                    System.out.println("Do you want change the jumpHeight? If yes, input yes or YES.");
                    if (!scanner.next().equalsIgnoreCase("YES")) break;
                    System.out.println("Input new height:");
                    int newDistance = intInput();
                    trials[(RANDRAND+1)%2].changeDistance(newDistance);
                }


        System.out.println("TESTS PASSED!");
    }


    //функции проверки ввода
    public static boolean checkInput(String string){
        return (checkInt(string)!="error");
    }

    public static String checkInt (String string) {
        try {
            Integer.parseInt(string);
            return string;
        } catch (NumberFormatException e) {
            System.out.println("Error Input value: you input no value");
            return "error";
        }
    }

    public static int intInput(){
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        while (!checkInput(str)){
            System.out.println("Input one more time.");
            str = scanner.nextLine();
        }

       return (Integer.parseInt(str)>0)?Integer.parseInt(str):Integer.parseInt(str)*(-1);
    }
}


