package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

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
            case 4:
                Map<Visitor, Book> visitorsWithJaneAusten = bookService.findVisitorsWithBookByAuthor(visitors, "Jane Austen");
                if (visitorsWithJaneAusten.isEmpty()) {
                    System.out.println("Нет посетителей с книгами автора 'Jane Austen'.");
                } else {
                    System.out.println("Посетители с книгами автора 'Jane Austen':");
                    visitorsWithJaneAusten.forEach((visitor, book) ->
                            System.out.println(visitor.getName() + ": " + book.getName()));
                }
                break;
            case 5:
                int maxFavoriteBooksCount = bookService.getMaxFavoriteBooksCount(visitors);
                System.out.println("Максимальное количество книг в избранном у посетителей: " + maxFavoriteBooksCount);
                break;
            case 6:
                List<SmsMessage> smsMessages = bookService.generateSmsMessages(visitors);
                System.out.println("SMS сообщения:");
                smsMessages.forEach(sms -> System.out.println("На номер " + sms.getPhoneNumber() + ": " + sms.getMessage()));
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