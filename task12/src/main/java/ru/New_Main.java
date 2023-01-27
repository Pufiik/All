package ru;

public class New_Main {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) {
        System.out.println("Thread main started working");
        float[] arr = new float[SIZE];//массив элементов
        float[] arrShortS = new float[HALF];// массив, отвечающий за первую пололвину
        float[] arrShortE = new float[HALF];// массив, отвечающий за ваторую пололвину


        for (int i=0; i<arr.length; i++){
            arr[i]=1.0f;
        }


        long a = System.currentTimeMillis();//запуск счетчика времени
        System.arraycopy(arr,0,arrShortS,0, HALF);//копирование в превый массив
        System.arraycopy(arr,0,arrShortE,0, HALF);//копировани во второй массив


        Thread myThread1 = new ThreadV1("Thread_1", arrShortS);
        myThread1.start();//запуск первого потока

        Thread myThread2 = new ThreadV2("Thread_2", arrShortE);
        myThread2.start();//заупск второго потока
        try {//ожидание главным потоком завершения работы обоих вспомомогатлеьных
           myThread1.join();
           myThread2.join();
        }
        catch (InterruptedException e){
            e.getStackTrace();
        }

        System.arraycopy(arrShortS, 0, arr, 0, HALF);
        System.arraycopy(arrShortE, 0, arr, HALF, HALF);
        System.out.println("Working Time = " + (System.currentTimeMillis() - a));
        System.out.println("Thread main finished working ");
    }
}
