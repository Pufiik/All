package ru.pugovishnikova;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Введите катеты первого треугольника: ");
        DashaClass triangle1 = new DashaClass(input());
        System.out.println("Введите катеты второго треугольника: ");
        DashaClass triangle2 = new DashaClass(input());
        System.out.println("ИНФОРМАЦИЯ О ПЕРВОМ ТРЕУГОЛЬНИКЕ");
        System.out.println("Первый катет равен: " + triangle1.getKatet1());
        System.out.println("Второй катет равен: " + triangle1.getKatet2());
        System.out.println("Гипотенуза треугольника равна: " + triangle1.getGipotenuza());
        System.out.println("ИНФОРМАЦИЯ О ВТОРОМ ТРЕУГОЛЬНИКЕ");
        System.out.println("Первый катет равен : " + triangle2.getKatet1());
        System.out.println("Второй катет равен: " + triangle2.getKatet2());
        System.out.println("Гипотенуза треугольника равна: " + triangle2.getGipotenuza());
        System.out.println("Треугольники 1 и 2 " + triangle1.isTrianglesEquals(triangle2) + "равны");
    }
    static String input(){
        Scanner scanner = new Scanner(System.in);
        String sr = scanner.nextLine();
        return sr;
    }
}


