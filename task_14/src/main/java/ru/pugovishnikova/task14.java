package ru.pugovishnikova;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class task14 {
    public static void main(String[] args) {
        int[] arr1 = {1,1,4,4,2,4,6,7,8,9};
        int[] arr2 = {1,1,4,4,2,4,6,7,4,9};
        int[] arr3 = {1,1,4,4,2,4,6,7,4,4};
        int[] arr4 = {1,1,2,5,6,7,9};
        int[] arr5 = {1,1,1,1,1,1};
        int[] arr6 = {4,4,4,4,4,4};
        int[] arr7 = {1,4,1,4,1,4};

        try {
            ArrayList<Integer> newArr1 = elementCatch(arr1);
            print(newArr1);
        } catch (RuntimeException e1){
            e1.printStackTrace();
        }
        catch (InterruptedException i1){
            i1.printStackTrace();
        }


        System.out.println("");
        try {
            ArrayList<Integer> newArr3 = elementCatch(arr3);
            print(newArr3);
        } catch (RuntimeException e2){
            e2.printStackTrace();
        }
        catch (InterruptedException i2){
            i2.printStackTrace();
        }

        System.out.println("");
        try {
            ArrayList<Integer> newArr2 = elementCatch(arr2);
            print(newArr2);
        } catch (RuntimeException e3){
            e3.printStackTrace();
        }
        catch (InterruptedException i3){
            i3.printStackTrace();
        }

        System.out.println("");
        try {
            ArrayList<Integer> newArr4 = elementCatch(arr4);
            print(newArr4);
        } catch (RuntimeException e4){
            e4.printStackTrace();
        }
        catch (InterruptedException i4){
            i4.printStackTrace();
        }
        System.out.println(check(arr1));
        System.out.println(check(arr5));
        System.out.println(check(arr6));
        System.out.println(check(arr7));
    }


    public static ArrayList<Integer> elementCatch (int[] arr) throws RuntimeException, InterruptedException{
        boolean flag = false;
        int ind=0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == 4) {
                flag = true;
                ind=i;
            }
        }

        if (!flag) throw new RuntimeException("There isn't any numbers equals 4");
        ArrayList<Integer> newArr = new ArrayList<>();
        if (ind==arr.length-1){
            System.out.println("no elements");
            throw new IndexOutOfBoundsException("There isn't any elements after last 4");
            }
        for (int j=ind+1; j<arr.length;j++)
            newArr.add(arr[j]);

          return newArr;
    }


    public static boolean check(int[] arr) {
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = true;

        for (int i=0; i<arr.length; i++){
            if (arr[i]==1) flag1=true;
            if (arr[i]==4)  flag2=true;
            if (arr[i]!=1 && arr[i]!=4) flag3 = false;
        }
        return flag1&&flag2&&flag3;
    }
    public static void print(ArrayList<Integer> arr){
        for (int i=0; i<arr.size();i++)
            System.out.println(arr.get(i));
    }
}
