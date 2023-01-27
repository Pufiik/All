package ru.pugovishnikova;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class task10 {
    public static void main(String[] args){
        System.out.println("Task1\n");
        String[] words = {
                    "jfekd",
                    "jfieoe",
                    "apple",
                    "apple",
                    "apple",
                    "apple",

        };

        HashMap<String, Integer> Words = new HashMap<>();
        for (String i: words) {
            if (Words.containsKey(i)) {
                int count = Words.get(i);
                Words.put(i,++count);
            }
            else{
                Words.put(i,1);
            }
        }

        for (Map.Entry<String, Integer> item : Words.entrySet()){
            System.out.printf("%s: %d\n",item.getKey(), item.getValue());
        }




        String[] persons = {
                "Ivanov 89109752525",
                "Pugovishnikova +79109792525",
                "Molotorenko 89157893245",
                "Petrov 230932932089382390",
                "Ivanov 89018181813",
                "Petrov 89018181815",
                "Petrov +79018181813",
                "Smirnov +8989809809890880",
                "Averkiev 89034567890",
                "Zabegaev +78905641234"
        };
        System.out.println("\nTask2\n");
        directory(persons);
    }


//    public static void sort(ArrayList<String> words){
//        int[] count = new int[];



    public static void directory(String[] persons) {
        Pattern pattern = Pattern.compile("^(\\+?7|8)[0-9]{10}$");//регулярное выражение для телефона РФ
        String phone ="";
        String name = "";


        HashMap<String,ArrayList<Person>> map = new HashMap<>();
        for (String s: persons){
            String[] parts = s.split(" ");
            name = parts[0];
            phone = parts[1];
            Matcher matcher = pattern.matcher(phone);
            phone = (matcher.matches())?parts[1]:"non-defined";

            if(map.containsKey(name)){
                ArrayList<Person> list = map.get(name);
                list.add(new Person(phone,name));

            }
            else{
                ArrayList<Person> list = new ArrayList<>();
                list.add(new Person(phone,name));
                map.put(parts[0], list);
            }
        }

        TreeMap<String, ArrayList<Person>> sortedmap = new TreeMap<>(map);
        for (Map.Entry<String, ArrayList<Person>> item : sortedmap.entrySet()){
            System.out.printf("%s: ",item.getKey());
            for (Person person: item.getValue())
            System.out.printf(" %s ",person.getNumber());
            System.out.println("");
        }

    }
}
