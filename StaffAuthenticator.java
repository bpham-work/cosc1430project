import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StaffAuthenticator {
    private final String FILE_NAME = "staffUsernamePassword.txt";
    private static final StaffAuthenticator authenticatorSingleton = new StaffAuthenticator();

    private Map<String, String> usersAndPasswords = new HashMap<>();

    public static StaffAuthenticator getInstance() {
        return authenticatorSingleton;
    }

    private StaffAuthenticator() {
        loadUsers();
    }

    private void loadUsers() {
        try {
            File f = new File(FILE_NAME);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String line;
            while ((line = b.readLine()) != null) {
                String[] namePassSplit = line.split("\\s+");
                String user = namePassSplit[0].toLowerCase();
                String password = namePassSplit[1];
                usersAndPasswords.put(user, password);
            }
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean authenticate(String username, String enteredPassword) {
        String passwordInDb = usersAndPasswords.get(username);
        return passwordInDb != null && passwordInDb.equals(enteredPassword);
    }
}
