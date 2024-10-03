package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxBooksCountTest {
    @Test
    public void testGetMaxFavoriteBooksCount() {
        // Создаем тестовых посетителей
        Visitor visitor1 = new Visitor();
        visitor1.setFavoriteBooks(Arrays.asList(new Book(), new Book()));

        Visitor visitor2 = new Visitor();
        visitor2.setFavoriteBooks(Arrays.asList(new Book(), new Book(), new Book()));

        Visitor visitor3 = new Visitor();
        visitor3.setFavoriteBooks(Arrays.asList(new Book(), new Book(), new Book(), new Book()));

        List<Visitor> visitors = Arrays.asList(visitor1, visitor2, visitor3);
        BookService bookService = new BookService(Collections.emptyList()); // Передаем пустой список книг

        int maxCount = bookService.getMaxFavoriteBooksCount(visitors);

        assertEquals(4, maxCount);
    }
}
