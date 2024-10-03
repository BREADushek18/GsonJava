package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmsMessageTest {
    @Test
    public void testGenerateSmsMessagesWithEqualAverage() {
        Visitor visitor1 = new Visitor();
        visitor1.setPhone("1234567890");
        visitor1.setFavoriteBooks(List.of(new Book()));

        Visitor visitor2 = new Visitor();
        visitor2.setPhone("0987654321");
        visitor2.setFavoriteBooks(Arrays.asList(new Book(), new Book(), new Book(), new Book()));

        Visitor visitor3 = new Visitor();
        visitor3.setPhone("5555555555");
        visitor3.setFavoriteBooks(Arrays.asList(new Book(), new Book()));

        List<Visitor> visitors = Arrays.asList(visitor1, visitor2, visitor3);
        BookService bookService = new BookService(Collections.emptyList());

        List<SmsMessage> smsMessages = bookService.generateSmsMessages(visitors);

        // Проверяем сообщения
        assertEquals(3, smsMessages.size());
        assertEquals("read more", smsMessages.get(0).getMessage());
        assertEquals("you are a bookworm", smsMessages.get(1).getMessage());
        assertEquals("fine", smsMessages.get(2).getMessage());
    }
}