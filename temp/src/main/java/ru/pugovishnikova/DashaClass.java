package ru.pugovishnikova;

import java.math.*;
import java.util.Scanner;
public class DashaClass {
    private float katet1;
    private float katet2;
    public DashaClass(String str) {
        if (isRightInput(str)) {
            this.katet1 = Float.parseFloat(str.split(" ")[0]);
            this.katet2 = Float.parseFloat(str.split(" ")[1]);
        }
        else setKatets();
    }
    public float getKatet1(){return katet1;}
    public float getKatet2(){return katet2;}
    public void setKatets() {
        System.out.print("Некорректный ввод. Введите заново длины катетов через пробел: ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        if (isRightInput(str)) {
            this.katet1 = Float.parseFloat(str.split(" ")[0]);
            this.katet2 = Float.parseFloat(str.split(" ")[1]);
        }
        else setKatets();
    }
    public boolean isRightInput(String str) {
        String[] str1 = str.split(" ");
        if (str1.length != 2 ) {
            return false; //Если нет пробелов, прошу ввести заново всю строку
        }
        else {
            try  {
                if (Float.parseFloat(str1[0]) <= 0 || Float.parseFloat(str1[1]) <= 0 ) {
                    throw new NumberFormatException();
                }
                return true;
            }
            catch (NumberFormatException ex) {
                return false;
            }
        }
    }
    public float getGipotenuza() {
        return (float) Math.sqrt(this.katet1*this.katet1 + this.katet2*this.katet2); // находим гипотенузу прямоугольного треугольника
    }
    public float getSinus1() {
        return (katet1 / getGipotenuza());
    }
    public float getSinus2(){
        return (katet2 / getGipotenuza());
    }
    public float getCosinus1() {
        return (float) (Math.sqrt(1 - getSinus1() * getSinus1()));
    }
    public float getCosinus2() {
        return (float) (Math.sqrt(1 - getSinus2() * getSinus2()));
    }
    public float getGradus1() {
        return (float) (Math.toDegrees(Math.asin(getSinus1())));
    }
    public float getGradus2() {
        return (float) (Math.toDegrees(Math.asin(getSinus2())));
    }
    public  float getRadiusVO() {
        return ((katet1 + katet2 - getGipotenuza()) / 2);
    }
    public float getRadiusOO() {
        return (float) (0.5 * getGipotenuza());
    }
    public float getTangens1() {
        return (getSinus1() / getCosinus1());
    }
    public float getTangens2() {
        return (getSinus2() / getCosinus2());
    }
    public float getCotangens1() {
        return (getCosinus1() / getSinus1());
    }
    public float getCotangens2() {
        return (getCosinus2() / getSinus2());
    }
    public String isTrianglesEquals(DashaClass other) {
        if (this.katet1 == other.katet1 && this.katet2 == other.katet2 || this.katet2 == other.katet1 && this.katet1 == other.katet2) return "";
        else return "не ";
    }
}


