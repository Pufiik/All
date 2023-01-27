package ru.pugovishnikova;

import java.util.ArrayList;

public class Task11_2 {
    public static void main(String[] args) {
        Integer[] str = {1, 2, 3};
        change(str, 0, 2);
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
    }



    static <T> ArrayList<T> convert(T[] obj1) {
        ArrayList<T> arr = new ArrayList<>();
        for (int i=0; i<obj1.length; i++){
            arr.add(obj1[i]);
        }
        return arr;
    }


    static<T> void change(T[] obj1, int ind1, int ind2){
        try {
            T c;
            c=obj1[ind1];
            obj1[ind1] = obj1[ind2];
            obj1[ind2] = c;
        }catch(IndexOutOfBoundsException e){
            System.out.println("Not correct index");
        }
    }
}


