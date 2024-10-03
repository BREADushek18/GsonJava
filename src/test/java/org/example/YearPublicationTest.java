package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YearPublicationTest {
    @Test
    public void testGetBooksSortedByYear() {
        List<Visitor> visitors = Main.loadVisitors();
        BookService bookService = new BookService(Main.getAllFavoriteBooks(visitors));
        List<Book> sortedBooks = bookService.getBooksSortedByYear();

        // Проверяем, что список отсортирован по году издания
        for (int i = 0; i < sortedBooks.size() - 1; i++) {
            assertTrue(sortedBooks.get(i).getPublishingYear() <= sortedBooks.get(i + 1).getPublishingYear());
        }
    }
}
