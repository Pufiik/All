package ru;


public class task12 {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) {
        System.out.println("Thread main started working");
        fun();
        System.out.println("Thread main finished working");
    }

    static void fun(){

        float[] arr = new float[SIZE];
        for (int i=0; i<arr.length; i++){
            arr[i]=1.0f;
        }
        long a = System.currentTimeMillis();
        for (int i=0; i<arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
        System.out.printf("Working time = %d\n",System.currentTimeMillis() - a);
    }
}
