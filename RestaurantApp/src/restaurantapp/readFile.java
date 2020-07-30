package restaurantapp;

import java.io.*;
import java.util.*;

public class readFile {
    private Scanner i;
    public void openFile(String file) {
        try {
            i = new Scanner(new File(file));
        } catch(Exception e) {
            System.out.println("Could not find file.");
        }
    }
 public int readUsers(String user) {
        while(i.hasNext()) {
            String index = i.next();
            String users = i.next();
            if(users.equals(user)) return Integer.parseInt(index);
        }
        return -1;
    }
    public int findIndex() {
        String index = "0";
        while(i.hasNext()) {
            index = i.next();
            String users = i.next();
        }
        return  Integer.parseInt(index) + 1;
    }
   public boolean readPasswords(int pos, String pass) {
        while(i.hasNext()) {
            int index = Integer.parseInt(i.next());
            String password = i.next();
            if(index == pos && pass.equals(password)) return true;
        }
        return false;
    }

    public void closeFile() {
        i.close();
    }
}