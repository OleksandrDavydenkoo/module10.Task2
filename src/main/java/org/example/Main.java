package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String inputFile = "file.txt";
        String outputFile = "user.json";
        List<User> userList = readUsersFromFile(inputFile);
        writeUsersToJsonFile(userList, outputFile);
    }

    private static void writeUsersToJsonFile(List<User> userList, String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(userList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static List<User> readUsersFromFile(String fileName) {
        List<User> userList = new ArrayList<>();
        File filename = new File(fileName);
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                userList.add(new User(name, age));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }
}