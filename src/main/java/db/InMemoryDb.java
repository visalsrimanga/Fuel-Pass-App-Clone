package db;

import javafx.scene.control.Button;

import java.util.ArrayList;

public class InMemoryDb {

    private static ArrayList<User> userDatabase=new ArrayList<>();
    static {
        registerUser(new User("123456789V", "Visal","Srimanga", "Matara", 20, new Button("Remove")));
        registerUser(new User("456789123V", "Saman","Silva", "Galle", 20, new Button("Remove")));
        registerUser(new User("789123456V", "Nimal","Perera", "Pandura", 20, new Button("Remove")));
    }

    public static ArrayList<User> getUserDatabase() {
        return userDatabase;
    }

    public static boolean isRegisteredUser(String nic){
        for (User user:userDatabase){
            if (user.getNic().equalsIgnoreCase(nic)) return true;
        }
        return false;
    }
    public static User findUser(String nic){
        for (User user:userDatabase){
            if (user.getNic().equalsIgnoreCase(nic)) {
                return user;
            }
        }
        return null;
    }

    public static void registerUser(User user){
        userDatabase.add(user);
    }
    public static void removeUser(String nic){
        for (User user:userDatabase) {
            if (user.getNic().equals(nic)){
                userDatabase.remove(user);
                return;
            }
        }
    }
    public static void removeUserByObject(User user){
        userDatabase.remove(user);
    }
}
