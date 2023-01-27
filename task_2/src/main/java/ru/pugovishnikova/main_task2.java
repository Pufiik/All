package ru.pugovishnikova;

import java.util.Scanner;

public class main_task2 {
    public static void main(String[] argc){

        int[] arr ={1, 1, 0, 0, 1, 0, 1, 1, 0, 0};//массив для первого задания
        int[] arr2 = new int[8];//массив для второго задания
        int[] arr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};//массив для третьего задания
        Scanner scanner = new Scanner(System.in);//создания объекта для задания размерности новых массивов
        System.out.println("Input length_matrix:");
        int length_matrix=scanner.nextInt();//задание размерности матрицы
        System.out.println("Input length_array:");
        int length_array=scanner.nextInt();//задание размерности массива для пятого задания
        int[][] matrix = new int[length_matrix][length_matrix];//создание матрицы заданной размерности
        int[] arr5 = new int[length_array];//массив для пятого задания
        //ввод элементов массива для пятого задания, так как он произвольный
        for (int i=0;i<length_array; i++){
            System.out.printf("Input element number %d:\n",i+1);
            arr5[i]=scanner.nextInt();
        }

        //задание 1
        System.out.println("Task 1:");
        System.out.println("old arr: ");
        print_arr(arr);
        task_1(arr,arr.length);
        System.out.println("new arr: ");
        print_arr(arr);

        //задание 2
        System.out.println("Task 2:");
        task_2(arr2,arr2.length);
        print_arr(arr2);

        //задание 3
        System.out.println("Task 3:");
        System.out.println("old arr: ");
        print_arr(arr3);
        task_3(arr3,arr3.length);
        System.out.println("new arr: ");
        print_arr(arr3);

        //задание 4
        System.out.println("Task 4:");
        task_4(matrix,length_matrix);
        for (int i=0;i<length_matrix;i++) {
            for (int j = 0; j < length_matrix; j++) {
                System.out.printf("%d ",matrix[i][j]);

            }
            System.out.printf("%s",'\n');
        }

        System.out.println("Task 5:");
        print_arr(arr5);
        int min=task_5_MIN(arr5,arr5.length);//поиск минимума массива 5
        int max=task_5_MAX(arr5,arr5.length);//поиск максимума массива 5
        System.out.printf("min = %d\n",min);//ввывод минимума массива 5
        System.out.printf("max = %d\n",max);//ввывод максимума массива 5

        System.out.println("Task 6:");
        System.out.println("The array is:");
        print_arr(arr5);
        System.out.printf("The result of arr5 is: ");
        boolean flag = task_6(arr5,arr5.length);//проверка на сумму элементов массива
        System.out.println(flag);//вывод результата

        System.out.println("Task 7:");
        System.out.println("turn right: ");
        task_7(arr5, 1);//сдвиг вправо
        print_arr(arr5);
        System.out.println("turn left: ");
        task_7(arr5,  -4);//сдвиг влево
        print_arr(arr5);

    }

     //метод для задания  1
     static int[] task_1(int[] arr, int n){
     for(int i=0;i<n;i++){
         arr[i]=(arr[i]==0)?1:0;
     }
     return arr;
    }

    //метод для задания  2
    static int[] task_2(int[] arr, int n){
        int k=0;
        for (int i=0;i<n;i++){
            arr[i]=k;
            k+=3;
        }
        return arr;
    }

    //метод для задания  3
    static int[] task_3(int[] arr, int n){
        for(int i=0;i<n;i++){
            if (arr[i]<6) arr[i]*=2;
        }
        return arr;
    }

    //метод для задания  4
    static int[][] task_4(int[][] mat, int n){

        for (int i=0;i<n;i++) {
            for (int j = 0; j <n; j++) {
                mat[i][j] = (i == j)||(j+i==n-1) ? 1 : 0;
            }
        }
        return mat;
    }

    //методы для задания  5
    static int task_5_MIN(int[] arr, int n){
        int min=arr[0];
        for (int i=1;i<n;i++){
            if (arr[i]<min) min=arr[i];
        }
        return min;
    }

    static int task_5_MAX(int[] arr, int n){
        int max=arr[0];
        for (int i=1;i<n;i++){
            if (arr[i]>max) max=arr[i];
        }
        return max;
    }

    //метод для задания  6
    static  boolean task_6(int[]  arr, int n){
        boolean flag = false;
        for (int i=0;i<n-1;i++){
            int sum1=0;
            int sum2=0;
            for(int k=0;k<=i;k++) {
                sum1+=arr[k];
            }
            for (int j=n-1;j>i;j--){
                sum2+=arr[j];
            }
            if (sum1==sum2){
                flag=true;
                break;
            }
        }
        return flag;
    }

    //методы для задания 7
    static int[] task_7(int[] arr, int n){
        if(n>0) turn_RIGTH(arr,n);
        else turn_LEFT(arr,-n);
        return arr;
    }

    static void turn_LEFT(int[] arr, int n){
        int[] temp = new int[n];
        int k=0;
        for (int i = 0; i < n; i++){
            temp[k++]=arr[i];
        }
        for (int i=n; i< arr.length; i++){
            arr[i-n]=arr[i];
        }
        for (int i = arr.length-n; i< arr.length; i++){
            arr[i]=temp[i-(arr.length-n)];
        }

    }

    static void turn_RIGTH(int[] arr, int n){
        int[] temp = new int[n];
        int k=0;
        for (int i = arr.length-n; i < arr.length; i++){
            temp[k++]=arr[i];
        }
        for (int i= arr.length-n-1; i>=0; i--){
            arr[i+n]=arr[i];
        }
        for (int i = 0; i<n; i++){
            arr[i]=temp[i];
        }
    }


    //метод вывода массива
    static void print_arr(int[] arr){
        for (int i=0; i<arr.length; i++){
            System.out.printf("%d ",arr[i]);
        }
        System.out.printf("\n");
    }
    
}



