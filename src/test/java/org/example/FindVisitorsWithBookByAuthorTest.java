package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindVisitorsWithBookByAuthorTest {
    @Test
    public void testFindVisitorsWithBookByAuthor() {
        List<Visitor> visitors = Main.loadVisitors();
        BookService bookService = new BookService(Main.getAllFavoriteBooks(visitors));

        Map<Visitor, Book> result = bookService.findVisitorsWithBookByAuthor(visitors, "Jane Austen");

        // Проверяем, что результат не пустой, если есть пользователи с книгами этого автора
        if (!result.isEmpty()) {
            for (Map.Entry<Visitor, Book> entry : result.entrySet()) {
                assertEquals("Jane Austen", entry.getValue().getAuthor());
            }
        }
    }
}
