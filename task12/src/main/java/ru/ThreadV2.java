package ru;


    public class ThreadV2 extends java.lang.Thread {



        private float[] arr;


        ThreadV2(String name, float[] arr){
            super(name);
            this.arr = arr;
        }

        public void run() {
            final int HALF = 10000000 / 2;
            System.out.printf("Thread %s started working\n", getName());
            for (int i=0; i<arr.length; i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + (i + HALF) / 5) * Math.cos(0.2f + (i+HALF) / 5) *
                        Math.cos(0.4f + (i+HALF) / 2));
            }
            System.out.printf("Thread %s finished working\n", getName());
        }
    }



