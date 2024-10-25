package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Getter
public class VisitorService {
    private List<Visitor> visitors;

    public VisitorService(String filePath) {
        loadVisitors(filePath);
    }

    private void loadVisitors(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            Type visitorListType = new TypeToken<List<Visitor>>() {}.getType();
            visitors = gson.fromJson(reader, visitorListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printVisitors() {
        System.out.println("Список посетителей:");
        visitors.forEach(System.out::println);
        System.out.println("Общее количество посетителей: " + visitors.size());
    }
}
