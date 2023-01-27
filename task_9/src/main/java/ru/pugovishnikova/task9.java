package ru.pugovishnikova;

import java.util.Scanner;

public class task9 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x,y,sum;
        System.out.println("Input matrix SIZE:");
        System.out.print("X = ");//ввод размерности х
        x=intInput();
        System.out.print("Y = ");
        y=intInput();//ввод размерности у
        System.out.println("");

        String[][] arr = new String[x][y];//создание массива строк
        System.out.println("Input data:");//заполнение массива
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++) {
                arr[i][j] = scanner.nextLine();
            }

        try { //обработка исключениея
            sumMatrix(arr);
        }
        catch(SizeException sizeException) {
            System.out.printf("The size you input is %s %s\n%s\n",sizeException.getX(),sizeException.getY(),sizeException.getMessage());
        }//исключение типа ошибка размерности
        catch (DataException dataException){
            System.out.printf("The coordinates are %s %s\n%s\n",dataException.getX(),dataException.getY(),dataException.getMessage());
        }//исключение типа ошибка данных
     

    }

    //проверка ввода на число для задания массива
    //**************************
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

    //****************************



    //функция обработки матрицы
        static void sumMatrix(String[][] arr) throws SizeException,DataException {
        int i, j, k, sum=0;
        if (arr.length != 4) {//обработка размерности
            throw new SizeException("Coordinate X wasn't input correct\n", arr.length + "", arr[0].length + "");

        } else if (arr[0].length != 4) {//обработка размерности
            throw new SizeException("Coordinate Y wasn't input correct\n", arr.length + "", arr[0].length + "");

        }

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                for (k = 0; k < arr[i][j].length(); k++)
                   if(!Character.isDigit((arr[i][j].charAt(k))))//обработка данных
                       throw new DataException("You input not number in pos\n", i+"",j+"");
                sum+=Integer.parseInt(arr[i][j]);
            }

        }
            System.out.println("sum =" + sum);//вывод суммы если всё корректно
    }
        static class SizeException extends MatrixException {//класс исключений размерности матрицы

            public SizeException(String message, String x, String y) {
                super(message, x, y);
            }
        }

        static class DataException extends MatrixException {//класс исключений данных матрицы

            public DataException(String message, String x, String y) {
                super(message, x, y);
            }
        }

      static  abstract class MatrixException extends Exception {//класс исключений матрицы
            private String x;
            private String y;

            public String getX() {
                return x;
            }

            public String getY() {
                return y;
            }

            public MatrixException(String message, String x, String y) {
                super(message);
                this.x = x;
                this.y = y;
            }
        }
    }

