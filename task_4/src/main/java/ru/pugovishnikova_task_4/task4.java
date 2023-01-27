package ru.pugovishnikova_task_4;


import java.util.Random;
import java.util.Scanner;

public class task4 {

    public static int WIN; //переменная, обозначающая число фишек, необходимых для победы
    public static char[][] field; //переменная, хранящая данные об игровом поле
    public static char man_move_char = 'X'; //маркировка фишки пользователя
    public static char comp_move_char = 'O'; //маркировка фишки компьютера
    public static char empty = '.'; //маркировка пустой клетки
    public static int X; //количество строк в поле
    public static int Y; //количество столбцоы в поле
    public static Scanner scanner = new Scanner(System.in); //переменная для считывания данных
    public static Random random = new Random(); //переменная для генерации рандомных чисел


    //метод, задающий размеры игрового поля и инициализирующий его пустыми фишками
    public static void set_field(){

        System.out.print("Input the field size through a space >>> \n");
        X=scanner.nextInt();
        Y=scanner.nextInt();
        field = new char[X][Y];

        for (int i=0; i<X; i++){
            for (int j=0; j<Y; j++){
                field[i][j]=empty;
            }
        }
    }


    //метод печати текущего состояния игрового поля на экран
    public static void print_field(){
        System.out.print("   ");

        for (int i=0; i<Y*2+1; i++){
            System.out.print((i%2==0)?"|": i/2+1);
            System.out.print(" ");
        }
        System.out.print('\n');
        for (int k=0; k<=Y*5; k++) System.out.print('-');
        System.out.print('\n');


        for (int i=0; i<X; i++){
            System.out.print(i+1);
            System.out.print("  ");
            System.out.print('|');
            for (int j=0; j<Y; j++){
                System.out.print(" ");
                System.out.print(field[i][j]);
                System.out.print(" ");
                System.out.print('|');
            }
            System.out.print('\n');
            for (int k=0; k<=Y*5; k++) System.out.print('-');
            System.out.print('\n');
        }
        System.out.print('\n');
    };


    //метод, описывающий ход пользователя
    public static boolean man_move(){
        System.out.println("Input coordinates of your move trough the space >>>");
        int x;
        int y;
        do {
             x = scanner.nextInt()-1;
             y = scanner.nextInt()-1;
        } while (!check_point(x,y) || !check_cell(x,y)); // проверка, что клетка внутри поля и свободна
        field[x][y] = man_move_char;
        return check_WIN(x,y,man_move_char); //проверка, что текущий ход приносит победу
    }


    //метод, описывающий ход компьютера
    public static boolean comp_move(){

        int x=0;
        int y=0;
        boolean flag = false;

        //проверка, что на текущий момент есть клетка на поле, которая принесет пользователю победу
        // если она есть, то туда ходит компьютер

        for (int i=0;i<X;i++){
            for (int j=0;j<Y;j++){
                if (check_cell(i,j)) {
                    field[i][j] = man_move_char;
                    if (check_WIN(i, j, man_move_char)) {
                        field[i][j] = empty;
                        x = i;
                        y = j;
                        flag = true;
                        break;
                    }
                    field[i][j]=empty;
                }
            }
        }


        //если клетки нет, то компьютер ходит рандомно
        if (flag==false) {
            do {
                x = random.nextInt(X);
                y = random.nextInt(Y);

            } while (!check_cell(x, y));
        }

        field[x][y]=comp_move_char;
        System.out.printf("The computer coordinates are: %d %d\n\n",x+1,y+1);
        return check_WIN(x,y,comp_move_char);  //проверка, что текущий ход приносит победу
    }


    //проверка, что текущая клетка свободна
    public static boolean check_cell(int x, int y){
        return field[x][y]=='.';
    }

    //проверка на правильность введенных координат
    public static boolean check_point(int x, int y){
        return x<X && x>=0 && y<Y && y>=0;
    }


    //проверка на победу, вызывается от текущей клетки, проверяет все возможные комбинации соседних клеток
    public static boolean check_WIN(int x, int y, char ch){

        int tempX1 = X-1-x; // смещение от нижней границы
        int tempX2 = x; // смещение от верхней границы
        int tempY1 = Y-1-y; // смещение от правой границы
        int tempY2 = y; //смещение от левой границы
        boolean flag = false;

        //vertical

        while (tempX1>=0 && tempX2>=(WIN-1-tempX1)){
            flag = true;
            for (int i = 0; i <= (tempX1 >= WIN - 1 ? WIN - 1 : tempX1); i++){
                if (field[x + i][y] != ch) flag = false;
            }
            for (int j = 0; j <= (tempX1>= WIN -1 ? 0 :  (WIN - 1 - tempX1)); j++) {
                if (field[x - j][y] != ch) flag = false;
            }
            if (flag) return flag;
            tempX1--;

        }



        //horizontal

        while (tempY1>=0 && tempY2>=(WIN-1-tempY1)){
            flag = true;
            for (int i = 0; i <= (tempY1 >= WIN - 1 ? WIN - 1 : tempY1); i++){
                if (field[x][y + i] != ch) flag = false;
            }
            for (int j = 0; j <= (tempY1>= WIN -1 ? 0 :  (WIN - 1 - tempY1)); j++) {
                if (field[x][y - j] != ch) flag = false;
            }
            if (flag) return flag;
            tempY1--;

        }


//diagonal
        tempX1 = X-1-x;
        tempY1 = Y-1-y;



//diagonal left down right up

        int MIN_DOWN=Math.min(tempY2,tempX1);

        while (MIN_DOWN >=0 &&  tempX2>=(WIN-1-MIN_DOWN) && tempY1>=(WIN- 1 - MIN_DOWN) ) {
            flag = true;

            for (int i = 0; i <= (MIN_DOWN >= WIN - 1 ? WIN - 1 : MIN_DOWN); i++) {
                if (field[x + i][y - i] != ch) flag = false;
            }

            for (int j = 0; j <= (MIN_DOWN>= WIN -1 ? 0 :  (WIN - 1 - MIN_DOWN)); j++) {
                if (field[x - j][y + j] != ch) flag = false;
            }
            if (flag) return flag;
            MIN_DOWN--;
        }

//diagonal left up right down

        int MIN_UP=Math.min(tempY1,tempX1);

        while (MIN_UP >=0 &&  tempX2>=(WIN-1-MIN_UP) && tempY2>=(WIN- 1 - MIN_UP) ) {
            flag = true;
            for (int i = 0; i <= (MIN_UP >= WIN - 1 ? WIN - 1 : MIN_UP); i++) {
                if (field[x + i][y + i] != ch) flag = false;
            }

            for (int j = 0; j <= (MIN_UP>= WIN -1 ? 0 :  (WIN - 1 - MIN_UP)); j++) {
                if (field[x - j][y - j] != ch) flag = false;
            }
            if (flag) return flag;

            MIN_UP--;
        }

            return flag;
    }


    //проверка на ничью
    public static boolean check_draw(){
        for (int i=0; i<X; i++){
            for (int j=0; j<Y; j++){
                if (field[i][j]==empty) return false;
            }
        }
        return true;
    }


    public static void main(String[] argc){


      while (true) {
          set_field();
          System.out.println("The game is started, changing who will do the first move\n");

          System.out.println("Input the win-number:");

          //проверка на то, что с текущим числом победы вообзе можно выиграть
          while (true) {
              WIN = scanner.nextInt();
              if (WIN > X && WIN > Y) {
                  System.out.println("Incorrect win-number, input again:");
              } else break;
          }


          print_field();
          char name;

          //рандомно выбирается, кто первый ходит

          int RAND = random.nextInt(2);//0 - comp, 1 - man
          if (RAND == 0) {
              System.out.println("The first move do computer");
              while (true) {
                  if (comp_move()) {
                      print_field();
                      name = comp_move_char;
                      break;
                  } else if (check_draw()) {
                      print_field();
                      System.out.print('\n');
                      System.out.println("YOU HANE DRAW!");
                  } else print_field();

                  if (man_move()) {
                      print_field();
                      name = man_move_char;
                      break;
                  } else if (check_draw()) {
                      print_field();
                      System.out.print('\n');
                      System.out.println("YOU HANE DRAW!");
                  } else print_field();
              }
          } else {

              while (true) {
                  if (man_move()) {
                      print_field();
                      name = man_move_char;
                      System.out.printf("the winner is %s\n\n", (name==comp_move_char? "computer" : "man"));
                      break;
                  } else if (check_draw()) {
                      print_field();
                      System.out.println("YOU HANE DRAW!");
                      break;
                  } else print_field();


                  if (comp_move()) {
                      print_field();
                      name = comp_move_char;
                      System.out.printf("the winner is %s\n\n", (name==comp_move_char? "computer" : "man"));
                      break;
                  } else if (check_draw()) {
                      print_field();
                      System.out.print('\n');
                      System.out.println("YOU HANE DRAW!");
                      break;
                  } else print_field();
              }
          }


          System.out.println("Do you want to play one more time? If yes, input Y or y, if no, input something else next:");

          if (!scanner.next().equalsIgnoreCase("Y")) break;
      }
    }
}
