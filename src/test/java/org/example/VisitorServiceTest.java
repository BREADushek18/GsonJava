package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitorServiceTest {
    private VisitorService visitorService;

    @BeforeEach
    public void setUp() {
        visitorService = new VisitorService("books.json");
    }

    @Test
    public void testPrintVisitors() {
        // Будем проверять количество посетителей, так как это проще
        List<Visitor> visitors = visitorService.getVisitors();
        assertEquals(15, visitors.size());
    }
}
