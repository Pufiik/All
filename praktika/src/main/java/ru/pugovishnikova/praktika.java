package ru.pugovishnikova;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.*;

public class praktika {

    public static float LEFTTRANS = 0.0f;
    public static float RIGHTTRANS = 26.0f;
    public static float LEFTOP1 = 15.0f;
    public static float RIGHTOP1 = 37.0f;
    public static float LEFTOP2 = 20.0f;
    public static float RIGHTOP2 = 32.0f;


    public static void main(String[] args) throws IOException {

        File logFile = new File("logFile.txt");
        Files.deleteIfExists(logFile.toPath());
        PrintWriter pw = new PrintWriter(logFile);

        Operator[] operators = {new Operator(LEFTOP1, RIGHTOP1, 1, pw), new Operator(LEFTOP2, RIGHTOP2, 2, pw)};
        Model newModel = new Model(operators, pw);


        while (newModel.getCurrentTime() < 3600.0f) {
            newModel.process();
        }

        newModel.operators[0].clear();
        newModel.operators[1].clear();
        newModel.operators[0]=null;
        newModel.operators[1]=null;
        pw.close();

    }


    static class Queue {
        private LinkedList<Transaction> queue;

        public Queue() {
            this.queue = new LinkedList<>();
        }

        public void addToQ(Transaction transaction) {
            queue.addLast(transaction);
        }

        public void removeTrans() {
            queue.removeFirst();
        }

        public Transaction getTrans() {
            return queue.getFirst();
        }

        public int size() {
            return queue.size();
        }
    }

    static class Transaction {
        private float time;
        private int ID;
        public Transaction(float globalTime, int number) {
            this.time = globalTime;
            this.ID = number;
        }

        public int getID() {
            return ID;
        }

        public float getTime() {
            return time;
        }
    }

    static class Operator {
        private PrintWriter pw;
        private int ID;
        private float leftBoard;//левая граница интервала обслуживания
        private float rightBoard;//правая граница интервала обслуживания
        private float timeOut;//время выхода

        public float getTimeOut() {
            return timeOut;
        }

        private Queue QUEUE;
        private Transaction currentTransaction;

        public Operator(float leftBoard, float rightBoard, int ID, PrintWriter pw) {
            this.pw = pw;
            this.ID = ID;
            this.leftBoard = leftBoard;
            this.QUEUE = new Queue();
            this.rightBoard = rightBoard;
            this.timeOut = 0;
        }

        public boolean checkEmpty() {
            return currentTransaction == null;
        }

        public void operatorProcess(Transaction transaction) {
            if (QUEUE.size() == 0) {
                if (timeOut < transaction.getTime()) {
                    if (currentTransaction != null) {
                        pw.printf("In %fs transaction ID#%d released the device number %d\n", timeOut, currentTransaction.getID(), ID);
                        pw.printf("In %fs transaction ID#%d released the model\n", timeOut, currentTransaction.ID);
                    }
                    currentTransaction = transaction;
                    pw.printf("In %fs transaction ID#%d occupied the device number %d\n", currentTransaction.getTime(), currentTransaction.getID(), ID);
                    timeOut = currentTransaction.getTime() + generate(leftBoard, rightBoard);
                } else {
                    QUEUE.addToQ(transaction);
                    pw.printf("In %fs transaction ID#%d entered the queue number %d\n", transaction.getTime(), transaction.getID(), ID);
                }
            } else {
                if (timeOut < transaction.getTime()) {
                    pw.printf("In %fs transaction ID#%d released the device number %d\n", timeOut, currentTransaction.getID(), ID);
                    pw.printf("In %fs transaction ID#%d released the model\n", timeOut, currentTransaction.getID());
                    currentTransaction = QUEUE.getTrans();
                    pw.printf("In %fs transaction ID#%d occupied the device number %d\n", timeOut, currentTransaction.getID(), ID);
                    QUEUE.removeTrans();
                    QUEUE.addToQ(transaction);
                    pw.printf("In %fs transaction ID#%d entered the queue number %d\n", timeOut, transaction.getID(), ID);
                    timeOut = currentTransaction.getTime() + generate(leftBoard, rightBoard);
                }
                QUEUE.addToQ(transaction);
                pw.printf("In %fs transaction ID#%d entered the queue number %d\n", transaction.getTime(), transaction.getID(), ID);
            }
        }

        public boolean analyze(Transaction transaction) {
            if (QUEUE.size() == 0) {
                if (timeOut < transaction.getTime()) {
                    if (currentTransaction != null) {
                        pw.printf("In %fs transaction ID#%d released the device number %d\n", timeOut, currentTransaction.getID(), ID);
                        pw.printf("In %fs transaction ID#%d released the model\n", timeOut, currentTransaction.getID());
                    }
                    currentTransaction = null;
                }
            } else {
                if (timeOut < transaction.getTime()) {
                    pw.printf("In %fs transaction ID#%d released the device number %d\n", timeOut, currentTransaction.getID(), ID);
                    pw.printf("In %fs transaction ID#%d released the model\n", timeOut, currentTransaction.getID());
                    currentTransaction = QUEUE.getTrans();
                    pw.printf("In %fs transaction ID#%d occupied the device number %d\n", timeOut, currentTransaction.getID(), ID);

                    timeOut = timeOut + generate(leftBoard, rightBoard);
                    QUEUE.removeTrans();
                    if (timeOut < transaction.getTime()) {
                        return true;
                    }
                }
            }
            return false;
        }
        public void clear(){
           QUEUE = null;
        }
    }

    static class Model {
        private PrintWriter pw;
        private int transactionCount;
        private Operator[] operators;
        private Transaction transaction;
        private float currentTime;

        public Model(Operator[] operators, PrintWriter pw) {
            this.pw = pw;
            this.transaction = new Transaction(generate(LEFTTRANS, RIGHTTRANS), 1);
            this.operators = operators;
            this.transactionCount = 1;
        }


        public float getCurrentTime() {
            return currentTime;
        }

        public Operator moveTo() {

            if (operators[0].checkEmpty() && !operators[1].checkEmpty()) return operators[0];
            if (operators[1].checkEmpty() && !operators[0].checkEmpty()) return operators[1];
            if (!operators[0].checkEmpty() && !operators[1].checkEmpty())
                return (operators[0].QUEUE.size() <= operators[1].QUEUE.size()) ? operators[0] : operators[1];
            return operators[0];
        }


        public boolean prog() {
            if (operators[0].getTimeOut() < operators[1].getTimeOut()) {
                return operators[0].analyze(transaction) || operators[1].analyze(transaction);
            } else {
                return operators[1].analyze(transaction) || operators[0].analyze(transaction);
            }
        }

        public void process() {
            currentTime = transaction.getTime();
            while (prog());
            pw.printf("In %fs transaction ID#%d entered the model\n", currentTime, transaction.ID);
            moveTo().operatorProcess(transaction);

            transaction = new Transaction(currentTime + generate(LEFTTRANS, RIGHTTRANS), ++transactionCount);
        }

    }


    public static float generate(float leftBoard, float rightBoard) {
        Random random = new Random();
        return leftBoard + random.nextFloat() * (rightBoard - leftBoard);
    }


}

