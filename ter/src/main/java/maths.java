public class maths {
    public static void main(String[] args) {
        int x=3;
        int[]arr = {0,0,0,0,0,0,0,0,0,0};

        for (int i=0;i<40;i++) {
            x = (81 * x + 3) % 100;
            System.out.println(x);
            arr[x/10]++;

        }

        System.out.println("");
        for (int i=0;i<10;i++){
            System.out.println(arr[i]);
        }


        int X=0;
        for (int i=1;i<=10;i++){
           X+=(arr[i-1]-4)*(arr[i-1]-4);
        }
        System.out.println(X);
        System.out.println((double)X/4);
    }
}
