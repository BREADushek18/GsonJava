package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class BookService {
    private List<Book> books; // Список всех книг

    public BookService(List<Book> books) {
        this.books = books;
    }

    // Задание 2: Получить список уникальных книг в избранном
    public Set<Book> getUniqueFavoriteBooks(List<Visitor> visitors) {
        // Предположим, что у вас есть способ получить любимые книги из посетителей
        // Например, у каждого посетителя может быть список любимых книг
        return visitors.stream()
                .flatMap(visitor -> visitor.getFavoriteBooks().stream()) // Метод getFavoriteBooks должен быть добавлен в класс Visitor
                .collect(Collectors.toSet());
    }

    // Задание 3: Сортировать книги по году издания
    public List<Book> getBooksSortedByYear() {
        return books.stream()
                .sorted(Comparator.comparingInt(Book::getPublishingYear))
                .collect(Collectors.toList());
    }

    // Задание 4: Проверить, есть ли у кого-то в избранном книга автора "Jane Austen"
    public Map<Visitor, Book> findVisitorsWithBookByAuthor(List<Visitor> visitors, String author) {
        return visitors.stream()
                .flatMap(visitor -> visitor.getFavoriteBooks().stream() // Используем поле напрямую
                        .filter(book -> book.getAuthor().equalsIgnoreCase(author)
                        ).map(book -> Map.entry(visitor, book)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
