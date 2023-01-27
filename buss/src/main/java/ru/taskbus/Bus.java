package ru.taskbus;

public class Bus {
    int countPlace; // количество всех  мест
    float pricePlace; // цена за место
    int takenPlace = 0; // количество занятых мест

    //конструктор по умолчанию, ничего не принимает, количество занятых мест по умолчанию равно нулю
    public Bus(){};

    //конструктор с параметрами, количество занятых мест по умолчанию равно нулю,
    //устанавливается только цена и количество всех мест

    public Bus (int count, float price){
        countPlace = count;
        pricePlace = price;

    }

    //конструктор со всеми параметрами
    public  Bus (int count, float price, int taken){
        countPlace = count;
        pricePlace = price;
        takenPlace= taken;

    }


    //методы, изменяющие параметры

    public void changeCountPlace (int newcount){
        countPlace = newcount;
    }
    public void changePrice (int newprice){
        pricePlace = newprice;
    }
    public void changeTakenPlace (int newtaken){
        takenPlace = newtaken;
    }


    //методы, изменяющие параметры
    public int takeCountPlace (){
        return countPlace;
    }
    public float takePrice (){
        return pricePlace;
    }
    public int takeTakenPlace (){
        return takenPlace;
    }

    //метод, рассчитывающий количество свободных мест
    public int freePlace() {
        return (countPlace - takenPlace);
    }

    //метод, проверяющий пустой автобус или нет, вернет true если пустой И false, если заполнен

    public boolean freeBus(){
        return (takenPlace==0);
    }

    //метод, рассчитывающий полную стоимость занятых мест
    public float allPrice(){
        return(takenPlace*pricePlace);
    }
}


