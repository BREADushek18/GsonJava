package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VisitorService visitorService = new VisitorService("books.json");
        List<Visitor> visitors = loadVisitors(); // Загружаем посетителей из файла
        BookService bookService = new BookService(getAllFavoriteBooks(visitors));

        System.out.println("Введите номер задания (1-6):");
        int taskNumber = scanner.nextInt();

        switch (taskNumber) {
            case 1:
                visitorService.printVisitors();
                break;
            case 2:
                Set<Book> uniqueFavoriteBooks = bookService.getUniqueFavoriteBooks(visitors);
                System.out.println("Уникальные книги в избранном:");
                uniqueFavoriteBooks.forEach(book -> System.out.println(book.getName()));
                System.out.println("Количество уникальных книг: " + uniqueFavoriteBooks.size());
                break;
            case 3:
                List<Book> sortedBooks = bookService.getBooksSortedByYear();
                System.out.println("Книги, отсортированные по году издания:");
                sortedBooks.forEach(book -> System.out.println(book.getName() + ", Год издания: " + book.getPublishingYear()));
                break;
            default:
                System.out.println("Неверный номер задания.");
        }
    }

    static List<Visitor> loadVisitors() {
        List<Visitor> visitors = new ArrayList<>();
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("books.json")) {
            Type visitorListType = new TypeToken<List<Visitor>>() {}.getType();
            visitors = gson.fromJson(reader, visitorListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return visitors; // Возвращаем загруженных посетителей
    }

    static List<Book> getAllFavoriteBooks(List<Visitor> visitors) {
        List<Book> allFavoriteBooks = new ArrayList<>();
        for (Visitor visitor : visitors) {
            allFavoriteBooks.addAll(visitor.getFavoriteBooks());
        }
        return allFavoriteBooks; // Возвращаем все любимые книги
    }
}