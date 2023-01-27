package ru.pugovishnikova;

import java.util.Scanner;

public class Task3 {

    public static void main (String[] argc){

        Scanner scanner = new Scanner(System.in);//  переменная класса Scanner
        String[] alph = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
                "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
                "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

        int index = Random_int();
        String comp_word = alph[index]; //рандомное слово из списка

        System.out.println("The words are:");
        print_words(alph);
        System.out.println("");
        System.out.println("Choose and input your word:");
        String man_word = scanner.nextLine(); //считывание слова

        //основное условие работы программы
       while (!compare(comp_word,man_word)){
           print_str(comp_word, man_word);
           System.out.println("Try one more time:");
           man_word = scanner.nextLine();
       }

        System.out.println("You are win");
        System.out.printf("The computer word is: %s \n",comp_word);


    }

    //функция, генеирующая числа от 0 до 25 включительно
    public static int Random_int()
    {
        return (int) (Math.random() * 26);
    }

    //функция вывода всех слов
    public static void print_words(String[] str){
        for (int i=0; i<str.length; i++){
            System.out.printf("   -");
            System.out.println(str[i]);
        }
    }

    //функция сравнения слов компьютера и пользователя
    public static boolean compare (String str1, String str2){
        boolean flag = true;
        for (int i = 0; i< str1.length(); i++){
            char a = str1.charAt(i);
            char b = str2.charAt(i);
            if(a!=b){
                flag = false;
                break;
            }
        }
        return flag;
    }

    //метод для вывода строки, собранной из букв слова от пользователя
    public static void print_str(String str1, String str2){
       char[] new_str = {'*','*','*','*','*','*','*','*','*','*','*','*','*','*','*'};
       for (int i=0; i<str1.length(); i++){
           for (int j=0; j<str2.length(); j++){
               char a=str1.charAt(i);
               char b=str2.charAt(j);
               if (a==b)
                   new_str[i] = b;
           }
       }

        System.out.println(new_str);
    }

}
