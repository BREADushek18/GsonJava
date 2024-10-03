package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuantityOfBooksTest {
    @Test
    public void testGetUniqueFavoriteBooks() {
        List<Visitor> visitors = Main.loadVisitors();
        BookService bookService = new BookService(Main.getAllFavoriteBooks(visitors));
        Set<Book> uniqueFavoriteBooks = bookService.getUniqueFavoriteBooks(visitors);

        assertEquals(20, uniqueFavoriteBooks.size());
    }
}
