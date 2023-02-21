import java.security.SecureRandom;
import java.util.*;

public class RandomPasswordGenerator {

    private static final String NUMERIC = "0123456789";
    private static final String UPPER_ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+-=[]}{|;':\",./<>?";

    public static void main(String[] args) {
        // Use try-with-resources to automatically close the Scanner
        try (Scanner scanner = new Scanner(System.in)) {
            int passwordLength = getPasswordLength(scanner);
            List<String> characterSets = getCharacterSets(scanner);
            String password = generateRandomPassword(passwordLength, characterSets);
            System.out.println("Your randomly generated password is: " + password);
        }
    }

    // Method to prompt the user to enter the desired password length
    public static int getPasswordLength(Scanner scanner) {
        System.out.print("Enter password length: ");
        int passwordLength = scanner.nextInt();
        while (passwordLength <= 0) {
            System.out.print("Password length must be greater than 0. Please enter a valid length: ");
            passwordLength = scanner.nextInt();
        }
        return passwordLength;
    }

    // Method to generate the random password
    public static String generateRandomPassword(int length, List<String> characterSets) {
        Random random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        String allChars = String.join("", characterSets);

        // Generate the password character by character
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allChars.length());
            password.append(allChars.charAt(index));
        }
        return password.toString();
    }

    // Method to prompt the user to select which character sets to include in the password
    public static List<String> getCharacterSets(Scanner scanner) {
        List<String> characterSets = new ArrayList<>();
        System.out.println("Choose character sets to include in your password:");
        System.out.println("1. Uppercase letters");
        System.out.println("2. Lowercase letters");
        System.out.println("3. Numbers");
        System.out.println("4. Special characters");
        System.out.println("Enter the corresponding numbers separated by spaces (e.g. 1 3 4):");

        // Consume the newline character left over from previous nextInt() call
        scanner.nextLine();

        String input = scanner.nextLine();
        Set<String> validInputs = new HashSet<>(Arrays.asList("1", "2", "3", "4"));
        String[] inputs = input.split(" ");

        // Use a while loop to keep prompting the user until valid inputs are given
        while (!isValidInputs(inputs, validInputs)) {
            System.out.println("Invalid input. Please enter the corresponding numbers separated by spaces (1 2 4):");
            input = scanner.nextLine();
            inputs = input.split(" ");
        }
        for (String inputNumber : inputs) {
            if (inputNumber.equals("1")) {
                characterSets.add(UPPER_ALPHA);
            } else if (inputNumber.equals("2")) {
                characterSets.add(LOWER_ALPHA);
            } else if (inputNumber.equals("3")) {
                characterSets.add(NUMERIC);
            } else if (inputNumber.equals("4")) {
                characterSets.add(SPECIAL_CHARS);
            }
        }
        return characterSets;
    }

    // Method to check if the user inputs are valid
    public static boolean isValidInputs(String[] inputs, Set<String> validInputs) {
        for (String input : inputs) {
            if (!validInputs.contains(input)) {
                return false;
            }
        }
        return true;
    }

}