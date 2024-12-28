import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HospitalMgmt {

    // Hospital details constants
    private static final String HOSPITAL_NAME = "      TOP-NOTCH CARE HOSPITAL";
    private static final String EMAIL = "contact@topnotchcare.com";
    private static final String WEBSITE = "www.topnotchcare.com";
    private static final String CONTACT_NO = "+1-800-123-4567";
    private static final String ADDRESS = "123 Health Ave, Wellness City, Medistan";
    private static final String FAX = "+1-800-765-4321";

    // User credentials and roles
    private static final Map<String, String> userCredentials = new HashMap<>();
    private static final Map<String, String> userRoles = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    // Initialize admin user and basic users
    private static void initializeUsers() {
        userCredentials.put("admin01", "admin123");
        userRoles.put("admin01", "Admin");

        // Basic predefined users
        userCredentials.put("doctor01", "doctor123");
        userRoles.put("doctor01", "Doctor");

        userCredentials.put("nurse01", "nurse123");
        userRoles.put("nurse01", "Nurse");

        userCredentials.put("patient01", "patient123");
        userRoles.put("patient01", "Patient");

        userCredentials.put("receptionist01", "recept123");
        userRoles.put("receptionist01", "Receptionist");
    }

    // Admin functionality to generate password
    private static String generatePassword(boolean manual) {
        if (manual) {
            System.out.print("Enter custom password: ");
            return scanner.nextLine();
        } else {
            // Auto-generate a random password
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";
            StringBuilder password = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 8; i++) {
                password.append(chars.charAt(random.nextInt(chars.length())));
            }
            return password.toString();
        }
    }

    // Admin functionality to add or update a user
    private static void manageUsers() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Role (Admin, Doctor, Nurse, Patient, Receptionist): ");
        String role = scanner.nextLine();

        System.out.print("Do you want to set the password manually? (yes/no): ");
        String choice = scanner.nextLine();
        boolean manual = choice.equalsIgnoreCase("yes");

        String password = generatePassword(manual);
        userCredentials.put(userId, password);
        userRoles.put(userId, role);

        System.out.println("User added/updated successfully!");
        System.out.println("User ID: " + userId);
        System.out.println("Role: " + role);
        System.out.println("Password: " + password);
    }

    // Display user credentials
    private static void viewUsers() {
        System.out.println("\nUser Credentials:");
        for (String userId : userCredentials.keySet()) {
            System.out.println("User ID: " + userId + ", Role: " + userRoles.get(userId) + ", Password: " + userCredentials.get(userId));
        }
    }

    // Display hospital banner with formatted day, date, and time
    private static void displayHospitalBanner() {
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String day = now.getDayOfWeek().toString();

        System.out.println("\n-----------------------------------");
        System.out.println(HOSPITAL_NAME);
        System.out.println("  Email: " + EMAIL);
        System.out.println("   Website: " + WEBSITE);
        System.out.println("    Contact No: " + CONTACT_NO);
        System.out.println("       Fax: " + FAX);
        System.out.println("-----------------------------------");
        System.out.println("Day: " + day);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("-----------------------------------");
    }

    public static void main(String[] args) {
        initializeUsers();

        // Show hospital banner only once
        displayHospitalBanner();

        // User authentication
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (!userCredentials.containsKey(userId) || !userCredentials.get(userId).equals(password)) {
            System.out.println("Invalid credentials. Exiting.");
            return;
        }

        String role = userRoles.get(userId);
        System.out.println("Welcome, " + role + "!");
        
        System.out.println("-----------------------------------");


        if (role.equals("Admin")) {
            while (true) {
                System.out.println("Admin Options:");
                System.out.println("1. Add/Update User");
                System.out.println("2. View All Users");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        manageUsers();
                        break;
                    case 2:
                        viewUsers();
                        break;
                    case 3:
                        System.out.println("Goodbye, Admin!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("You have no administrative privileges.");
        }
    }
}
