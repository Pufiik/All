package ru.taskbus;
import java.util.Scanner;

public class Main {
    public static void main(String[] argc) {
        Scanner in = new Scanner(System.in);
        //считать с консоли количество людей в двух группах

        System.out.println("Input person in group 1:");
        String string1=in.nextLine();
        int a1= checkInt(string1);//люди первой группы
        System.out.println("Input person in group 2:");
        String string2=in.nextLine();
        int a2= checkInt(string2);//люди второй группы

        int group=0;//общее количество людей

        if (a1<0 && a2<0)
            System.exit(-1);
        else group=a1+a2;

        int pers1=0;//будущее количество людей для первого автобуса для рентабельного распределения
        int pers2=0;//будущее количество людей для второго автобуса для рентабельного распределения


        //Bus bus = new Bus(); //вспомогательный объект
        System.out.println("Input countPlace for bus 1:");
        String str1 = in.nextLine();
        System.out.println("Input pricePlace for bus 1:");
        String str2 = in.nextLine();
        System.out.println("Input countPlace for bus 2:");
        String str3 = in.nextLine();
        System.out.println("Input pricePlace for bus 2:");
        String str4 = in.nextLine();

        int count1 = checkInt(str1);
        float price1 = checkFloat(str2);
        int count2 = checkInt(str3);
        float price2 = checkFloat(str4);

       if (count1>0 && count2 >0 && price1>0 &&price2>0) {
           Bus bus1 = new Bus(count1, price1);
           Bus bus2 = new Bus(count2, price2);

          if (group<=(bus1.countPlace+bus2.countPlace)) {
              while (group > 0) {
                  if (bus1.allPrice() <= 11000f || bus1.countPlace > pers1) {
                      pers1++;
                      group--;
                      bus1.changeTakenPlace(pers1);
                  }
                  if (bus2.allPrice() <= 11000f || bus2.countPlace > pers2 && group > 0) {
                      pers2++;
                      group--;
                      bus2.changeTakenPlace(pers2);
                  }
              }

               System.out.printf("%d, %d", bus1.takeTakenPlace(), bus2.takeTakenPlace());
           }
          else {
              System.out.println("No seats left for all people");
              System.exit(-3);
          }
       }
       else {
           System.out.println("Error Input data");
           System.exit(-2);
       }
    }

public static int checkInt(String string){
    try {
        return Integer.parseInt(string);
    } catch (NumberFormatException e) {
        System.out.println("Error Input value");
        return -1;
    }
}

    public static float checkFloat(String string){
        try {
            return Float.parseFloat(string);
        } catch (NumberFormatException e) {
            System.out.println("Error Input value");
            return -1.0f;
        }
    }

//распределение маленьких групп людей в первый автобус
// n количество введенных групп, count количество мест в автобусе
    public static void groups(Scanner scanner, int n, int count){
        int[] Array;
        Array = new int[n];
        for(int i=0;i<n;i++){
            System.out.printf("Input %d group", i+1);
            String str = scanner.next();
            int a=checkInt(str);
            if (a>0)
                Array[i]=a;
            else System.exit(-1);
        }

        int k=0;
        int j=0;

        while(count>0){
            count-=Array[j++];
        }

        if(count==0) System.out.printf("%d groups will fit in a bus", j);
        else System.out.printf("%d groups will fit in a bus", --j);
    }
}
