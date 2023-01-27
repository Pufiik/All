package ru;

public class main_main {
    import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

    public class Homework {

        public static final String ANSI_RESET = "\u001B[0m";
        public static final String RED_BOLD_BRIGHT = "\033[1;91m";
        public static final String GREEN_BOLD_BRIGHT = "\033[1;92m";

        static final int SIZE = 10_000_000;
        static final int HALF = SIZE / 2;

        public static void main(String[] args) {

            new Homework().doHomework();

        }

        public void doHomework(){

            float[] arr1 = new float[SIZE];
            float[] arr2 = new float[HALF];

            float[] arr3 = new float[SIZE];
            float[] arr4 = new float[HALF];

            ArrayProcessing arrayProcessing = new ArrayProcessing();
            try
            {
                arrayProcessing.oneThreadProcessing(arr1);
                System.out.println("\n***\n");
                //arrayProcessing.oneThreadProcessing(arr2);

                System.out.println("\n*** 2 потока ***\n");
                arrayProcessing.multiThreadProcessing(arr3, 2);
                System.out.println("\n***\n");
                //arrayProcessing.multiThreadProcessing(arr4, 2);

                System.out.println("\n*** 4 потока ***\n");
                arrayProcessing.multiThreadProcessing(arr3, 4);
                System.out.println("\n***\n");
                //arrayProcessing.multiThreadProcessing(arr4, 4);

                System.out.println("\n*** 6 потоков ***\n");
                arrayProcessing.multiThreadProcessing(arr3, 6);
                System.out.println("\n***\n");
                //arrayProcessing.multiThreadProcessing(arr4, 6);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        /**
         * Вспомогательный поток для обработки массива
         */
        public class ArrayThread extends Thread {

            private final float[] arr;
            private final int startIndex;

            /**
             *
             * @param name - наименование потока
             * @param arr - массив
             * @param startIndex - смещение индекса общего массива (необходимо для корректного расчета формулы)
             */
            public ArrayThread(String name, float[] arr, int startIndex) {
                super(name);
                this.arr = arr;
                this.startIndex = startIndex;
            }

            @Override
            public void run() {
                System.out.printf("Поток %s запущен ... \n Индекс от: %d; Длинна массива: %d", Thread.currentThread().getName(), startIndex, arr.length);
                System.out.println("");
                for (int element = 0; element < this.arr.length; element++) {
                    this.arr[element] = (float) (this.arr[element] * Math.sin(0.2f + (element + startIndex) / 5) * Math.cos(0.2f + (element + startIndex) / 5) * Math.cos(0.4f + (element + startIndex) / 2));
                }
                System.out.printf("Поток %s завершил свою работу ... \n", Thread.currentThread().getName());
            }

        }

        public class ArrayThreadV2 implements Runnable {

            private final float[] arr;
            private final int startIndex;

            /**
             *
             * @param arr - массив
             * @param startIndex - смещение индекса общего массива (необходимо для корректного расчета формулы)
             */
            public ArrayThreadV2(float[] arr, int startIndex) {
                this.arr = arr;
                this.startIndex = startIndex;
            }

            @Override
            public void run() {
                System.out.printf("Поток %s запущен ... \n Индекс от: %d; Длинна массива: %d\n", Thread.currentThread().getName(), startIndex, arr.length);
                for (int element = 0; element < this.arr.length; element++) {
                    this.arr[element] = (float) (this.arr[element] * Math.sin(0.2f + (element + startIndex) / 5) * Math.cos(0.2f + (element + startIndex) / 5) * Math.cos(0.4f + (element + startIndex) / 2));
                }
                System.out.printf("Поток %s завершил свою работу ... \n", Thread.currentThread().getName());
            }

        }

        /**
         * Класс содержит вспомогательные методы для обработки массива
         */
        public class ArrayProcessing{

            /**
             * Обработка массива в один поток
             * @param arr - Массив
             * @throws InterruptedException
             */
            public void oneThreadProcessing(float[] arr) throws InterruptedException {
                System.out.println("Обработка массива в один поток ...");
                long startTime = System.currentTimeMillis();
                // Заполняем массив единицами
                Arrays.fill(arr, 1.0f);

                long time1 = (System.currentTimeMillis() - startTime);

                ArrayThread thread = new ArrayThread("thread #1", arr, 0);
                thread.start();
                thread.join();
                long time2 = (System.currentTimeMillis() - time1 - startTime);

                System.out.println(
                        "Операция выполнена за " + RED_BOLD_BRIGHT +  (System.currentTimeMillis() - startTime) + ANSI_RESET
                                + " мс\nЗаполнение единицами " + time1
                                + " мс\nОбработка формулой " + time2
                                + " мс");
            }

            /**
             * Обработка массива в несколько потоков
             * @param arr - массив
             * @param threads - кол-во потоков
             * @throws InterruptedException
             */
            public void multiThreadProcessing(float[] arr, int threads) throws InterruptedException {
                System.out.printf("Обработка массива в %d потока ...\n", threads);
                long startTime = System.currentTimeMillis();
                // Заполняем массив единицами
                Arrays.fill(arr, 1.0f);
                long timeToFill = (System.currentTimeMillis() - startTime);


                List<float[]> arrays = new ArrayList<>();
                int index = 0;
                int length = arr.length / threads;
                for (int i = 0; i < threads; i++) {
                    float[] temp = new float[length];
                    System.arraycopy(arr, index, temp, 0, length);
                    index += length;
                    arrays.add(temp);
                }


                List<Thread> threadsList = new ArrayList<>();
                long splitTime = (System.currentTimeMillis() - startTime - timeToFill);
                for (int i = 0; i < arrays.size(); i++) {
                    Thread t = new ArrayThread("thread #" + (i + 1),  arrays.get(i), 0);
                    t.start();
                    threadsList.add(t);
                }

                for (Thread t : threadsList) t.join();

                long processingTime = (System.currentTimeMillis() - startTime - timeToFill - splitTime);

                index = 0;
                for (int i = 0; i < arrays.size(); i++) {
                    System.arraycopy(arrays.get(i), 0, arr, index, length);
                    index += length;
                }

                long joinTime = (System.currentTimeMillis() - startTime - timeToFill - splitTime - processingTime);

                System.out.println(
                        "Операция выполнена за " + GREEN_BOLD_BRIGHT +  (System.currentTimeMillis() - startTime) + ANSI_RESET
                                + " мс\nРазбивка массива " + splitTime
                                + " мс\nОбработка формулой " + processingTime
                                + " мс\nСклейка массива " + joinTime
                                + " мс");
            }

        }

    }
}
