package ru.pugovishnikova;


import java.util.Scanner;

public class task5 {


    public static void main(String[] argc) {
        Scanner scanner = new Scanner(System.in);//переменная для считывания данных из консоли
        String str="";//переменная, куда считается количество объектов типа сотрудник, строкового типа данных для отлова ошибок ввода
        int n=1;//реальное значение количества переменных

        //цикл ввода количетсва, выполняется, пока не будет введено приемлимое значение больше 0 и типа int
        while (true){
            System.out.println("Input the count of employees: ");
             str = scanner.nextLine();
             n = checkInt(str);//вызов проверки данных на тип int
             if (n>0) break;// проверка на положительность
            System.out.println("Invalid count, input one more time");
            
            }
        Employee[] employees = new Employee[n]; //создание массива типа сотрудник
        set(employees,scanner);//заполнение
        print_array(employees);//вывод
    }


    //метод, отвечающий за заполнение массива данными
    public static void set(Employee[] employeees, Scanner scan){
        String str_surname="";
        String str_name="";
        String str_patronymic="";
        String str_job="";
        String str_email="";
        String str_phone="";
        String str_salary = "";
        String str_age ="" ;


        for (int i = 0; i<employeees.length; i++){
            System.out.printf("Input info about employee number %d\n\n", i+1);
            System.out.println("Input surname:");
            str_surname=scan.nextLine();
            System.out.println("Input name:");
            str_name=scan.nextLine();
            System.out.println("Input patronymic:");
            str_patronymic=scan.nextLine();
            System.out.println("Input job:");
            str_job=scan.nextLine();
            System.out.println("Input email:");
            str_email=scan.nextLine();
            System.out.println("Input phone_number:");
            str_phone=scan.nextLine();
            System.out.println("Input salary:");
            str_salary=scan.nextLine();
            System.out.println("Input age:");
            str_age=scan.nextLine();

            employeees[i]=new Employee(str_surname,str_name,str_patronymic,str_job,str_email,str_phone,str_salary,str_age);
        }

    }


    //метод вывода нужных элементов массива
    public static void print_array(Employee[] employeees){
        System.out.printf("We are suitable for the following people:\n\n");
        for (int i = 0; i<employeees.length; i++){
            if(employeees[i].getAge() > 40)  employeees[i].print();
        }

    }


    //отлов ошибок ввода
    public static int checkInt(String string){
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            System.out.println("Error Input value: you input no value");
            return -1;
        }
    }
}