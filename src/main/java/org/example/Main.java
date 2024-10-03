package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VisitorService visitorService = new VisitorService("books.json");

        System.out.println("Введите номер задания (1-6):");
        int taskNumber = scanner.nextInt();

        switch (taskNumber) {
            case 1:
                visitorService.printVisitors();
                break;
            default:
                System.out.println("Неверный номер задания.");
        }
    }
}
