

import java.util.Random;
import java.util.Scanner;

    public class a {
        /* Задание № 1 Написать программу, которая загадывает случайное число от 0 до 9
        и пользователю дается 3попытки угадать это число.
        При каждой попытке компьютер должен сообщить, больше лиуказанное пользователем число, чем загаданное, или меньше.
        После победы или проигрышавыводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).*/
        public static void main (String...args){

            Scanner input = new Scanner(System.in);
            Random numbers = new Random();
            int try3 =0;
            int random = numbers.nextInt(9);
            System.out.print("Угадай число с трех раз,");

            while (try3 < 3) {
                System.out.print("введи от 0 до 9: ");
                int number = input.nextInt();

                if (number > random) {
                    System.out.println("Слишком много! давай поменьше:");
                    System.out.println("");
                } else if (number < random) {
                    System.out.println("Маловато, давай побольше:");
                    System.out.println("");
                } else if (number == random) {
                    System.out.println("Ты отгадал!");
                    System.out.println("");
                }
                try3++;
            }
            System.out.print("К сожалению, ваши попытки закончились. Начать игру заново? Да? жми 1; Нет? нажми 0:");

            String userData = input.next();
            if(userData.equals("1")){
                main(null);

            /* Задание № 2 Создать массив из слов String[] words = {""apple"", ""orange"", ""lemon"", ""banana"",
            ""apricot"", ""avocado"", ""broccoli"", ""carrot"",
            ""cherry"", ""garlic"", ""grape"", ""melon"", ""leak"", ""kiwi"", ""mango"", ""mushroom"", ""nut"", ""olive"", ""pea"",
            "peanut"", ""pear"", ""pepper"", ""pineapple"", ""pumpkin"", ""potato""}.
            При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
            сравнивает его с загаданным словом и сообщает, правильно ли ответил пользователь. Если
            слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
            apple – загаданное
            apricot - ответ игрока
            ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
            Для сравнения двух слов посимвольно можно пользоваться:
            String str = ""apple"";
            char a = str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
            Играем до тех пор, пока игрок не отгадает слово.
            Используем только маленькие буквы*/


                String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
                        "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
                        "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

                String guess = null;
                String word = words[random.nextInt(words.length)];
                System.out.println(Arrays.toString(words));
                do {
                    System.out.print("Guess the word: ");
                    guess = sc.next();
                    for (int i = 0; i < 15; i++)
                        if (i < word.length() && i < guess.length() &&
                                word.charAt(i) == guess.charAt(i))
                            System.out.print(word.charAt(i));
                        else
                            System.out.print((word.equals(guess))? "" : "#");
                    System.out.println();
                } while (!word.equals(guess));
            }

            /**
             * reading text file to array using Scanner
             *
             * @param   file     object File for reading
             * @return  String[] array from file
             */
            static String[] readFromFile(File file) {
                String str = "";
                try (Scanner read = new Scanner(file)) {
                    while (read.hasNext())
                        str += read.nextLine() + "\n";
                } catch (IOException ex) {}
                return str.split("\n");
            }
        }
    }



}
