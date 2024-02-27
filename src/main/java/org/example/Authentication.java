package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class Authentication {
    private static final String filePath = "C:\\Users\\hp\\Desktop\\Results\\IMS\\src\\main\\resources\\employees.json";

    public void createUser(String username, String password, String role) {
        JSONObject userDetails = new JSONObject();
        userDetails.put("username", username);
        userDetails.put("password", password);
        userDetails.put("role", role);

        // Try to load existing users
        JSONArray usersArray = new JSONArray();
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(filePath);
            Object obj = parser.parse(reader);
            usersArray = (JSONArray) obj;
            reader.close();
        } catch (IOException | ParseException e) {
            // File might not exist or is empty; ignore
        }

        // Add the new user
        usersArray.add(userDetails);

        // Write back to the file
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(usersArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String authenticateUser(String username, String password) {
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(filePath);
            Object obj = parser.parse(reader);
            JSONArray usersArray = (JSONArray) obj;
            for (Object userObj : usersArray) {
                JSONObject user = (JSONObject) userObj;
                String userUsername = (String) user.get("username");
                String userPassword = (String) user.get("password");
                String userRole = (String) user.get("role");
                if (userUsername.equals(username) && userPassword.equals(password)) {
                    return userRole; // Return user's role if username and password match
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return "unauthenticated"; // Return a string indicating authentication failure
    }
}
